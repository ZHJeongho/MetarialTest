package com.jeongho.metarial.Utils;

import android.graphics.Bitmap;

import com.jeongho.qxblibrary.Utils.UrlUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Jeongho on 16/7/17.
 */
public class ServerUtil {

    public OnStringCallback mOnStringCallback;

    public void getHomeVpData(final OnStringCallback onStringCallback){
        OkHttpUtils
                .get()
                .url(UrlUtil.getHomeVpData())
                .build()
                .execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                onStringCallback.onError(call, e, id);
            }

            @Override
            public void onResponse(String response, int id) {
                onStringCallback.onSuccess(response, id);
            }
        });
    }

    public void getBitmap(String bitmapUrl, final OnBitmapCallback onBitmapCallback){
        OkHttpUtils
                .get()
                .url(bitmapUrl)
                .build()
                .execute(new BitmapCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        onBitmapCallback.onError(call, e, id);
                    }

                    @Override
                    public void onResponse(Bitmap response, int id) {
                        onBitmapCallback.onSuccess(response, id);
                    }
                });
    }

    public interface OnStringCallback {
        void onError(Call call, Exception e, int id);
        void onSuccess(String response, int id);
    }

    public interface OnBitmapCallback{
        void onError(Call call, Exception e, int id);
        void onSuccess(Bitmap response, int id);
    }
}
