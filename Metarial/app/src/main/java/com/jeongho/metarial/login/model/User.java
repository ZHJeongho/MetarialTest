package com.jeongho.metarial.login.model;

/**
 * Created by Jeongho on 2016/6/22.
 */
public class User implements IUser{
    private String mUserName;
    private String mUserPwd;

    public User() {

    }

    public User(String userName, String userPwd) {
        mUserName = userName;
        mUserPwd = userPwd;
    }

    @Override
    public boolean checkLoginInfo() {
        if (mUserName.equals("123") && mUserPwd.equals("123")){
            return true;
        }
        return false;
    }
}
