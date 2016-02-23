package com.dhair.costin.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.dhair.common.process.ProcessManager;
import com.dhair.costin.BuildConfig;
import com.dhair.costin.data.model.UserModel;
import com.dhair.costin.injection.component.ApplicationComponent;
import com.dhair.costin.injection.component.DaggerApplicationComponent;
import com.dhair.costin.injection.component.DaggerUserComponent;
import com.dhair.costin.injection.component.UserComponent;
import com.dhair.costin.injection.module.ApplicationModule;
import com.dhair.costin.injection.module.UserModule;
import com.dhair.hotfix.DhairHotFix;
import com.dhair.uninstall.watcher.Watcher;
import com.orhanobut.logger.Logger;


/**
 * Creator: dengshengjin on 16/1/8 20:02
 * Email: deng.shengjin@zuimeia.com
 */
public class CostinApplication extends Application {
    private static final String URL = "http://www.baidu.com";
    private ApplicationComponent mApplicationComponent;
    private UserComponent mUserComponent;
    private final static String TAG = CostinApplication.class.getSimpleName();
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        DhairHotFix.init(this);
        DhairHotFix.loadPatch(this, "/mnt/sdcard/dhair_hotfix_patch.jar");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(TAG);
        if (BuildConfig.DEBUG) {
            ProcessManager.isMyProcessInTheForeground();
        }

        Watcher.run(this, URL, true);

        setupApplicationComponent();

    }

    public static CostinApplication getApplication(@NonNull Context context) {
        return (CostinApplication) context.getApplicationContext();
    }

    private void setupApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public UserComponent createUserComponent(UserModel userModel) {
        mUserComponent = DaggerUserComponent.builder()
                .applicationComponent(mApplicationComponent)
                .userModule(new UserModule(userModel))
                .build();
        return mUserComponent;
    }

    public UserComponent getUserComponent() {
        return mUserComponent;
    }

    public void releaseUserComponent() {
        mUserComponent = null;
    }
}
