package com.jeongho.qxblibrary.Utils;

/**
 * Created by Jeongho on 2016/7/7.
 */
public class UrlUtil {
    public static final String ADDRESS = "http://139.129.117.90/qxb_back/";

    public static String resigterUserUrl(){
        return ADDRESS + "user/register";
    }

    public static String getUserDetails(){
        return ADDRESS + "/user/getDetail";
    }

    public static String getHomeVpData(){
        return ADDRESS + "json/homepage.json";
    }

    public static String loginUser() {
        return ADDRESS + "user/login";
    }

    public static String getUserDetail() {
        return ADDRESS + "user/getDetail";
    }

    //POST
    public static String updateUserDetail(){
        return ADDRESS + "user/update";
    }
    //Content-type=multipart/form-data
    public static String updatePortrait(){
        return ADDRESS + "user/icon";
    }

    public static String getBicycleList(){
        return ADDRESS + "/bike/list";
    }
}
