package com.jeongho.metarial.common;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Jeongho on 16/8/4.
 */
public class QxbApplication extends Application{
    private static QxbApplication instance;

    public static QxbApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this);
    }
}
