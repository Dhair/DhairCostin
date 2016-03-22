package com.dhair.costin.ui.home;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dhair.common.library.util.PhoneUtil;
import com.dhair.costin.IMyAidlInterface;
import com.dhair.costin.R;
import com.dhair.costin.application.CostinApplication;
import com.dhair.costin.data.model.UserModel;
import com.dhair.costin.injection.component.UserComponent;
import com.dhair.costin.service.AIDLService;
import com.dhair.costin.service.MessengerService;
import com.dhair.costin.ui.base.activity.BaseMvpActivity;
import com.dhair.costin.ui.home.presenter.HomePresenter;
import com.dhair.costin.ui.widget.touch.CustomView;
import com.dhair.costin.utils.exitapp.ExitAppHelper;
import com.orhanobut.logger.Logger;

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

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    @Bind(R.id.button)
    Button mButton;

    @Bind(R.id.custom_view)
    CustomView mCustomView;

    @Bind(R.id.test1)
    RelativeLayout mTest1;

    @Bind(R.id.test2)
    RelativeLayout mTest2;
    private ListPagerAdapter mPagerAdapter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExitAppHelper.registerReceiver();
        Logger.e("JobSchedulerService onCreate");
        memoryReference();

        noMemoryReference();
    }

    private void memoryReference() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    SystemClock.sleep(1000);
                }
            }
        }.start();
    }

    private ThreadTest mThreadTest;

    private static class ThreadTest extends Thread {
        @Override
        public void run() {
            super.run();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void noMemoryReference() {
        if (mThreadTest == null) {
            mThreadTest = new ThreadTest();
        }
        mThreadTest.start();
    }

    private void disableThreadMemoryReference() {
        mThreadTest.interrupt();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disableThreadMemoryReference();
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
        mPagerAdapter = new ListPagerAdapter(getSupportFragmentManager());
        getActivityComponent().inject(this);

        ClassLoader classLoader = getClassLoader();
        Logger.e("[onCreate] classLoader " + " : " + classLoader+","+Runtime.getRuntime().toString());
        if (classLoader != null) {
            Logger.e("[onCreate] classLoader " + " : " + classLoader.toString());
            while (classLoader.getParent() != null) {
                classLoader = classLoader.getParent();
                Logger.e("[onCreate] classLoader " + " : " + classLoader.toString());
            }
        }
    }

    @Override
    protected void initWidgets() {
        setSupportActionBar(mToolbar);//针对Theme 为AppTheme.NoAction :make the drawer layout under the actionbar
        updateActionBar();
        displayNavigationRoomView();

        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_layout_open, R.string.drawer_layout_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置TabLayout的Tab操作屏幕可滚动

        mButton.setOnClickListener(v -> {
//            mCustomView.requestLayout();
//            mCustomView.invalidate();
//            Logger.e("aild messenger=result onServiceConnected");
//            bindServiceByMessenger();

            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.splash_bg);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mTest1.addView(imageView, lp);
//            mTest2.addView(imageView, lp);
        });
    }

    private void bindServieByAidl() {
        Intent intent = new Intent(AIDLService.class.getName());
        intent.setPackage(getPackageName());
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                try {
                    int result = iMyAidlInterface.add(1, 2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

    private Messenger mMessenger;

    private void bindServiceByMessenger() {
        Intent intent = new Intent(MessengerService.class.getName());
        intent.setPackage(getPackageName());
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Logger.e("aild messenger=result onServiceConnected");
                mMessenger = new Messenger(service);

                Message messageClient = Message.obtain(null, 0);
                Messenger replyMessenger = new Messenger(new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case 1:
                                int result = msg.getData().getInt("result");
                                break;
                        }
                    }
                });
                Bundle bundle = new Bundle();
                bundle.putInt("first", 1);
                bundle.putInt("second", 2);
                messageClient.setData(bundle);
                messageClient.replyTo = replyMessenger;
                try {
                    mMessenger.send(messageClient);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_dialog) {
            Logger.e("JobSchedulerService onOptionsItemSelected");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class ListPagerAdapter extends FragmentStatePagerAdapter {
        private int mDefaultTab = 7;

        public ListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return HomeFragment.getInstance();
        }

        @Override
        public int getCount() {
            return mDefaultTab;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Title" + position;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Logger.e("JobSchedulerService onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putInt("sfdsf", 1);
    }
}
