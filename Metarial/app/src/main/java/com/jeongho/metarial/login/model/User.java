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
        mServerUtil.loginUser(this, new ServerUtil.OnStringCallback2() {
            @Override
            public boolean onError(Call call, Exception e, int id) {
                return false;
            }

            @Override
            public boolean onSuccess(String response, int id) {

                return true;
            }
        });
        return false;
    }
}
