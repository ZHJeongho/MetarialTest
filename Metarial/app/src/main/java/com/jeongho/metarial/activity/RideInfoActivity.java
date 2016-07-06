package com.jeongho.metarial.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jeongho.metarial.R;
import com.jeongho.metarial.service.RideService;

/**
 * Created by Jeongho on 16/7/5.
 */
public class RideInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mShowTraceBtn;
    private Button mStartRideBtn;
    private Button mStopRideBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_info);

        mShowTraceBtn = (Button) findViewById(R.id.btn_show_trace);
        mShowTraceBtn.setOnClickListener(this);
        mStartRideBtn = (Button) findViewById(R.id.btn_start_ride);
        mStartRideBtn.setOnClickListener(this);
        mStopRideBtn = (Button) findViewById(R.id.btn_stop_ride);
        mStopRideBtn.setOnClickListener(this);

    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, RideInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_show_trace:
                RideTraceActivity.startAction(this);
                break;
            case R.id.btn_start_ride:
                Intent intent = new Intent(this, RideService.class);
                startService(intent);
                break;
            case R.id.btn_stop_ride:
                Intent intent1 = new Intent(this, RideService.class);
                stopService(intent1);
        }
    }
}
