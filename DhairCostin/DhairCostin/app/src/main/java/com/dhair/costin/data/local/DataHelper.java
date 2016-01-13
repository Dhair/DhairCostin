package com.dhair.costin.data.local;

import android.app.Activity;

import com.orhanobut.logger.Logger;

/**
 * Creator: dengshengjin on 16/1/13 11:39
 * Email: deng.shengjin@zuimeia.com
 */
public class DataHelper {

    private Activity mActivity;

    public DataHelper(Activity activity) {
        mActivity = activity;
    }

    public void print(){
        Logger.e("DataHelper print"+mActivity.toString());
    }
}
