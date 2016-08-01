package com.jeongho.metarial.login.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jeongho.metarial.R;
import com.jeongho.metarial.login.presenter.ILoginPresenter;
import com.jeongho.metarial.login.presenter.LoginPresenterCompl;
import com.jeongho.metarial.widge.SnackUtil;

/**
 * Created by Jeongho on 2016/6/21.
 */
public class LoginActivity extends Activity implements ILoginView, View.OnClickListener {

    private ILoginPresenter mILoginPresenter;

    private FloatingActionButton mLoginBtn;
    private Button mRegisterBtn;
    private TextInputLayout mUserNameTil;
    private TextInputLayout mUserPwdTil;
    private RelativeLayout mRootRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initView();
        initData();
    }

    private void initView() {
        mLoginBtn = (FloatingActionButton) findViewById(R.id.fab_login);
        mLoginBtn.setOnClickListener(this);

        mRegisterBtn = (Button) findViewById(R.id.btn_register);
        mRegisterBtn.setOnClickListener(this);

        mUserNameTil = (TextInputLayout) findViewById(R.id.til_user_name);
        mUserPwdTil = (TextInputLayout) findViewById(R.id.til_user_pwd);

        mRootRl = (RelativeLayout) findViewById(R.id.rl_root);
    }

    private void initData() {
        mILoginPresenter = new LoginPresenterCompl(this);
    }


    @Override
    public void onClick(View v) {
        SnackUtil.createShortSnackbar(mRootRl, R.string.user_login_error, SnackUtil.ALERT).show();
        switch (v.getId()){
            case R.id.btn_login:
                String userName = mUserNameTil.getEditText().getText().toString();
                String userPwd = mUserPwdTil.getEditText().getText().toString();
                mILoginPresenter.login(userName, userPwd);
                break;
        }
    }

    @Override
    public void onLoginResult(Boolean result) {
        if (result){
            //登陆成功

        }else {
            //登陆失败
            mUserPwdTil.setError(getResources().getString(R.string.user_login_error));
        }
    }
}
