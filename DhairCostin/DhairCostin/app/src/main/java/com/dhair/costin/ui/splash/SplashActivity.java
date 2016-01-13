package com.dhair.costin.ui.splash;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.dhair.costin.R;
import com.dhair.costin.application.CostinApplication;
import com.dhair.costin.data.DataManager;
import com.dhair.costin.data.model.UserModel;
import com.dhair.costin.ui.base.activity.BaseMvpActivity;
import com.dhair.costin.ui.home.HomeActivity;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Creator: dengshengjin on 16/1/11 11:28
 * Email: deng.shengjin@zuimeia.com
 */
public class SplashActivity extends BaseMvpActivity<SplashPresenter> {
    @Bind(R.id.textview)
    TextView mTextView;

    @Inject
    DataManager mDataManager;

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
        mTextView.setText(new Hello().say());
        mTextView.setOnClickListener(v -> {
            startActivity(HomeActivity.getStartIntent(SplashActivity.this));
            finish();
        });

        UserModel userModel = new UserModel();
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        userModel.setUserId(timeInMillis);
        userModel.setUsername("User " + timeInMillis);
        CostinApplication.getApplication(getApplicationContext()).createUserComponent(userModel);
    }

    @Override
    protected void initActions() {

    }
}
