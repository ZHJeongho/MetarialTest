package com.jeongho.metarial.login.presenter;

import com.jeongho.metarial.login.model.IUser;
import com.jeongho.metarial.login.model.User;
import com.jeongho.metarial.login.view.ILoginView;
import com.jeongho.metarial.login.view.LoginCallback;

/**
 * Created by Jeongho on 2016/6/21.
 */
public class LoginPresenterCompl implements ILoginPresenter{

    private IUser mUser;
    private ILoginView mILoginView;

    public LoginPresenterCompl(ILoginView ILoginView) {
        mILoginView = ILoginView;
    }

    @Override
    public void login(String userName, String pwd) {
        mUser = new User();
//        if (mUser.checkLoginInfo()){
//            mILoginView.onLoginResult(true);
//        }else {
//            mILoginView.onLoginResult(false);
//        }

        mUser.checkLoginInfo(userName, pwd, new LoginCallback() {
            @Override
            public void loginSuccess() {
                //TODO:保存Token
                mILoginView.onLoginSuccess();
            }

            @Override
            public void loginFailed(String message) {
                mILoginView.onLoginFailed(message);
            }
        });
    }

}
