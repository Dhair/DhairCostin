package com.dhair.costin.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.dhair.common.library.util.PhoneUtil;
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

    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

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
        setSupportActionBar(mToolbar);//针对Theme 为AppTheme.NoAction :make the drawer layout under the actionbar
        updateActionBar();
        displayNavigationRoomView();

        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_layout_open, R.string.drawer_layout_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }

    private void updateActionBar() {
        //针对Theme 为AppTheme
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {

        }
        actionBar.setHomeButtonEnabled(true);//actionbar 左上角图标是否可以点击
        actionBar.setDisplayHomeAsUpEnabled(true);//左上角左边是否加上一个返回按钮
        actionBar.setDisplayShowHomeEnabled(false);//左上角是否展示应用程序图标,false则只会显示app name
        actionBar.setIcon(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_launcher));
    }

    private void displayNavigationRoomView() {
        mNavigationView.inflateHeaderView(R.layout.navigation_header);
        mNavigationView.inflateMenu(R.menu.navigation_menu);
        if (getResources().getBoolean(R.bool.navigation_under_status_bar)) {
            ViewGroup mSpaceRoomBox = (ViewGroup) mNavigationView.getHeaderView(0).findViewById(R.id.navigation_space_room);
            if (mSpaceRoomBox != null) {
                ViewGroup.LayoutParams lp = mSpaceRoomBox.getLayoutParams();
                lp.height = PhoneUtil.getStatusBarHeight(getApplicationContext());
                mSpaceRoomBox.requestLayout();
            }
            mSpaceRoomBox.setVisibility(View.VISIBLE);
        } else {
            mNavigationView.getHeaderView(0).findViewById(R.id.navigation_space_room).setVisibility(View.GONE);
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExitAppHelper.unregisterReceiver();
    }
}
