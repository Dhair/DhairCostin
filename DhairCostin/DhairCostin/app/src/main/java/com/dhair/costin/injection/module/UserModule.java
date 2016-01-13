package com.dhair.costin.injection.module;

import android.content.Context;

import com.dhair.costin.data.local.UserManager;
import com.dhair.costin.data.model.UserModel;
import com.dhair.costin.injection.context.ApplicationContext;
import com.dhair.costin.injection.scope.UserScope;

import dagger.Module;
import dagger.Provides;

/**
 * Creator: dengshengjin on 16/1/13 17:14
 * Email: deng.shengjin@zuimeia.com
 */
@Module
public class UserModule {
    private final UserModel mUserModel;

    public UserModule(UserModel userModel) {
        mUserModel = userModel;
    }

    @Provides
    @UserScope
    UserModel providerUserModel() {
        return mUserModel;
    }

    @Provides
    @UserScope
    UserManager providerUserManager(@ApplicationContext Context context, UserModel userModel) {
        return new UserManager(context, userModel);
    }
}
