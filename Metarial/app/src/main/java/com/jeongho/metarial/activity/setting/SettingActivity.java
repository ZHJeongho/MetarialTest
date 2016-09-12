package com.jeongho.metarial.activity.setting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.jeongho.metarial.BaseActivity;
import com.jeongho.metarial.R;
import com.jeongho.metarial.activity.MainActivity;

/**
 * Created by Jeongho on 2016/9/8.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    private RelativeLayout mMessagePushRl;
    private RelativeLayout mCallbackRl;
    private RelativeLayout mCleanCacheRl;
    private RelativeLayout mVersionUpdateRl;
    private RelativeLayout mAboutQxbRl;

    @Override
    public void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.nav_setting);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(mToolbar);

        mMessagePushRl = (RelativeLayout) findViewById(R.id.rl_message_push);
        mCallbackRl = (RelativeLayout) findViewById(R.id.rl_callback);
        mCleanCacheRl = (RelativeLayout) findViewById(R.id.rl_clean_cache);
        mVersionUpdateRl = (RelativeLayout) findViewById(R.id.version_update);
        mAboutQxbRl = (RelativeLayout) findViewById(R.id.rl_about_qxb);
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

        mMessagePushRl.setOnClickListener(this);
        mCallbackRl.setOnClickListener(this);
        mCleanCacheRl.setOnClickListener(this);
        mVersionUpdateRl.setOnClickListener(this);
        mAboutQxbRl.setOnClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_setting;
    }


    public static void startAction(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_message_push:
                break;
            case R.id.rl_callback:
                break;
            case R.id.rl_clean_cache:
                break;
            case R.id.version_update:
                break;
            case R.id.rl_about_qxb:
                break;

        }
    }
}
