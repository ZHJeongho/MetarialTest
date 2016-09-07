package com.jeongho.metarial.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.LocationMode;
import com.baidu.trace.OnEntityListener;
import com.baidu.trace.OnStartTraceListener;
import com.baidu.trace.OnStopTraceListener;
import com.baidu.trace.Trace;
import com.jeongho.metarial.R;
import com.jeongho.metarial.activity.MainActivity;
import com.jeongho.metarial.activity.RideInfoActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jeongho on 16/7/5.
 */
public class RideService extends Service{

    private static final String TAG = "RideService";

    private RideBinder mRideBinder = new RideBinder();

    private List<String> mList = new LinkedList<>();
    //鹰眼客户端
    private LBSTraceClient client;
    //轨迹服务
    private Trace mTrace;

    //鹰眼服务ID
    private long mServerId = 120566;
    //返回结果的类型（0 : 返回全部结果，1 : 只返回entityName的列表）
    private int mReturnType = 0;

    private int mActiveTime;

    private String mEntityName;

    private OnGetEntityList mOnGetEntityList;

    private OnStartTraceListener mOnStartTraceListener;
    private OnStopTraceListener mOnStopTraceListener;
    private OnEntityListener mOnEntityListener;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mRideBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        mList.add("aaa");
        mList.add("aab");
        mList.add("aac");

        Notification.Builder builder = new Notification.Builder(this);
        Notification notification = builder
                .setSmallIcon(R.mipmap.ic_launcher, 0)
                .setTicker("tongzhi")
                .setWhen(System.currentTimeMillis())
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent intent = new Intent(this, RideInfoActivity.class);
        //启动Activity
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_layout);
        //启动Activity
        PendingIntent openMain = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.content, openMain);
        remoteViews.setTextViewText(R.id.content, "哈哈哈哈");
        notification.contentView = remoteViews;
        notification.contentIntent = pendingIntent;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //id不同 就会弹出多个通知
        manager.notify(1, notification);
//        startForeground(1, notification);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mActiveTime = (int) (System.currentTimeMillis() / 1000 - 12 * 60 * 60);

        initClient();
        initListener();

        startTrace();
        return super.onStartCommand(intent, flags, startId);
    }

    private void startTrace() {
        if (client != null){
            client.startTrace(mTrace);
        }
    }

    private void initListener() {
        //初始化开启轨迹服务监听
        initOnStartTraceListener();
        //初始化停止轨迹服务监听
        initOnStopTraceListener();
        //初始化查询轨迹服务监听
        initOnEntityListener();
    }

    private void initOnEntityListener() {
        mOnEntityListener = new OnEntityListener() {
            @Override
            public void onRequestFailedCallback(String s) {

            }

            @Override
            public void onQueryEntityListCallback(String s) {
                System.out.println("entity回调接口消息 : " + s);
                //mOnGetEntityList.parseEntityList(s);
            }
        };
    }

    private void initOnStopTraceListener() {
        //实例化停止轨迹服务回调接口
        mOnStopTraceListener = new OnStopTraceListener(){
            // 轨迹服务停止成功
            @Override
            public void onStopTraceSuccess() {
                Log.d("stop", "Success");
            }
            // 轨迹服务停止失败（arg0 : 错误编码，arg1 : 消息内容，详情查看类参考）
            @Override
            public void onStopTraceFailed(int arg0, String arg1) {
            }
        };
    }

    private void initOnStartTraceListener() {
        //开启轨迹服务
        mOnStartTraceListener = new OnStartTraceListener() {
            //开启轨迹服务回调接口（arg0 : 消息编码，arg1 : 消息内容，详情查看类参考）
            @Override
            public void onTraceCallback(int arg0, String arg1) {
                Log.d("onTraceCallback", arg1);
            }
            //轨迹服务推送接口（用于接收服务端推送消息，arg0 : 消息类型，arg1 : 消息内容，详情查看类参考）
            @Override
            public void onTracePushCallback(byte arg0, String arg1) {
                Log.d("onTracePushCallback", arg1);
            }
        };
    }

    private void initClient() {
        //实例化轨迹服务客户端
        client = new LBSTraceClient(getApplicationContext());

        //entity标识
        mEntityName = "QxbTest";
        //轨迹服务类型（0 : 不上传位置数据，也不接收报警信息； 1 : 不上传位置数据，但接收报警信息；2 : 上传位置数据，且接收报警信息）
        int  traceType = 2;
        //实例化轨迹服务
        mTrace = new Trace(getApplicationContext(), mServerId, mEntityName, traceType);

        // 设置http请求协议类型0:http,1:https
        client.setProtocolType(1);

        // 设置定位模式
        client.setLocationMode(LocationMode.High_Accuracy);
        //打包周期10s 采集周期2s
        client.setInterval(2, 10);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

        //停止轨迹服务
        if (client != null){
            client.stopTrace(mTrace, mOnStopTraceListener);
        }
    }

    public class RideBinder extends Binder{
        public List<String> getPointList(){
            return mList;
        }

//        public String getEntityList(){
//
//        }
    }

//    public void getEntityList(){
//
//        client.queryEntityList(mServerId, mEntityName, null,
//                mReturnType, mActiveTime, 1000, 1, onEntityListener);
//    }

    public void getReatimeLoc(){
        //client.queryRealtimeLoc();
    }

    public interface OnGetEntityList{
        void parseEntityList(String result);
    }

    public void setOnGetEntityList(OnGetEntityList onGetEntityList){
        mOnGetEntityList = onGetEntityList;
    }
}
