package com.jeongho.metarial.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.jeongho.metarial.BaseActivity;
import com.jeongho.metarial.R;
import com.jeongho.metarial.Utils.ServerUtil;
import com.jeongho.metarial.adapter.CommonRecyclerAdapter;
import com.jeongho.metarial.adapter.CommonRecyclerVH;
import com.jeongho.metarial.bean.BicycleListBean;
import com.jeongho.qxblibrary.Utils.ToastUtil;

import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Jeongho on 2016/9/7.
 */
public class EquipmentListActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {

    private static final int INIT_BICYCLE_LIST = 0x01;
    private Toolbar mToolbar;
    private RecyclerView mContentRv;
    private CommonRecyclerAdapter<BicycleListBean.BicycleBean> mRecyclerAdapter;
    private List<BicycleListBean.BicycleBean> mBicycleBeanList = new LinkedList<>();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == INIT_BICYCLE_LIST){
                ToastUtil.showShort(EquipmentListActivity.this, "aa");
                initBicycle();
            }
        }
    };

    private void initBicycle() {

        mRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void initView() {
        mContentRv = (RecyclerView) findViewById(R.id.rv_bicycle);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(R.string.bicycle);
            mToolbar.inflateMenu(R.menu.menu_home);
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        }
    }

    @Override
    public void initData() {
        mRecyclerAdapter = new CommonRecyclerAdapter<>(this, R.layout.item_bicycle_rv, mBicycleBeanList, new CommonRecyclerAdapter.OnBindViewHolder() {
            @Override
            public void bindViewHolder(CommonRecyclerVH holder, int position) {
                holder.setText(R.id.tv_model, mBicycleBeanList.get(position).getModel());
            }

            @Override
            public void onItemViewClick(View v, int position) {
                ToastUtil.showShort(EquipmentListActivity.this, "" + position);
            }
        });

        mContentRv.setLayoutManager(new GridLayoutManager(EquipmentListActivity.this, 2));
        mContentRv.setAdapter(mRecyclerAdapter);

        ServerUtil.getBicycleList(new ServerUtil.OnStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("bicycle List", e.getMessage());
            }

            @Override
            public void onSuccess(String response, int id) {
                Log.d("bicycle List", response);
                Gson gson = new Gson();
                BicycleListBean bicycleList = gson.fromJson(response, BicycleListBean.class);
                //                    JSONObject jsonObject = new JSONObject(response);
                //                    String result = jsonObject.getString("result");
                switch (bicycleList.result) {
                    case "200":
                        //                            String bikes = jsonObject.getString("bikes");

                        mBicycleBeanList.addAll(bicycleList.bikes);
                        Log.d("size", mBicycleBeanList.size() + "");
                        Message msg = Message.obtain();
                        msg.what = INIT_BICYCLE_LIST;
                        mHandler.sendMessage(msg);
                        break;
                    case "300":

                        break;
                }
            }
        });

        Log.d("size", mBicycleBeanList.size() + "");




    }

    @Override
    public void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.startAction(EquipmentListActivity.this);
            }
        });
        mToolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_equipment_list;
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, EquipmentListActivity.class);
        context.startActivity(intent);


    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){

        }
        return true;
    }
}
