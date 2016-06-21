package com.jeongho.metarial.login.presenter;

import com.jeongho.metarial.login.view.ILoginView;

/**
 * Created by Jeongho on 2016/6/21.
 */
public class LoginPresenterCompl implements ILoginPresenter{

    private ILoginView mILoginView;

    public LoginPresenterCompl(ILoginView ILoginView) {
        mILoginView = ILoginView;
    }

    @Override
    public void login(String userName, String pwd) {
        if (userName.equals("123") && pwd.equals("123")){
            mILoginView.onLoginResult(true);
        }else {
            mILoginView.onLoginResult(false);
        }
    }
}
