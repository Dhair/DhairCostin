package com.dhair.costin.ui.main;

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
public class EnterActivity extends BaseActivity<EnterPresenter> {
    @Bind(R.id.textview)
    TextView mTextView;
    private ExitAppHelper mExitAppHelper;

    @Nullable
    @Override
    protected EnterPresenter createPresenter(Context context) {
        return new EnterPresenter();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_enter;
    }

    @Override
    protected void initData() {
        mExitAppHelper = new ExitAppHelper(EnterActivity.this);
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
        mTextView.setOnClickListener(v -> startActivity(HomeActivity.getStartIntent(EnterActivity.this)));
    }

    @Override
    protected void initActions() {

    }
}
