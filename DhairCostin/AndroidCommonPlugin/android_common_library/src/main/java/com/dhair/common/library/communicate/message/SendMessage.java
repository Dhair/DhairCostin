package com.dhair.common.library.communicate.message;

import java.net.SocketAddress;
import java.nio.ByteBuffer;

/**
 * Creator: dengshengjin on 16/1/19 20:44
 * Email: deng.shengjin@zuimeia.com
 */
public class SendMessage extends Message {
    public SendMessage(SocketAddress socketAddress, byte[] data) {
        super(socketAddress, data);
    }

    public static SendMessage newMessage(int appId, int msgType, byte[] uuid, String content) {
        if (content == null || content.length() == 0) {
            return null;
        }

        byte[] contentBuffer = content.getBytes();
        byte[] data = new byte[Message.CLIENT_MESSAGE_MIN_LENGTH + contentBuffer.length];
        ByteBuffer.wrap(data)
                .put((byte) Message.getVersion())
                .put((byte) appId)
                .put((byte) msgType)
                .put(uuid)
                .putShort((short) contentBuffer.length)
                .put(contentBuffer)
                .put((byte) '\n');
        return new SendMessage(null, data);//null 发送至远程服务器
    }
}
