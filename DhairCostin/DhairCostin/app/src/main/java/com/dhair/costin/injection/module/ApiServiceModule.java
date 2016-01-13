package com.dhair.costin.injection.module;

import android.app.Application;

import com.dhair.costin.data.remote.WallpaperService;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Creator: dengshengjin on 16/1/13 19:42
 * Email: deng.shengjin@zuimeia.com
 */
@Module
public class ApiServiceModule {
    private static final String ENDPOINT = "http://lab.zuimeia.com";

    @Provides
    @Singleton
    OkHttpClient providerOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.networkInterceptors().add(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("platform", "android")
                    .build();
            return chain.proceed(request);
        });
        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit providerRetrofit(Application application, OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return builder.build();
    }

    @Provides
    @Singleton
    WallpaperService providerService(Retrofit retrofit) {
        return retrofit.create(WallpaperService.class);
    }
}
