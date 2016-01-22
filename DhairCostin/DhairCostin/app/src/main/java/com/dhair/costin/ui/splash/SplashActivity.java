package com.dhair.costin.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.annotation.Nullable;

import com.dhair.costin.R;
import com.dhair.costin.application.CostinApplication;
import com.dhair.costin.data.model.UserModel;
import com.dhair.costin.ui.base.activity.BaseMvpActivity;
import com.dhair.costin.ui.home.HomeActivity;
import com.dhair.costin.utils.handler.AbsDispatchMessage;
import com.dhair.costin.utils.handler.DispatchHandler;

import java.util.Calendar;

/**
 * Creator: dengshengjin on 16/1/11 11:28
 * Email: deng.shengjin@zuimeia.com
 */
public class SplashActivity extends BaseMvpActivity<SplashPresenter> {
    private DispatchHandler<AbsDispatchMessage> mDispatchHandler;
    private final static int HANDLE_DELAY = 1 << 1;

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
    }

    @Override
    protected void initWidgets() {
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
                        startActivity(intent);

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
}
