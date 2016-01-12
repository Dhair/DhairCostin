package com.dhair.costin.ui.splash;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.dhair.costin.R;
import com.dhair.costin.ui.base.BaseActivity;
import com.dhair.costin.ui.home.HomeActivity;
import com.dhair.costin.utils.exitapp.ExitAppHelper;

import butterknife.Bind;

/**
 * Creator: dengshengjin on 16/1/11 11:28
 * Email: deng.shengjin@zuimeia.com
 */
public class SplashActivity extends BaseActivity<SplashPresenter> {
    @Bind(R.id.textview)
    TextView mTextView;
    private ExitAppHelper mExitAppHelper;

    @Nullable
    @Override
    protected SplashPresenter createPresenter(Context context) {
        return new SplashPresenter();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        mExitAppHelper = new ExitAppHelper(SplashActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExitAppHelper.registerReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExitAppHelper.unregisterReceiver();
    }

    @Override
    protected void initWidgets() {
        mTextView.setText(new Hello().say());
        mTextView.setOnClickListener(v -> startActivity(HomeActivity.getStartIntent(SplashActivity.this)));
    }

    @Override
    protected void initActions() {

    }
}
