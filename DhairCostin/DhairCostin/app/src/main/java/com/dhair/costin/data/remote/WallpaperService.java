package com.dhair.costin.data.remote;

import com.dhair.costin.data.model.Wallpaper;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Creator: dengshengjin on 16/1/12 20:26
 * Email: deng.shengjin@zuimeia.com
 */
public interface WallpaperService {

    @GET("/wallpaper/category/1/")
    Observable<List<Wallpaper>> queryWallpaper(@Query("time") long startTime, @Query("page_size") int pageSize, @Query("tz") int timeZone);

}
