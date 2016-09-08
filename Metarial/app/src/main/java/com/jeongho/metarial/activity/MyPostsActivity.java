package com.jeongho.metarial.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jeongho.metarial.R;

/**
 * Created by Jeongho on 2016/9/8.
 */
public class MyPostsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, MyPostsActivity.class);
        context.startActivity(intent);
    }
}
