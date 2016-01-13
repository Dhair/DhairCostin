package com.dhair.costin.ui.base.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.dhair.costin.application.CostinApplication;
import com.dhair.costin.injection.component.ActivityComponent;
import com.dhair.costin.injection.component.DaggerActivityComponent;
import com.dhair.costin.injection.module.ActivityModule;
import com.dhair.costin.ui.base.fragment.HashComponent;

/**
 * Creator: dengshengjin on 16/1/12 23:10
 * Email: deng.shengjin@zuimeia.com
 */
public abstract class BaseDaggerActivity extends AppCompatActivity implements HashComponent<ActivityComponent> {
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
    public ActivityComponent getComponent() {
        return mActivityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onContentChanged() {//即setContentView()或者addContentView()方法执行完毕时就会调用该方法
        super.onContentChanged();
    }

    @Override
    protected void onRestart() {//onStop后重新回来
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {//内存不足再次进入时
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void onPostCreate(Bundle savedInstanceState) {//onCreate方法彻底执行完毕的回调
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {//onResume方法彻底执行完毕的回调
        super.onPostResume();
    }

    @Override
    protected void onPause() {//整个窗口被半遮盖或者半透明的时候会执行，
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {//在整个窗口被完全遮盖才会触发
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {//在整个窗口被完全遮盖才会触发, 触发onStop的方法之前必定会触发onPause方法
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityComponent = null;
    }

    //启动Activity:onCreate -> onContentChanged -> onStart -> onPostCreate -> onResume -> onPostResume
    //被覆盖      :onPause -> onSaveInstanceState -> onStop
    //重新进入    :onRestart -> onStart -> onResume -> onPostResume
    //点击后退    :onPause -> onStop -> onDestroy
    //按Home     :onPause -> onSaveInstance -> onStop
    //开启不保留活动Home:onPause ->onSaveInstance -> onStop -> onDestroy
    //开启不保留活动重新进入:onCreate -> onContentChanged -> onStart -> onRestoreInstanceState -> onPostCreate -> onResume -> onPostResume

}
