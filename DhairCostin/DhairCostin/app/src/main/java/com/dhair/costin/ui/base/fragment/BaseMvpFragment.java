package com.dhair.costin.ui.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhair.costin.ui.base.MvpView;
import com.dhair.costin.ui.base.Presenter;

import butterknife.ButterKnife;

/**
 * Creator: dengshengjin on 16/1/13 14:59
 * Email: deng.shengjin@zuimeia.com
 */
public abstract class BaseMvpFragment<P extends Presenter> extends BaseDaggerFragment implements MvpView {
    private P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = createPresenter(getActivity().getApplicationContext());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View contentView = inflater.inflate(getContentView(), null);
        ButterKnife.bind(this, contentView);
        initData();
        initViews(contentView, savedInstanceState);
        initActions(contentView);

        return contentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Nullable
    protected abstract P createPresenter(Context context);

    protected P getPresenter() {
        return mPresenter;
    }

    protected abstract void initData();

    @LayoutRes
    protected abstract int getContentView();

    protected abstract void initViews(View contentView, Bundle savedInstanceState);

    protected abstract void initActions(View contentView);
}
