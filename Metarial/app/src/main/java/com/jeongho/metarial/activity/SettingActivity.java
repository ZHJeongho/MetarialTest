package com.jeongho.metarial.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jeongho.metarial.BaseActivity;
import com.jeongho.metarial.R;

/**
 * Created by Jeongho on 2016/9/8.
 */
public class SettingActivity extends BaseActivity{
    private Toolbar mToolbar;


    @Override
    public void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.nav_setting);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        setSupportActionBar(mToolbar);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.startAction(SettingActivity.this);
            }
        });
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_setting;
    }


    public static void startAction(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }
}
