package com.dhair.costin.data.local;

import android.content.Context;

import com.dhair.costin.data.remote.WallpaperService;
import com.dhair.costin.injection.ApplicationContext;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

/**
 * Creator: dengshengjin on 16/1/10 18:26
 * Email: deng.shengjin@zuimeia.com
 */
public class DataManager {

    private Context mContext;
    private WallpaperService mWallpaperService;

    @Inject
    public DataManager(@ApplicationContext Context context, WallpaperService wallpaperService) {
        mContext = context;
        mWallpaperService = wallpaperService;
    }

    public void print(String s) {
        Logger.e("Splash Presenter print" + mWallpaperService+","+mContext.toString()+","+s);
    }
}
