package com.jeongho.metarial.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jeongho.metarial.R;
import com.jeongho.metarial.Utils.TextFontUtil;

/**
 * Created by Jeongho on 2016/7/22.
 */
public class RideMeetDetailsActivity extends AppCompatActivity{

    private TextView mLeaderNameTv;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_meet);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(mToolbar);

        mLeaderNameTv = (TextView) findViewById(R.id.tv_leader_name);
        mLeaderNameTv.setTypeface(TextFontUtil.get(this).getMedium());
        mLeaderNameTv.setText("ZhangQinJie");
    }

    public static void startAcition(Context context) {
        Intent intent = new Intent(context, RideMeetDetailsActivity.class);
        context.startActivity(intent);
    }
}
