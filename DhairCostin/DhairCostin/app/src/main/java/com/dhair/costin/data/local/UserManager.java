package com.dhair.costin.data.local;

import android.content.Context;

import com.dhair.costin.data.model.UserModel;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

/**
 * Creator: dengshengjin on 16/1/13 17:22
 * Email: deng.shengjin@zuimeia.com
 */
public class UserManager {
    private Context mContext;
    private UserModel mUserModel;

    @Inject
    public UserManager(Context context, UserModel userModel) {
        mContext = context;
        mUserModel = userModel;
    }

    public void print(){
        Logger.e(mUserModel.toString());
    }
}
