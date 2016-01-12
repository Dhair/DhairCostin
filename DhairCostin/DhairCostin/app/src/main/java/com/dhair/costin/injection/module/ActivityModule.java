package com.dhair.costin.injection.module;

import android.app.Activity;
import android.content.Context;

import com.dhair.costin.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Creator: dengshengjin on 16/1/12 20:56
 * Email: deng.shengjin@zuimeia.com
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity providerActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providerContext() {
        return mActivity;
    }
}
