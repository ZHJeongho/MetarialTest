package com.jeongho.metarial.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.jeongho.metarial.R;

/**
 * 发现详情界面
 * Created by Jeongho on 2016/8/2.
 */
public class NewsDetailsAty extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_news_details);
    }
}
