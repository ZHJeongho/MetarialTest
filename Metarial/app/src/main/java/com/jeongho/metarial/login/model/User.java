package com.jeongho.metarial.login.model;

import com.jeongho.metarial.Utils.ServerUtil;

import okhttp3.Call;

/**
 * Created by Jeongho on 2016/6/22.
 */
public class User implements IUser{
    private String mUserName;
    private String mUserPwd;
    private ServerUtil mServerUtil;

    public User() {

    }

    public User(String userName, String userPwd) {
        mUserName = userName;
        mUserPwd = userPwd;
        mServerUtil = new ServerUtil();
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getUserPwd() {
        return mUserPwd;
    }

    public void setUserPwd(String userPwd) {
        mUserPwd = userPwd;
    }

    @Override
    public boolean checkLoginInfo() {
        mServerUtil.loginUser(this, new ServerUtil.OnStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                //checkLogin.onError(call, e, id);
            }

            @Override
            public void onSuccess(String response, int id) {
                //checkLogin.onSuccess(response, id);
            }
        });
        return false;
    }

//    public interface ICheckLogin{
//        void onError(Call call, Exception e, int id);
//        void onSuccess(String response, int id);
//    }
}
