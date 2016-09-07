package com.jeongho.metarial.login.presenter;

import android.util.Log;

import com.jeongho.metarial.common.QxbApplication;
import com.jeongho.metarial.login.model.IUser;
import com.jeongho.metarial.login.model.User;
import com.jeongho.metarial.login.view.GetUserInfoCallback;
import com.jeongho.metarial.login.view.ILoginView;
import com.jeongho.metarial.login.view.LoginCallback;
import com.jeongho.qxblibrary.Utils.SharedPreferencesUtil;

/**
 * Created by Jeongho on 2016/6/21.
 */
public class LoginPresenterCompl implements ILoginPresenter, LoginCallback, GetUserInfoCallback {

    private IUser mUser;
    private ILoginView mILoginView;

    public LoginPresenterCompl(ILoginView ILoginView) {
        mILoginView = ILoginView;
    }

    @Override
    public void login(String userName, String pwd) {
        mUser = new User(userName, pwd);
        mUser.checkLoginInfo(this);
    }

    /**
     * 登录成功
     * @param token
     * @param userAccount
     * @param userPwd
     */
    @Override
    public void loginSuccess(String token, String userAccount, String userPwd) {
        Log.d("loginSuccess ---> ", token);
        try{
            SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(
                    QxbApplication.getInstance(), SharedPreferencesUtil.USER_DATA);
            sharedPreferencesUtil.putString(SharedPreferencesUtil.TOKEN, token);
            //TODO:加密
            sharedPreferencesUtil.putString(SharedPreferencesUtil.USER_ACCOUNT, userAccount);
            sharedPreferencesUtil.putString(SharedPreferencesUtil.USER_PASSWORD, userPwd);

        }catch (Exception e){
            e.printStackTrace();
        }
        //获取用户信息
        mUser.getUserInfo(this);
    }

    /**
     * 登录失败
     * @param message
     */
    @Override
    public void loginFailed(String message) {
        mILoginView.onLoginFailed(message);
    }
    /**
     * 登录成功后，获取到用户信息后再切换Aty
     * @param userInfo
     */
    @Override
    public void getInfoSuccess(String userInfo) {
        Log.d("getInfoSuccess", userInfo);
        try{
            SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(
                    QxbApplication.getInstance(), SharedPreferencesUtil.USER_DATA);
            sharedPreferencesUtil.putString(SharedPreferencesUtil.USER_INFO, userInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        mILoginView.onLoginSuccess();
    }

//    @Override
//    public void getInfoSuccess(UserInfoBean bean) {
//        Log.d("getInfoSuccess", bean.toString());
//        mILoginView.onLoginSuccess();
//    }
}
