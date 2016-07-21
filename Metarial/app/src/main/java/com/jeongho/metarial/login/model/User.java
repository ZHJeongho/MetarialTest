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
    private ICheckLogin mICheckLogin;

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
    public void checkLoginInfo() {
        mServerUtil.loginUser(this, new ServerUtil.OnStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mICheckLogin.onError(call, e, id);
            }

            @Override
            public void onSuccess(String response, int id) {
                mICheckLogin.onSuccess(response, id);
            }
        });
    }

    public void setICheckLogin(ICheckLogin checkLogin){
        mICheckLogin = checkLogin;
    }

    public interface ICheckLogin{
        void onError(Call call, Exception e, int id);
        void onSuccess(String response, int id);
    }
}
