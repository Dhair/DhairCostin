package com.dhair.costin.ui.home;

import android.content.Context;
import android.os.Bundle;

import com.dhair.costin.ui.base.BasePresenter;
import com.dhair.costin.utils.exitapp.ExitAppHelper;

/**
 * Creator: dengshengjin on 16/1/11 10:31
 * Email: deng.shengjin@zuimeia.com
 */
public class HomePresenter extends BasePresenter<HomeMvpView> {
    private ExitAppHelper mExitAppHelper;

    public HomePresenter(Context context) {
        super(context);
        mExitAppHelper = new ExitAppHelper(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mExitAppHelper.registerReceiver();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        mExitAppHelper.unregisterReceiver();
    }
}
