package com.dhair.costin.ui.home;

import android.content.Context;
import android.os.Bundle;

import com.dhair.costin.data.model.Wallpaper;
import com.dhair.costin.ui.base.BasePresenter;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Creator: dengshengjin on 16/1/11 10:31
 * Email: deng.shengjin@zuimeia.com
 */
public class HomePresenter extends BasePresenter<HomeMvpView> {

    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    public void queryWallpapers() {
        Subscription subscription = getDataManager().queryWallpapers(0, 1, 30)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Wallpaper>>() {
                    @Override
                    public void onCompleted() {
                        Logger.e("queryWallpapers complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("queryWallpapers onError" + e.toString());
                    }

                    @Override
                    public void onNext(List<Wallpaper> wallpapers) {

                    }
                });
        bindSubscription(subscription);
    }
}
