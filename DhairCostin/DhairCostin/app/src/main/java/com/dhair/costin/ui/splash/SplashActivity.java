package com.dhair.costin.ui.splash;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.dhair.costin.R;
import com.dhair.costin.data.local.DataHelper;
import com.dhair.costin.data.local.DataManager;
import com.dhair.costin.ui.base.BaseMvpActivity;
import com.dhair.costin.ui.home.HomeActivity;

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
    @Inject
    DataHelper mDataHelper;

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
        mDataManager.print(getActivityComponent().activity().toString() + ",");
        mDataHelper.print();
        mTextView.setOnClickListener(v -> startActivity(HomeActivity.getStartIntent(SplashActivity.this)));
    }

    @Override
    protected void initActions() {

    }
}
