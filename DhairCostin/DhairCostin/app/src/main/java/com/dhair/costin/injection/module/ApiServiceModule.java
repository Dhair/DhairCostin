package com.dhair.costin.injection.module;

import android.app.Application;
import android.content.Context;

import com.dhair.costin.data.remote.WallpaperService;
import com.dhair.costin.data.remote.converter.JsonConverterFactory;
import com.dhair.costin.injection.context.ApplicationContext;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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
    OkHttpClient providerOkHttpClient(@ApplicationContext Context context) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        okHttpClient.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                addHeaders(builder);
                Request request = builder.build();
                return chain.proceed(request);
            }

            private void addHeaders(Request.Builder builder) {
                builder.addHeader("platform", "android");
            }

        });
        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit providerRetrofit(Application application, OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return builder.build();
    }

    @Provides
    @Singleton
    WallpaperService providerService(Retrofit retrofit) {
        return retrofit.create(WallpaperService.class);
    }
}
