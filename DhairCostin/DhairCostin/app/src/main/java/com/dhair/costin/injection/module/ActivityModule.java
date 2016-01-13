package com.dhair.costin.injection.module;

import android.app.Activity;
import android.content.Context;

import com.dhair.costin.injection.context.ActivityContext;
import com.dhair.costin.injection.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Creator: dengshengjin on 16/1/12 20:56
 * Email: deng.shengjin@zuimeia.com
 */
@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return mActivity;
    }

    //标识mActivity 的生命周期与ActivityComponent 的生命周期一致,Activity 执行OnDestroy 时
    //ActivityComponent = null 则该对象也 消亡

    @Provides
    @ActivityContext
    @ActivityScope
    Context providesContext() {//提供ActivityContext的具体实现
        return mActivity;
    }

}
