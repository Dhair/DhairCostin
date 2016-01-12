package com.dhair.costin.ui.base;

import android.content.Context;

/**
 * Creator: dengshengjin on 16/1/11 11:20
 * Email: deng.shengjin@zuimeia.com
 */
public abstract class BasePresenter<V extends MvpView> implements Presenter<V> {
    private V mMvpView;
    private Context mContext;

    public BasePresenter(Context context) {
        mContext = context;
    }

    @Override
    public void attachView(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView(boolean retainView) {
        mMvpView = null;
    }

    @Override
    public V getMvpView() {
        return mMvpView;
    }

    @Override
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    @Override
    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attach<MvpView> before requesting data to the Presenter");
        }
    }
}
