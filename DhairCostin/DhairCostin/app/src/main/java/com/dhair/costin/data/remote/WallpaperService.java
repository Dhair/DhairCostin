package com.dhair.costin.data.remote;

import android.content.Context;

import com.dhair.costin.data.model.Wallpaper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Creator: dengshengjin on 16/1/12 20:26
 * Email: deng.shengjin@zuimeia.com
 */
public interface WallpaperService {
    String ENDPOINT = "http://lab.zuimeia.com";

    @GET("/wallpaper/category/1/")
    Observable<List<Wallpaper>> queryWallpaper(@Query("time") long startTime, @Query("page_size") int pageSize, @Query("tz") int timeZone);

    class Factory {
        public static WallpaperService makeService(Context context) {
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.networkInterceptors().add(chain -> {
                Request request = chain.request().newBuilder().addHeader("test", "test").build();
                return chain.proceed(request);
            });
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(WallpaperService.ENDPOINT)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(WallpaperService.class);
        }
    }

}
