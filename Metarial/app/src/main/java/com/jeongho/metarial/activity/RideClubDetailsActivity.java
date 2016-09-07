package com.jeongho.metarial.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jeongho.metarial.R;

/**
 * 骑行俱乐部详细界面
 * Created by Jeongho on 16/8/18.
 */
public class RideClubDetailsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_club_details);
    }

    public static void startAction(Context context) {

    }
}
