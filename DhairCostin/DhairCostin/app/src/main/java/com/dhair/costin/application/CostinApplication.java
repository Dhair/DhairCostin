package com.dhair.costin.application;

import android.app.Application;

import com.dhair.costin.BuildConfig;
import com.dhair.hotfix.DhairHotFix;
import com.dhair.uninstall.watcher.Watcher;

/**
 * Creator: dengshengjin on 16/1/8 20:02
 * Email: deng.shengjin@zuimeia.com
 */
public class CostinApplication extends Application {
    private static final String URL = "http://www.baidu.com";

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {

        }

        DhairHotFix.init(this);
        DhairHotFix.loadPatch(this, "/mnt/sdcard/dhair_hotfix_patch.jar");

        Watcher.run(this, URL, true);

    }


}
