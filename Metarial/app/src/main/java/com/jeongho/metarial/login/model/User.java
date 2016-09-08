package com.jeongho.metarial.login.model;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.jeongho.metarial.Utils.ServerUtil;
import com.jeongho.metarial.bean.ResponseBean;
import com.jeongho.metarial.common.QxbApplication;
import com.jeongho.metarial.login.view.GetUserInfoCallback;
import com.jeongho.metarial.login.view.LoginCallback;
import com.jeongho.qxblibrary.Utils.SharedPreferencesUtil;

import okhttp3.Call;

/**
 * Created by Jeongho on 2016/6/22.
 */
public class User implements IUser {
    private String userCode;
    private String passwd;

    private ServerUtil mServerUtil;

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
    public void checkLoginInfo(final LoginCallback callback) {

        //用户名和密码都不能为空
        if (TextUtils.isEmpty(userCode) || TextUtils.isEmpty(passwd)){
            return;
        }
//        user.userCode = userCode;
//        try {
//            //加密
//            user.passwd = SecurityUtil.encrypt(pwd);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Log.d("userName", this.userCode);
        Log.d("pwd",this.passwd);
        ServerUtil.loginUser(this, new ServerUtil.OnStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.loginFailed(e.getMessage());
            }

            @Override
            public void onSuccess(String response, int id) {
                Log.d("response", response);
                Gson gson = new Gson();
                ResponseBean bean = gson.fromJson(response, ResponseBean.class);
                switch (bean.result) {
                    case "300":
                        callback.loginFailed(bean.message);
                        break;
                    default:
                        callback.loginSuccess(bean.token, userCode, passwd);
                        break;
                }
            }
        });
    }

    @Override
    public void getUserInfo(final GetUserInfoCallback callback) {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(
                QxbApplication.getInstance(), SharedPreferencesUtil.USER_DATA);
        String token = sharedPreferencesUtil.getString(SharedPreferencesUtil.TOKEN, "");
        ServerUtil.getUserDetail(token, new ServerUtil.OnStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onSuccess(String response, int id) {
                callback.getInfoSuccess(response);
            }
        });
    }
}
