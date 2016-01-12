package com.dhair.costin.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.dhair.costin.R;
import com.dhair.costin.ui.base.BaseActivity;
import com.dhair.costin.ui.base.BasePresenter;
import com.dhair.costin.utils.exitapp.ExitAppHelper;

/**
 * Creator: dengshengjin on 16/1/11 10:30
 * Email: deng.shengjin@zuimeia.com
 */
public class HomeActivity extends BaseActivity {


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initWidgets() {

    }

    @Override
    protected void initActions() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: {
                ExitAppHelper.exitAppFinally(getApplicationContext());
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @NonNull
    @Override
    protected BasePresenter createPresenter(Context context) {
        return new HomePresenter(context);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }
}
