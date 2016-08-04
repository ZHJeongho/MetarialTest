package com.jeongho.metarial.login.model;

import com.google.gson.Gson;
import com.jeongho.metarial.Utils.ServerUtil;
import com.jeongho.metarial.bean.ResponseBean;
import com.jeongho.metarial.login.view.LoginCallback;

import okhttp3.Call;

/**
 * Created by Jeongho on 2016/6/22.
 */
public class User implements IUser {
    private String userCode;
    private String passwd;

    private ServerUtil mServerUtil;

    public User() {

    }

    public User(String userName, String userPwd) {
        userCode = userName;
        passwd = userPwd;
    }

    public String getUserName() {
        return userCode;
    }

    public void setUserName(String userName) {
        userCode = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }


    @Override
    public void checkLoginInfo(String name, String pwd, final LoginCallback callback) {

        User user = new User();
        user.userCode = name;
        user.passwd = pwd;

        ServerUtil.loginUser(user, new ServerUtil.OnStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.loginFailed(e.getMessage());
            }

            @Override
            public void onSuccess(String response, int id) {

                Gson gson = new Gson();
                ResponseBean bean = gson.fromJson(response, ResponseBean.class);
                switch (bean.result) {
                    case "300":
                        callback.loginFailed(bean.message);
                        break;
                    default:
                        callback.loginSuccess();
                        break;
                }
            }
        });
    }
}
