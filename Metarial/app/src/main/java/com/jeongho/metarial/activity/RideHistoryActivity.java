package com.jeongho.metarial.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jeongho.metarial.R;

/**
 * 个人骑行历史界面
 * 可以显示个人的骑行记录
 * Created by Jeongho on 16/8/18.
 */
public class RideHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_history);
    }

    public static void startAction(Context context) {

    }
}
