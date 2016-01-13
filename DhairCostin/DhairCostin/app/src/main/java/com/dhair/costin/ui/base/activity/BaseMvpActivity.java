package com.dhair.costin.ui.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.dhair.costin.ui.base.BasePresenter;
import com.dhair.costin.ui.base.MvpView;

import butterknife.ButterKnife;

/**
 * Creator: dengshengjin on 16/1/11 11:20
 * Email: deng.shengjin@zuimeia.com
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseDaggerActivity implements MvpView {
    private P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = createPresenter(getApplicationContext());
        }
        if (getPresenter() != null) {
            getPresenter().attachView(this);
        }
        setContentView(getContentView());
        ButterKnife.bind(this);
        initData();
        initWidgets();
        initActions();
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
}
