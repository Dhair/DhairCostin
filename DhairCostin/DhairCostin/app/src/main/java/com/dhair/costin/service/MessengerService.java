package com.dhair.costin.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Creator: dengshengjin on 16/2/28 15:41
 * Email: deng.shengjin@zuimeia.com
 */
public class MessengerService extends Service {
    Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    int first = msg.getData().getInt("first");
                    int second = msg.getData().getInt("second");
                    Message message = Message.obtain();
                    message.what = 1;
                    try {
                        Bundle bundle = new Bundle();
                        bundle.putInt("result", (first + second));
                        message.setData(bundle);
                        Messenger messenger = msg.replyTo;
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
