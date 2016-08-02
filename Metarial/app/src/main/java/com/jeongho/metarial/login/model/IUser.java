package com.jeongho.metarial.login.model;

import com.jeongho.metarial.login.view.LoginCallback;

/**
 * Created by Jeongho on 2016/6/22.
 */
public interface IUser {
    void checkLoginInfo(String name, String pwd, LoginCallback callback);
}
