package com.dhair.costin.application;

import android.app.Application;
import android.content.Context;

import com.dhair.common.process.ProcessManager;
import com.dhair.costin.BuildConfig;
import com.dhair.hotfix.DhairHotFix;
import com.dhair.uninstall.watcher.Watcher;
import com.orhanobut.logger.Logger;


/**
 * Creator: dengshengjin on 16/1/8 20:02
 * Email: deng.shengjin@zuimeia.com
 */
public class CostinApplication extends Application {
    private static final String URL = "http://www.baidu.com";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        DhairHotFix.init(this);
        DhairHotFix.loadPatch(this, "/mnt/sdcard/dhair_hotfix_patch.jar");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init();
        if (BuildConfig.DEBUG) {
            Logger.e("isMyProcessInTheForeground "+ ProcessManager.isMyProcessInTheForeground());
        }

        Watcher.run(this, URL, true);

    }


}
