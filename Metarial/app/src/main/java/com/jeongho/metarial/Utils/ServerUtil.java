package com.jeongho.metarial.Utils;

import android.graphics.Bitmap;

import com.jeongho.metarial.login.model.User;
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

    /**
     * 获取首页轮播内容
     * @param onStringCallback
     */
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

    /**
     * 获取网络图片
     * @param bitmapUrl
     * @param onBitmapCallback
     */
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

    /**
     * 用户登录
     * @param user
     * @param onStringCallback
     */
    public void loginUser(User user, final OnStringCallback onStringCallback){
        OkHttpUtils
                .post()
                .url(UrlUtil.loginUser())
                .addParams("phone", user.getUserName())
                .addParams("passwd", user.getUserPwd())
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



    public interface OnStringCallback {
        void onError(Call call, Exception e, int id);
        void onSuccess(String response, int id);
    }

    public interface OnBitmapCallback{
        void onError(Call call, Exception e, int id);
        void onSuccess(Bitmap response, int id);
    }
}
