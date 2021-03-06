package com.dhair.costin.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.dhair.common.library.util.DHairActivityCompact;
import com.dhair.costin.R;
import com.dhair.costin.application.CostinApplication;
import com.dhair.costin.data.model.UserModel;
import com.dhair.costin.ui.base.activity.BaseMvpActivity;
import com.dhair.costin.ui.home.HomeActivity;
import com.dhair.costin.utils.handler.AbsDispatchMessage;
import com.dhair.costin.utils.handler.DispatchHandler;
import com.orhanobut.logger.Logger;

import java.util.Calendar;

/**
 * Creator: dengshengjin on 16/1/11 11:28
 * Email: deng.shengjin@zuimeia.com
 */
public class SplashActivity extends BaseMvpActivity<SplashPresenter> {
    private DispatchHandler<AbsDispatchMessage> mDispatchHandler;
    private final static int HANDLE_DELAY = 1 << 1;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Nullable
    @Override
    protected SplashPresenter createPresenter(Context context) {
        return new SplashPresenter(context);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        getActivityComponent().inject(this);
        ClassLoader classLoader = getClassLoader();
        if (classLoader != null) {
            Logger.e("[onCreate] SplashActivity classLoader " + " : " + classLoader.toString()+","+Runtime.getRuntime());
            while (classLoader.getParent() != null) {
                classLoader = classLoader.getParent();
                Logger.e("[onCreate] SplashActivity classLoader " + " : " + classLoader.toString());
            }
        }
    }

    @Override
    protected void initWidgets() {
        View v;
        UserModel userModel = new UserModel();
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        userModel.setUserId(timeInMillis);
        userModel.setUsername("User " + timeInMillis);
        CostinApplication.getApplication(getApplicationContext()).createUserComponent(userModel);
    }

    @Override
    protected void initActions() {
        mDispatchHandler = new DispatchHandler<>(new AbsDispatchMessage() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case HANDLE_DELAY:
                        Intent intent = HomeActivity.getStartIntent(getContext());
                        DHairActivityCompact.startActivityWithCustomAnim(SplashActivity.this, intent, R.anim.slide_right_in, R.anim.slide_none);
                        break;
                }
            }
        });
        mDispatchHandler.sendEmptyMessageDelayed(HANDLE_DELAY, 1000);
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDispatchHandler.removeMessages(HANDLE_DELAY);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Logger.e("JobSchedulerService onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }
}
