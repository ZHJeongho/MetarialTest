package com.jeongho.metarial.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.jeongho.metarial.BaseActivity;
import com.jeongho.metarial.R;

/**
 * Created by Jeongho on 2016/9/8.
 */
public class MyCollectActivity extends BaseActivity{

    private Toolbar mToolbar;


    @Override
    public void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.nav_collect);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_collect;
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, MyCollectActivity.class);
        context.startActivity(intent);
    }
}
