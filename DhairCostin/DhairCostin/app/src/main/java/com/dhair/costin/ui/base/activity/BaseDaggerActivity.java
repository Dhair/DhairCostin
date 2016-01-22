package com.dhair.costin.ui.base.activity;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

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


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onUserInteraction() {
        // dispatchKeyEvent 实体back home menu
        // dispatchKeyShortcutEvent 调度快捷键的事件
        // dispatchTouchEvent(ActionDown)
        // dispatchTrackballEvent 通过轨迹球移动事件下到集中视图。
        // dispatchGenericMotionEvent 派遣一个通用的移动事件
        //performUserLeaving
        super.onUserInteraction();
    }

    protected boolean isLowRamDevice() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        return ActivityManagerCompat.isLowRamDevice(activityManager);
    }

    @Override
    public void onLowMemory() {//已经没有后台进程
        super.onLowMemory();
    }

    //4.0 add
    //TRIM_MEMORY_COMPLETE 内存不足，并且该进程在后台进程列表最后一个，马上就要被清理
    //TRIM_MEMORY_MODERATE 内存不足，并且该进程在后台进程列表的中部
    //TRIM_MEMORY_BACKGROUND 内存不足，并且该进程是后台进程
    //TRIM_MEMORY_UI_HIDDEN 内存不足，并且该进程的UI已经不可见了
    //4.1 add
    //TRIM_MEMORY_RUNNING_CRITICAL 内存不足(后台进程不足3个)，并且该进程优先级比较高，需要清理内存
    //TRIM_MEMORY_RUNNING_LOW 内存不足(后台进程不足5个)，并且该进程优先级比较高，需要清理内存
    //TRIM_MEMORY_RUNNING_MODERATE 内存不足(后台进程超过5个)，并且该进程优先级比较高，需要清理内存

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onTrimMemory(int level) {//4.0之后的API
        super.onTrimMemory(level);
    }

    //1，OnLowMemory被回调时，已经没有后台进程；而onTrimMemory被回调时，还有后台进程。
    //2，OnLowMemory是在最后一个后台进程被杀时调用，一般情况是low memory killer 杀进程后触发；而OnTrimMemory的触发更频繁，每次计算进程优先级时，只要满足条件，都会触发。
    //3，通过一键清理后，OnLowMemory不会被触发，而OnTrimMemory会被触发一次。
}
