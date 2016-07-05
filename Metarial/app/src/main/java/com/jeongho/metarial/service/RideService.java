package com.jeongho.metarial.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.OnStartTraceListener;
import com.baidu.trace.OnStopTraceListener;
import com.baidu.trace.Trace;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jeongho on 16/7/5.
 */
public class RideService extends Service{

    private RideBinder mRideBinder = new RideBinder();

    private List<String> mList = new LinkedList<>();

    private LBSTraceClient client;

    private Trace trace;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mRideBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mList.add("aaa");
        mList.add("aab");
        mList.add("aac");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //实例化轨迹服务客户端
        client = new LBSTraceClient(getApplicationContext());
        //鹰眼服务ID
        long serviceId  = 115806;
        //entity标识
        String entityName = "mycar";
        //轨迹服务类型（0 : 不上传位置数据，也不接收报警信息； 1 : 不上传位置数据，但接收报警信息；2 : 上传位置数据，且接收报警信息）
        int  traceType = 2;
        //实例化轨迹服务
        trace = new Trace(getApplicationContext(), serviceId, entityName, traceType);
        //实例化开启轨迹服务回调接口
        OnStartTraceListener startTraceListener = new OnStartTraceListener() {
            //开启轨迹服务回调接口（arg0 : 消息编码，arg1 : 消息内容，详情查看类参考）
            @Override
            public void onTraceCallback(int arg0, String arg1) {
            }
            //轨迹服务推送接口（用于接收服务端推送消息，arg0 : 消息类型，arg1 : 消息内容，详情查看类参考）
            @Override
            public void onTracePushCallback(byte arg0, String arg1) {
            }
        };

        //开启轨迹服务
        client.startTrace(trace, startTraceListener);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //实例化停止轨迹服务回调接口
        OnStopTraceListener stopTraceListener = new OnStopTraceListener(){
            // 轨迹服务停止成功
            @Override
            public void onStopTraceSuccess() {
            }
            // 轨迹服务停止失败（arg0 : 错误编码，arg1 : 消息内容，详情查看类参考）
            @Override
            public void onStopTraceFailed(int arg0, String arg1) {
            }
        };

        //停止轨迹服务
        client.stopTrace(trace,stopTraceListener);
    }

    public class RideBinder extends Binder{
        public List<String> getPointList(){
            return mList;
        }
    }
}
