package com.dhair.common.library.communicate;

import com.dhair.common.library.communicate.message.Message;

/**
 * Creator: dengshengjin on 16/1/19 19:41
 * Email: deng.shengjin@zuimeia.com
 */
public interface INioClient {

    void init();

    void start() throws Exception;

    void stop() throws Exception;

    void reset() throws Exception;

    void heartbeat() throws Exception;

    void sendData(byte[] data) throws Exception;

    void receiveData() throws Exception;

    void feedbackServer(Message message) throws Exception;
}
