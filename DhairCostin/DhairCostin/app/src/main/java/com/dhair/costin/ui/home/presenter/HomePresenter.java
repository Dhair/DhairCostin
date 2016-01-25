package com.dhair.costin.ui.home.presenter;

import android.content.Context;
import android.os.Bundle;

import com.dhair.costin.data.model.Wallpaper;
import com.dhair.costin.ui.base.BasePresenter;
import com.dhair.costin.ui.home.view.HomeMvpView;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Subscriber;
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
        //http://stackoverflow.com/questions/22240406/rxjava-how-to-compose-multiple-observables-with-dependencies-and-collect-all-re
        Subscription subscription = getDataManager().queryWallpapers(0, 30, 8)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(jsonObject -> {
                    Logger.e("Observable doOnNext");
                })
                .map(jsonObject -> Wallpaper.parse(jsonObject.optJSONArray("images")))
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Wallpaper>>() {
                    @Override
                    public void onCompleted() {
                        Logger.e("Observable onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Wallpaper> wallpapers) {
                        Logger.e("Observable onNext");
                    }
                });
        bindSubscription(subscription);
    }
}
