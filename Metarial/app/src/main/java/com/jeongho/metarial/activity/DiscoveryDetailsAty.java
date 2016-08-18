package com.jeongho.metarial.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jeongho.metarial.R;

/**
 * 发现详情界面
 * Created by Jeongho on 2016/7/9.
 */
public class DiscoveryDetailsAty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int tag = intent.getIntExtra("TAG", 0);
        switch (tag){
            case 1:
                setContentView(R.layout.fragment_attention);
                break;
            case 2:
                setContentView(R.layout.fragment_collect);
                break;
        }
    }
}
