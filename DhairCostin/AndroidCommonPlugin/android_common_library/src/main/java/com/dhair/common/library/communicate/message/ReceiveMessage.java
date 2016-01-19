package com.dhair.common.library.communicate.message;

import java.net.SocketAddress;

/**
 * Creator: dengshengjin on 16/1/19 20:44
 * Email: deng.shengjin@zuimeia.com
 */
public class ReceiveMessage extends Message {
    public ReceiveMessage(SocketAddress socketAddress, byte[] data) {
        super(socketAddress, data);
    }
}
