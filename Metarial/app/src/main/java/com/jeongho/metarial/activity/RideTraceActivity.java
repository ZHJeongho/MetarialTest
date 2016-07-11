package com.jeongho.metarial.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.jeongho.metarial.R;
import com.jeongho.metarial.service.RideService;
import com.jeongho.qxblibrary.Utils.ToastUtil;

import java.util.List;

/**
 * Created by Jeongho on 16/7/5.
 */
public class RideTraceActivity extends AppCompatActivity{

    private RideService.RideBinder mBinder;
    private MapView mMapView;
    private BaiduMap mBaiduMap;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (RideService.RideBinder)service;
            List<String> list = mBinder.getPointList();

            ToastUtil.showShort(RideTraceActivity.this, list.size() + "");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_ride_trace);

        mMapView = (MapView) findViewById(R.id.map);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        Intent intent = new Intent(this, RideService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, RideTraceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
}
