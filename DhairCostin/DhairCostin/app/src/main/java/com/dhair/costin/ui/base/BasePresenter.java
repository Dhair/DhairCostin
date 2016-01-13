package com.dhair.costin.ui.base;

import android.content.Context;

import com.dhair.costin.data.DataManager;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Creator: dengshengjin on 16/1/11 11:20
 * Email: deng.shengjin@zuimeia.com
 */
public abstract class BasePresenter<V extends MvpView> implements Presenter<V> {
    private V mMvpView;
    private Context mContext;

    private CompositeSubscription mCompositeSubscription;
    private DataManager mDataManager;

    public BasePresenter(Context context) {
        mContext = context;
        mCompositeSubscription = new CompositeSubscription();
    }

    public void setupDataManager(DataManager dataManager) {
        mDataManager = dataManager;
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

    @Override
    public void onDestroy() {
        mCompositeSubscription.unsubscribe();
        mCompositeSubscription.clear();
    }

    protected void bindSubscription(Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }

    protected void unBindSubscription(Subscription subscription) {
        mCompositeSubscription.remove(subscription);
    }

    protected DataManager getDataManager() {
        return mDataManager;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attach<MvpView> before requesting data to the Presenter");
        }
    }
}
