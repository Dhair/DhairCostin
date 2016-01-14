package com.dhair.costin.data.remote;

import org.json.JSONObject;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Creator: dengshengjin on 16/1/12 20:26
 * Email: deng.shengjin@zuimeia.com
 */
public interface WallpaperService {

    @GET("/wallpaper/category/1/")
    Observable<JSONObject> queryWallpaper(@Query("time") long startTime, @Query("page_size") int pageSize, @Query("tz") int timeZone);

}
