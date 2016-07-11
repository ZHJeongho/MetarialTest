package com.jeongho.metarial;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Jeongho on 2016/7/7.
 */
public class QxbApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("OKHTTP"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();

        OkHttpUtils.initClient(okHttpClient);
        //初始化百度地图
        SDKInitializer.initialize(getApplicationContext());
    }
}
