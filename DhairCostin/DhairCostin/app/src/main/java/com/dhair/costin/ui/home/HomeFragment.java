package com.dhair.costin.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dhair.costin.R;
import com.dhair.costin.ui.base.fragment.BaseMvpFragment;
import com.dhair.costin.ui.home.adapter.HomeListAdapter;
import com.dhair.costin.ui.home.presenter.HomeListPresenter;
import com.dhair.costin.ui.home.view.HomeListMvpView;

import butterknife.Bind;

/**
 * Creator: dengshengjin on 16/1/25 11:53
 * Email: deng.shengjin@zuimeia.com
 */
public class HomeFragment extends BaseMvpFragment<HomeListPresenter> implements HomeListMvpView {
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private HomeListAdapter mAdapter;

    public static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    protected HomeListPresenter createPresenter(Context context) {
        return new HomeListPresenter(context);
    }

    @Override
    protected void initData() {
        mAdapter = new HomeListAdapter(getContext());
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_list;
    }

    @Override
    protected void initViews(View contentView, Bundle savedInstanceState) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void initActions(View contentView) {

    }
}
