package com.dhair.costin.injection.component;

import android.app.Activity;

import com.dhair.costin.data.local.DataHelper;
import com.dhair.costin.injection.PerActivity;
import com.dhair.costin.injection.module.ActivityModule;
import com.dhair.costin.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Creator: dengshengjin on 16/1/12 22:29
 * Email: deng.shengjin@zuimeia.com
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {//Scope其实就是一个Component的对象，Scope的注解就是在Component上的
    Activity activity();

    void inject(SplashActivity splashActivity);

    DataHelper dataHelper();
}