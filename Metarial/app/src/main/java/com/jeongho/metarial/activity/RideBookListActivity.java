package com.jeongho.metarial.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jeongho.metarial.R;

/**
 * 路书列表界面
 * 默认为用户分享路书列表
 * 点击按钮后  出现自己的路书
 * Created by Jeongho on 16/8/18.
 */
public class RideBookListActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_book_list);

    }


    public static void startAction(Context context) {

    }
}
