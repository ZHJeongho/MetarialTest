package com.jeongho.metarial.login.presenter;

import com.jeongho.metarial.login.model.IUser;
import com.jeongho.metarial.login.model.User;
import com.jeongho.metarial.login.view.ILoginView;

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
        mUser = new User(userName, pwd);
//        if (mUser.checkLoginInfo()){
//            mILoginView.onLoginResult(true);
//        }else {
//            mILoginView.onLoginResult(false);
//        }

        mUser.checkLoginInfo();
    }

}
