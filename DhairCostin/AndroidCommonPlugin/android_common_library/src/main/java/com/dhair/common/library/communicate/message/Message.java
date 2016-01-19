package com.dhair.common.library.communicate.message;

import java.net.SocketAddress;
import java.nio.ByteBuffer;

/**
 * Creator: dengshengjin on 16/1/19 19:09
 * Email: deng.shengjin@zuimeia.com
 */
public class Message {

    public static final int SERVER_MESSAGE_MIN_LENGTH = 5;
    public static final int CLIENT_MESSAGE_MIN_LENGTH = 21;
    public static final int CMD_HEARTBEAT = 0x0;//心跳消息
    public static final int CMD_NORMAL = 0x1;//通用消息
    public static final int CMD_CATEGORY = 0x2;//分类消息
    public static final int CMD_CUSTOM = 0x3;//定制消息
    private static int mVersion = 1;

    private SocketAddress mSocketAddress;
    private byte[] mData;

    public Message(SocketAddress socketAddress, byte[] data) {
        mSocketAddress = socketAddress;
        mData = data;
    }

    public int getContentLength() {
        return ByteBuffer.wrap(mData, SERVER_MESSAGE_MIN_LENGTH - 2, 2).getChar();
    }

    public int getCmd() {
        byte b = mData[2];
        return b & 0xff;
    }

    public boolean checkFormat() {
        if (mSocketAddress == null || mData == null || mData.length < SERVER_MESSAGE_MIN_LENGTH) {
            return false;
        }
        int cmd = getCmd();
        if (cmd != CMD_HEARTBEAT && cmd != CMD_NORMAL && cmd != CMD_CATEGORY && cmd != CMD_CUSTOM) {
            return false;
        }
        int dataLength = getContentLength();
        if (mData.length != (dataLength + SERVER_MESSAGE_MIN_LENGTH)) {
            return false;
        }
        if (cmd == CMD_NORMAL && dataLength != 0) {
            return false;
        }
        if (cmd == CMD_CATEGORY && dataLength != 8) {
            return false;
        }
        if (cmd == CMD_CUSTOM && dataLength < 1) {
            return false;
        }
        return true;
    }

    public byte[] getData() {
        return mData;
    }

    public void setData(byte[] data) {
        mData = data;
    }

    public SocketAddress getSocketAddress() {
        return mSocketAddress;
    }

    public void setSocketAddress(SocketAddress socketAddress) {
        mSocketAddress = socketAddress;
    }

    public static int getVersion() {
        return mVersion;
    }

    public static void setVersion(int version) {
        if (version < 1 || version > 255) {
            return;
        }
        mVersion = version;
    }
}
