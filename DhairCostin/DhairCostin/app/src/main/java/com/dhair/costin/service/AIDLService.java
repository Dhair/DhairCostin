package com.dhair.costin.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.dhair.costin.IMyAidlInterface;

/**
 * Creator: dengshengjin on 16/2/28 15:48
 * Email: deng.shengjin@zuimeia.com
 */
public class AIDLService extends Service {
    IMyAidlInterface.Stub mStub = new IMyAidlInterface.Stub() {

        @Override
        public int add(int first, int second) throws RemoteException {
            return first + second;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }
}
