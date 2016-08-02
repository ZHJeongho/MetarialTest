package com.jeongho.metarial.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
                boolean isOn = GPSIsOn();
                if (!isOn) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("GPS设置").setMessage(R.string.suggest_GPS)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.show();
                }
                Intent intent = new Intent(this, RideService.class);
                startService(intent);
                break;
            case R.id.btn_stop_ride:
                Intent stopIntent = new Intent(this, RideService.class);
                stopService(stopIntent);
        }
    }

    private boolean GPSIsOn() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
