package com.dhair.costin.injection.component;

import android.app.Application;
import android.content.Context;

import com.dhair.costin.application.CostinApplication;
import com.dhair.costin.data.remote.WallpaperService;
import com.dhair.costin.injection.context.ApplicationContext;
import com.dhair.costin.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Creator: dengshengjin on 16/1/12 22:31
 * Email: deng.shengjin@zuimeia.com
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(CostinApplication costinApplication);

    @ApplicationContext
    Context context(); //提供ApplicationContext

    WallpaperService wallpaperService();

    Application application();
}
