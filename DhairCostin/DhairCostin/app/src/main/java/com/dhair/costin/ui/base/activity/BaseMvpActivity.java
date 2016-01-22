package com.dhair.costin.ui.base.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.dhair.common.library.util.PhoneUtil;
import com.dhair.costin.R;
import com.dhair.costin.data.DataManager;
import com.dhair.costin.ui.base.BasePresenter;
import com.dhair.costin.ui.base.MvpView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Creator: dengshengjin on 16/1/11 11:20
 * Email: deng.shengjin@zuimeia.com
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseDaggerActivity implements MvpView, IStatusBar {
    private P mPresenter;
    @Inject
    DataManager mDataManager;

    @Bind(R.id.status_bar_box)
    ViewGroup mStatusBarBox;

    FrameLayout mAbsBox;

    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = createPresenter(getApplicationContext());
        }
        if (getPresenter() != null) {
            getPresenter().attachView(this);
        }
        setContentView(R.layout.activity_abs);
        initAbsData();
        initAbsWidgets();
        ButterKnife.bind(this);
        initData();
        initWidgets();
        initActions();

        if (getPresenter() != null) {
            getPresenter().setupDataManager(mDataManager);
        }
    }

    private void initAbsData() {
        mInflater = LayoutInflater.from(getApplicationContext());
    }

    private void initAbsWidgets() {
        updateStatusBarHeightV19();
        updateActionBarColorV19();
        updateStatusBarColorV21();
        updateAbsContentView();
    }

    private void updateStatusBarHeightV19() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT && !PhoneUtil.isFullScreen(this)) {
            ViewGroup.LayoutParams lp = mStatusBarBox.getLayoutParams();
            lp.height = PhoneUtil.getStatusBarHeight(getApplicationContext());
            mStatusBarBox.requestLayout();
        }
    }

    private void updateActionBarColorV19() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)));
            }
        }
    }

    private void updateStatusBarColorV21() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && getStatusBarColor() > 0) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getStatusBarColor());
        }
    }

    private void updateAbsContentView() {
        mAbsBox = (FrameLayout) findViewById(R.id.abs_box);
        mAbsBox.addView(mInflater.inflate(getContentView(), mAbsBox, false));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPresenter() != null) {
            getPresenter().onResume();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getPresenter() != null) {
            getPresenter().onStart();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (getPresenter() != null) {
            getPresenter().onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().onDestroy();
            getPresenter().detachView(!isFinishing());
        }
        ButterKnife.unbind(this);
    }

    @Nullable //annotation 方便IDE提示
    protected abstract P createPresenter(Context context);

    protected P getPresenter() {
        return mPresenter;
    }

    @LayoutRes
    protected abstract int getContentView();

    protected abstract void initData();

    protected abstract void initWidgets();

    protected abstract void initActions();

    @Override
    public int getStatusBarColor() {
        return 0;
    }

}
