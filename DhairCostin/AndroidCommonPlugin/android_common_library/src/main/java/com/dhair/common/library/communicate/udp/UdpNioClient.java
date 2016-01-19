package com.dhair.common.library.communicate.udp;

import com.dhair.common.library.communicate.message.Message;
import com.dhair.common.library.communicate.message.SendMessage;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Creator: dengshengjin on 16/1/19 20:37
 * Email: deng.shengjin@zuimeia.com
 */
public class UdpNioClient extends AbsUdpNioClient {
    private Executor mExecutor;

    public UdpNioClient(byte[] uuid, int appId, String remoteAddress, int remotePort) {
        super(uuid, appId, remoteAddress, remotePort);
        mExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    protected boolean hasNetworkConnection() {
        return true;
    }

    @Override
    protected void trySystemSleep() {

    }

    @Override
    protected void onPushMessage(Message message) {
        if (message == null) {
            System.out.println("message is null");
        }
        if (message.getData() == null || message.getData().length == 0) {
            System.out.println("message has no data");
        }
        System.out.println(message.getData());
    }

    public void sendContent(final String content) throws Exception {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    SendMessage message = SendMessage.newMessage(getAppId(), Message.CMD_CUSTOM, getUuid(), content);
                    if (message != null) {
                        sendData(message.getData());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
