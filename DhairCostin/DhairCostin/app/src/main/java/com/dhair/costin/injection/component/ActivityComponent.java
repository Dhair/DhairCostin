package com.dhair.costin.injection.component;

import android.app.Activity;

import com.dhair.costin.injection.scope.ActivityScope;
import com.dhair.costin.injection.module.ActivityModule;
import com.dhair.costin.ui.home.HomeActivity;
import com.dhair.costin.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Creator: dengshengjin on 16/1/12 22:29
 * Email: deng.shengjin@zuimeia.com
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();

    void inject(SplashActivity splashActivity);

    void inject(HomeActivity homeActivity);

}