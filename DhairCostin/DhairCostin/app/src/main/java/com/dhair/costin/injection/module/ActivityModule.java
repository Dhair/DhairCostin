package com.dhair.costin.injection.module;

import android.app.Activity;
import android.content.Context;

import com.dhair.costin.data.local.DataHelper;
import com.dhair.costin.injection.ActivityContext;
import com.dhair.costin.injection.PerActivity;

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
    @PerActivity
    Activity provideActivity() { //Scope机制可以使得在scope存在时保持类的单例
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {//提供ActivityContext的具体实现
        return mActivity;
    }

    @Provides
    @PerActivity
    DataHelper providerDataHelper(Activity activity){
        return new DataHelper(activity);
    }

}
