package com.jeongho.metarial.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.jeongho.metarial.BaseActivity;
import com.jeongho.metarial.R;
import com.jeongho.metarial.Utils.ServerUtil;
import com.jeongho.metarial.common.QxbAccount;
import com.jeongho.metarial.common.QxbApplication;
import com.jeongho.qxblibrary.Utils.SharedPreferencesUtil;
import com.jeongho.qxblibrary.Utils.ToastUtil;

import okhttp3.Call;

/**
 * Created by Jeongho on 2016/9/7.
 */
public class EquipmentDetailsAty extends BaseActivity implements ServerUtil.OnStringCallback {

    private Toolbar mToolbar;
    @Override
    public void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null){
            ToastUtil.showShort(this, "not null");
            mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            setSupportActionBar(mToolbar);
        }
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
        Log.d("error", e.getMessage());
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
