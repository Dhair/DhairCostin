package com.dhair.costin.injection.module;

import android.app.Application;
import android.content.Context;

import com.dhair.costin.data.remote.WallpaperService;
import com.dhair.costin.injection.ApplicationContext;

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
    Application providerApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context providerContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    WallpaperService provideWallpaperService() {
        return WallpaperService.Factory.makeService(mApplication);
    }
}