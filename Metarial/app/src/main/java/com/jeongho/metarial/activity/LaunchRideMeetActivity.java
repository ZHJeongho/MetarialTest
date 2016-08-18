package com.jeongho.metarial.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jeongho.metarial.R;

/**
 * 发起约行界面
 * Created by Jeongho on 2016/7/22.
 */
public class LaunchRideMeetActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_ride_meet);
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, LaunchRideMeetActivity.class);
        context.startActivity(intent);
    }
}
