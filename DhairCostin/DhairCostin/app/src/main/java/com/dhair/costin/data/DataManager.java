package com.dhair.costin.data;

import android.content.Context;

import com.dhair.costin.data.remote.WallpaperService;
import com.dhair.costin.injection.context.ApplicationContext;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Creator: dengshengjin on 16/1/10 18:26
 * Email: deng.shengjin@zuimeia.com
 */
@Singleton
public class DataManager {

    private Context mContext;
    private WallpaperService mWallpaperService;

    @Inject
    public DataManager(@ApplicationContext Context context, WallpaperService wallpaperService) {
        mContext = context;
        mWallpaperService = wallpaperService;
    }

    public Observable<JSONObject> queryWallpapers(long startTime, int page, int pageSize) {
        return mWallpaperService.queryWallpaper(startTime, page, pageSize);
    }
}
