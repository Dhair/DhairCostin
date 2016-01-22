package com.dhair.costin.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.widget.TextView;

import com.dhair.costin.R;
import com.dhair.costin.application.CostinApplication;
import com.dhair.costin.data.model.UserModel;
import com.dhair.costin.injection.component.UserComponent;
import com.dhair.costin.ui.base.activity.BaseMvpActivity;
import com.dhair.costin.utils.exitapp.ExitAppHelper;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Creator: dengshengjin on 16/1/11 10:30
 * Email: deng.shengjin@zuimeia.com
 */
public class HomeActivity extends BaseMvpActivity<HomePresenter> {

    @Inject
    ExitAppHelper mExitAppHelper;

    @Bind(R.id.retrofit)
    TextView mRetrofitText;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExitAppHelper.registerReceiver();
    }

    @NonNull
    @Override
    protected HomePresenter createPresenter(Context context) {
        return new HomePresenter(context);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initWidgets() {
        mRetrofitText.setOnClickListener(v -> getPresenter().queryWallpapers());
    }

    @Override
    protected void initActions() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: {
                CostinApplication costinApplication = CostinApplication.getApplication(getApplicationContext());
                UserComponent userComponent = costinApplication.getUserComponent();
                if (userComponent != null) {
                    UserModel userModel = userComponent.userModel();
                    if (userModel != null) {
                        costinApplication.releaseUserComponent();
                    }
                }
                ExitAppHelper.exitAppFinally(getApplicationContext());
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExitAppHelper.unregisterReceiver();
    }
}
