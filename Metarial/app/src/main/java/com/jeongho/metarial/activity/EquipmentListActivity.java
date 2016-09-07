package com.jeongho.metarial.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jeongho.metarial.R;
import com.jeongho.metarial.Utils.ServerUtil;

import okhttp3.Call;

/**
 * Created by Jeongho on 2016/9/7.
 */
public class EquipmentListActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_list);
        ServerUtil.getBicycleList(new ServerUtil.OnStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("bicycle list", e.getMessage());
            }

            @Override
            public void onSuccess(String response, int id) {
                Log.d("bicycle list", response);
            }
        });
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, EquipmentListActivity.class);
        context.startActivity(intent);


    }
}
