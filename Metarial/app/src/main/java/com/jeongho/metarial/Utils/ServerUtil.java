package com.jeongho.metarial.Utils;

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.jeongho.metarial.login.model.User;
import com.jeongho.qxblibrary.Utils.UrlUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Jeongho on 16/7/17.
 */
public class ServerUtil {

    public OnStringCallback mOnStringCallback;

    private final static String HEADER_AUTH= "authorization";

    /**
     * 获取首页轮播内容
     * @param onStringCallback
     */
    public static void getHomeVpData(final OnStringCallback onStringCallback){
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
    public static void getBitmap(String bitmapUrl, final OnBitmapCallback onBitmapCallback){
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
    public static void loginUser(User user, final OnStringCallback onStringCallback){
        try {
            OkHttpUtils
                    .postString()
//                    .post()
                    .url(UrlUtil.loginUser())
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .content(new Gson().toJson(user))
//                    .addParams("userCode", user.getUserName())
//                    .addParams("passwd", SecurityUtil.encrypt(user.getPasswd()))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            onStringCallback.onError(call, e, id);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            onStringCallback.onSuccess(response, id);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getBicycleList(String startIndex, final OnStringCallback onStringCallback){
        OkHttpUtils
                .postString()
                .url(UrlUtil.getBicycleList())
                .content(new Gson().toJson(new TestID(startIndex)))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
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

    public static void getUserDetail(String token, final OnStringCallback onStringCallback){

//        SharedPreferencesUtil preferencesUtil = new SharedPreferencesUtil(
//                QxbApplication.getContext(), SharedPreferencesUtil.USER_DATA);
//        String token = preferencesUtil.getString(SharedPreferencesUtil.TOKEN, "");

        OkHttpUtils.post()
                .url(UrlUtil.getUserDetail())
                .addHeader(HEADER_AUTH, token)
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

    public interface OnStringCallback2{
        boolean onError(Call call, Exception e, int id);
        boolean onSuccess(String response, int id);
    }
}
