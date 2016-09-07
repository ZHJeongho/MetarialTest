package com.jeongho.metarial.login.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jeongho.metarial.R;
import com.jeongho.metarial.activity.MainActivity;
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

        switch (v.getId()){
            case R.id.fab_login:
                String userAccount = mUserNameTil.getEditText().getText().toString();
                String userPwd = mUserPwdTil.getEditText().getText().toString();
                mILoginPresenter.login(userAccount, userPwd);
                break;
        }
    }

    @Override
    public void onLoginSuccess() {
        Intent intent = getIntent();
        int requestCode = intent.getIntExtra("request_code", 0x01);
        switch (requestCode){
            case 0x01:
                setResult(MainActivity.RESULT_LOGIN);
                break;
            case 0x02:
                setResult(MainActivity.RESULT_COLLECT);
                break;
            case 0x03:
                setResult(MainActivity.RESULT_ATTENTION);
                break;
            case 0x04:
                setResult(MainActivity.RESULT_POSTS);
                break;
        }
        this.finish();
    }

    @Override
    public void onLoginFailed(String error) {
        //登录失败不返回Main
        SnackUtil.createShortSnackbar(mRootRl, error, SnackUtil.ALERT).show();
        //setResult(MainActivity.RESULT_LOGIN);
        //this.finish();
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
