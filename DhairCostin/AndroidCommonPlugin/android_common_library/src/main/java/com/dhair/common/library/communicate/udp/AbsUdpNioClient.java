package com.dhair.common.library.communicate.udp;

import com.dhair.common.library.communicate.INioClient;
import com.dhair.common.library.communicate.message.Message;
import com.dhair.common.library.communicate.message.ReceiveMessage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Creator: dengshengjin on 16/1/19 11:25
 * Email: deng.shengjin@zuimeia.com
 */
public abstract class AbsUdpNioClient implements Runnable, INioClient {
    private DatagramSocket mDatagramSocket;
    private long mLastSent;
    private long mLastReceived;
    private int mRemotePort = 9966;
    private int mAppId = 1;
    private byte[] mUuid;
    private String mRemoteAddress;
    private ConcurrentLinkedQueue<ReceiveMessage> mMessageQueue = new ConcurrentLinkedQueue<>();

    private AtomicLong mQueueIn = new AtomicLong(0);
    private AtomicLong mQueueOut = new AtomicLong(0);

    private int mBufferSize = 1024;
    private int mHeartBeatInterval = 50;

    private byte[] mBufferArr;
    private ByteBuffer mByteBuffer;
    private boolean mNeedReset = true;

    private boolean mStarted = false;
    private boolean mStopped = false;

    private Thread mReceiverThread;
    private Thread mWorkerThread;
    private Worker mWorker;

    private long mSendPackets;
    private long mReceivedPackets;


    public AbsUdpNioClient(byte[] uuid, int appId, String remoteAddress, int remotePort) {
        if (uuid == null || uuid.length != 16) {
            throw new UuidFormatException();
        }
        if (appId < 1 || appId > 255) {
            throw new AppIdFormatException();
        }
        if (remoteAddress == null || remoteAddress.length() == 0) {
            throw new RemoteAddressException();
        }
        mUuid = uuid;
        mAppId = appId;
        mRemoteAddress = remoteAddress;
        mRemotePort = remotePort;
    }

    private boolean enqueue(ReceiveMessage message) {
        boolean result = mMessageQueue.add(message);
        if (result) {
            mQueueIn.addAndGet(1);
        }
        return result;
    }

    private Message dequeue() {
        Message message = mMessageQueue.poll();
        if (message != null) {
            mQueueOut.addAndGet(1);
        }
        return message;
    }

    @Override
    public synchronized void init() {
        mBufferArr = new byte[mBufferSize];
        mByteBuffer = ByteBuffer.wrap(mBufferArr);
    }

    @Override
    public synchronized void start() throws Exception {
        if (mStarted) {
            return;
        }
        init();
        mReceiverThread = new Thread(this, "UDPClientReceiver");
        mReceiverThread.setDaemon(true);
        synchronized (mReceiverThread) {
            mReceiverThread.start();
            mReceiverThread.wait();
        }

        mWorker = new Worker(this);
        mWorkerThread = new Thread(mWorker, "UDPClientWorker");
        mWorkerThread.setDaemon(true);
        synchronized (mWorkerThread) {
            mWorkerThread.start();
            mWorkerThread.wait();
        }
        mStarted = true;
    }

    @Override
    public void stop() throws Exception {
        mStopped = true;
        if (mDatagramSocket != null) {
            try {
                mDatagramSocket.close();
            } catch (Exception t) {
                t.printStackTrace();
            }
            mDatagramSocket = null;
        }
        if (mReceiverThread != null) {
            try {
                mReceiverThread.interrupt();
            } catch (Exception t) {
                t.printStackTrace();
            }
        }
        if (mWorkerThread != null) {
            try {
                mWorkerThread.interrupt();
            } catch (Exception t) {
                t.printStackTrace();
            }
        }
    }

    @Override
    public void reset() throws Exception {
        if (!mNeedReset) {
            return;
        }
        if (mDatagramSocket != null) {
            try {
                mDatagramSocket.close();
            } catch (Throwable t) {
                t.printStackTrace();
            }
            mDatagramSocket = null;
        }
        if (hasNetworkConnection()) {
            mDatagramSocket = new DatagramSocket();
            mDatagramSocket.connect(new InetSocketAddress(mRemoteAddress, mRemotePort));
            mNeedReset = false;
        } else {
            try {
                Thread.sleep(1000);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    @Override
    public void heartbeat() throws Exception {
        if (System.currentTimeMillis() - mLastSent < mHeartBeatInterval * 1000) {
            return;
        }
        byte[] buffer = new byte[Message.CLIENT_MESSAGE_MIN_LENGTH];
        ByteBuffer.wrap(buffer)
                .put((byte) Message.getVersion())
                .put((byte) mAppId)
                .put((byte) Message.CMD_HEARTBEAT)
                .put(mUuid)
                .putChar((char) 0);
        sendData(buffer);
    }


    @Override
    public void sendData(byte[] data) throws Exception {
        if (data == null) {
            return;
        }
        if (mDatagramSocket == null) {
            return;
        }
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
        datagramPacket.setSocketAddress(mDatagramSocket.getRemoteSocketAddress());
        mDatagramSocket.send(datagramPacket);
        mLastSent = System.currentTimeMillis();
        mSendPackets++;
    }

    @Override
    public void receiveData() throws Exception {
        DatagramPacket datagramPacket = new DatagramPacket(mBufferArr, mBufferArr.length);
        mDatagramSocket.setSoTimeout(5000);
        mDatagramSocket.receive(datagramPacket);
        if (datagramPacket.getData() == null || datagramPacket.getLength() <= 0 || datagramPacket.getData().length <= 0) {
            return;
        }
        byte[] data = new byte[datagramPacket.getLength()];

        System.arraycopy(datagramPacket.getData(), 0, data, 0, datagramPacket.getLength());
        ReceiveMessage message = new ReceiveMessage(datagramPacket.getSocketAddress(), data);
        if (!message.checkFormat()) {
            return;
        }
        mReceivedPackets++;
        mLastReceived = System.currentTimeMillis();
        if (message.getCmd() == Message.CMD_HEARTBEAT) {
            return;
        }
        feedbackServer(message);
        enqueue(message);
        mWorker.wakeup();
    }

    @Override
    public void feedbackServer(Message message) throws Exception {
        if (message.getCmd() == Message.CMD_NORMAL) {
            byte[] buffer = new byte[Message.CLIENT_MESSAGE_MIN_LENGTH];
            ByteBuffer.wrap(buffer)
                    .put((byte) Message.getVersion())
                    .put((byte) mAppId)
                    .put((byte) Message.CMD_NORMAL)
                    .put(mUuid)
                    .putChar((char) 0);
            sendData(buffer);
        } else if (message.getCmd() == Message.CMD_CATEGORY) {
            byte[] buffer = new byte[Message.CLIENT_MESSAGE_MIN_LENGTH + 8];
            byte[] data = message.getData();
            ByteBuffer.wrap(buffer)
                    .put((byte) Message.getVersion())
                    .put((byte) mAppId)
                    .put((byte) Message.CMD_CATEGORY)
                    .put(mUuid)
                    .putChar((char) 8)
                    .put(data, Message.SERVER_MESSAGE_MIN_LENGTH, 8);
            sendData(buffer);
        } else if (message.getCmd() == Message.CMD_CUSTOM) {
            byte[] buffer = new byte[Message.CLIENT_MESSAGE_MIN_LENGTH];
            ByteBuffer.wrap(buffer)
                    .put((byte) Message.getVersion())
                    .put((byte) mAppId)
                    .put((byte) Message.CMD_CUSTOM)
                    .put(mUuid)
                    .putChar((char) 0);
            sendData(buffer);
        }
    }

    @Override
    public void run() {
        synchronized (mReceiverThread) {
            mReceiverThread.notifyAll();
        }
        while (!mStopped) {
            try {
                if (!hasNetworkConnection()) {
                    try {
                        trySystemSleep();
                        Thread.sleep(1000);
                    } catch (Exception t) {
                        t.printStackTrace();
                    }
                    continue;
                }
                reset();
                heartbeat();
                receiveData();
            } catch (SocketTimeoutException e) {

            } catch (Exception e) {
                mNeedReset = true;
            } catch (Throwable t) {
                mNeedReset = true;
            } finally {
                if (mNeedReset) {
                    try {
                        trySystemSleep();
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (mMessageQueue.isEmpty() || !hasNetworkConnection()) {
                    try {
                        trySystemSleep();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        if (mDatagramSocket != null) {
            try {
                mDatagramSocket.close();
            } catch (Throwable t) {
                t.printStackTrace();
            }
            mDatagramSocket = null;
        }
    }


    public static class Worker implements Runnable {
        private AbsUdpNioClient mClient;

        public Worker(AbsUdpNioClient client) {
            mClient = client;
        }

        @Override
        public void run() {
            synchronized (mClient.mWorker) {
                mClient.mWorker.notifyAll();
            }
            while (!mClient.isStopped()) {
                try {
                    handleEvent();
                } catch (Exception t) {
                    t.printStackTrace();
                } finally {
                    waitMessage();
                }
            }
        }

        private void wakeup() {
            synchronized (this) {
                this.notifyAll();
            }
        }

        private void waitMessage() {
            synchronized (this) {
                try {
                    wait(1000);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }

        private void handleEvent() throws Exception {
            Message message;
            while (true) {
                message = mClient.dequeue();
                if (message == null) {
                    return;
                }
                if (!message.checkFormat()) {
                    continue;
                }
                mClient.onPushMessage(message);
            }
        }
    }


    public static class UuidFormatException extends RuntimeException {
        public UuidFormatException() {
            super("uuid byte array must be not null and length of 16 bytes");
        }
    }

    public static class AppIdFormatException extends RuntimeException {
        public AppIdFormatException() {
            super("AppId must be from 1 to 255");
        }
    }

    public static class RemoteAddressException extends RuntimeException {
        public RemoteAddressException() {
            super("server address illegal");
        }
    }

    public long getReceivedPackets() {
        return mReceivedPackets;
    }

    public long getSendPackets() {
        return mSendPackets;
    }

    public byte[] getUuid() {
        return mUuid;
    }

    public int getAppId() {
        return mAppId;
    }

    public long getLastReceived() {
        return mLastReceived;
    }

    public boolean isStopped() {
        return mStopped;
    }

    protected abstract boolean hasNetworkConnection();

    protected abstract void trySystemSleep();

    protected abstract void onPushMessage(Message message);
}
