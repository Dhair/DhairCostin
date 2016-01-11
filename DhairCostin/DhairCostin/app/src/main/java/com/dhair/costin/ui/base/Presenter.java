package com.dhair.costin.ui.base;

import android.os.Bundle;

/**
 * Creator: dengshengjin on 16/1/11 11:20
 * Email: deng.shengjin@zuimeia.com
 */
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView(boolean retainView);

    V getMvpView();

    boolean isViewAttached();

    void checkViewAttached();

    void onCreate(Bundle savedInstanceState);

    void onResume();

    void onStart();

    void onPause();

    void onStop();

    void onDestroy();

}
