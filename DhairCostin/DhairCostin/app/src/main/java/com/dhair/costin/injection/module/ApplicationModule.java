package com.dhair.costin.injection.module;

import android.app.Application;
import android.content.Context;

import com.dhair.costin.injection.context.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Creator: dengshengjin on 16/1/12 20:23
 * Email: deng.shengjin@zuimeia.com
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providerApplication() {//提供Application 的具体实现
        return mApplication;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context providerContext() {//提供ApplicationContext的具体实现
        return mApplication;
    }
}