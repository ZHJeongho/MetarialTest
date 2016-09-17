package com.jeongho.metarial.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jeongho.metarial.BaseActivity;
import com.jeongho.metarial.R;
import com.jeongho.metarial.Utils.ServerUtil;
import com.jeongho.metarial.common.QxbAccount;
import com.jeongho.metarial.common.QxbApplication;
import com.jeongho.qxblibrary.Utils.SharedPreferencesUtil;

import okhttp3.Call;

/**
 * Created by Jeongho on 2016/9/7.
 */
public class EquipmentDetailsAty extends BaseActivity implements ServerUtil.OnStringCallback {
    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String bikeId = intent.getStringExtra("bikeId");
        if (QxbAccount.isSignUp){
            SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(
                    QxbApplication.getInstance(), SharedPreferencesUtil.USER_DATA);
            String token = sharedPreferencesUtil.getString(SharedPreferencesUtil.TOKEN, "");
            ServerUtil.getBicycleDetail(bikeId, token, this);
        }else {
            ServerUtil.getBicycleDetail(bikeId, this);
        }

    }

    @Override
    public void initListener() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_equipment_details;
    }

    public static void startAction(Context context, String bikeId) {
        Intent intent = new Intent(context, EquipmentDetailsAty.class);
        intent.putExtra("bikeId", bikeId);
        context.startActivity(intent);
    }

    /**
     * 获取整车详细信息失败
     * @param call
     * @param e
     * @param id
     */
    @Override
    public void onError(Call call, Exception e, int id) {

    }

    /**
     * 获取整车详细信息成功
     * @param response
     * @param id
     */
    @Override
    public void onSuccess(String response, int id) {
        Log.d("response", response);
    }
}
