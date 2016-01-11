package com.dhair.costin.utils.handler;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Creator: dengshengjin on 16/1/11 14:37
 * Email: deng.shengjin@zuimeia.com
 */
public class DispatchHandler<T extends IDispatchMessage> extends Handler {
    private WeakReference<T> mWeakReference;

    public DispatchHandler(T t) {
        mWeakReference = new WeakReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (mWeakReference != null) {
            T t = mWeakReference.get();
            if (t != null) {
                t.handleMessage(msg);
            }
        }
    }
}
