package com.jeongho.metarial.login.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jeongho.metarial.R;
import com.jeongho.metarial.login.presenter.ILoginPresenter;
import com.jeongho.metarial.login.presenter.LoginPresenterCompl;

/**
 * Created by Jeongho on 2016/6/21.
 */
public class LoginActivity extends Activity implements ILoginView, View.OnClickListener {

    private ILoginPresenter mILoginPresenter;
    private Button mLoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initData() {
        mILoginPresenter = new LoginPresenterCompl(this);
    }

    private void initView() {
        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    public void signInSuccess() {
        Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signInFail() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                mILoginPresenter.login("123", "123");
                break;
        }
    }
}
