package com.dhair.costin.ui.splash;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.dhair.costin.R;
import com.dhair.costin.ui.base.BaseActivity;
import com.dhair.costin.ui.home.HomeActivity;

import butterknife.Bind;

/**
 * Creator: dengshengjin on 16/1/11 11:28
 * Email: deng.shengjin@zuimeia.com
 */
public class SplashActivity extends BaseActivity<SplashPresenter> {
    @Bind(R.id.textview)
    TextView mTextView;

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
