package com.jeongho.metarial.login.view;

/**
 * Created by Jeongho on 2016/8/2.
 */
public interface LoginCallback {
    void loginSuccess(String token, String userAccount, String userPwd);

    void loginFailed(String error);
}
