package com.dhair.costin.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.dhair.costin.application.CostinApplication;
import com.dhair.costin.injection.component.ActivityComponent;
import com.dhair.costin.injection.component.DaggerActivityComponent;
import com.dhair.costin.injection.module.ActivityModule;

/**
 * Creator: dengshengjin on 16/1/12 23:10
 * Email: deng.shengjin@zuimeia.com
 */
public abstract class BaseDaggerActivity extends AppCompatActivity {
    private ActivityComponent mActivityComponent;
    private Handler mHandler;

    protected ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(CostinApplication.getApplication(this).getApplicationComponent())
                    .build();
        }
        return mActivityComponent;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    protected Handler getHandler() {
        if (mHandler == null) {
            synchronized (this) {
                if (mHandler == null) {
                    mHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return mHandler;
    }

    protected Context getContext() {
        return getApplicationContext();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityComponent = null;
    }
}
