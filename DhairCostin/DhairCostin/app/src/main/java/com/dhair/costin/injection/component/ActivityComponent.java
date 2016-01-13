package com.dhair.costin.injection.component;

import com.dhair.costin.injection.ActivityScope;
import com.dhair.costin.injection.module.ActivityModule;
import com.dhair.costin.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Creator: dengshengjin on 16/1/12 22:29
 * Email: deng.shengjin@zuimeia.com
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity splashActivity);
}