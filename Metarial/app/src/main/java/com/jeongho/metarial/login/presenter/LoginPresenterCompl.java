package com.jeongho.metarial.login.presenter;

import android.util.Log;

import com.jeongho.metarial.QxbApplication;
import com.jeongho.metarial.bean.UserInfoBean;
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
        mUser = new User();
        mUser.checkLoginInfo(userName, pwd, this);
    }

    /**
     * 登录成功
     * @param token
     */
    @Override
    public void loginSuccess(String token) {
        Log.d("loginSuccess ---> ", token);
        try{
            SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(
                    QxbApplication.getContext(), SharedPreferencesUtil.USER_DATA);
            sharedPreferencesUtil.putString(SharedPreferencesUtil.TOKEN, token);
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
     * @param bean
     */
    @Override
    public void getInfoSuccess(UserInfoBean bean) {
        Log.d("getInfoSuccess", bean.toString());
        mILoginView.onLoginSuccess();
    }
}
