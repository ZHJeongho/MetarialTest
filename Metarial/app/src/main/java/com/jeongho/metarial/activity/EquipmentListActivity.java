package com.jeongho.metarial.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jeongho.metarial.BaseActivity;
import com.jeongho.metarial.R;
import com.jeongho.metarial.Utils.ServerUtil;
import com.jeongho.metarial.adapter.CommonRecyclerAdapter;
import com.jeongho.metarial.adapter.CommonRecyclerVH;
import com.jeongho.metarial.bean.BicycleListBean;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Jeongho on 2016/9/7.
 */
public class EquipmentListActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    //下拉刷新
    private static final int REFRESH_BICYCLE_LIST = 0x01;
    //上拉加载
    private static final int MORE_BICYCLE_LIST = 0x02;
    private Toolbar mToolbar;
    private RecyclerView mContentRv;
    private SwipeRefreshLayout mContentSrl;
    private CommonRecyclerAdapter<BicycleListBean.BicycleBean> mRecyclerAdapter;
    private List<BicycleListBean.BicycleBean> mBicycleBeanList = new LinkedList<>();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == REFRESH_BICYCLE_LIST){
                Log.d("temp size", mTempList.size() + "");
                if (mContentSrl.isRefreshing()){
                    mContentSrl.setRefreshing(false);
                }
                initBicycle();
            }
        }
    };

    private void initBicycle() {
        mRecyclerAdapter.refreshNewData(mTempList);
    }

    @Override
    public void initView() {
        mContentRv = (RecyclerView) findViewById(R.id.rv_bicycle);
        mContentSrl = (SwipeRefreshLayout) findViewById(R.id.srl_bicycle);
        mContentSrl.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorPrimaryLight, R.color.colorAccent);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(R.string.bicycle);
            mToolbar.inflateMenu(R.menu.menu_home);
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        }
    }

    private LinkedList<BicycleListBean.BicycleBean> mTempList;
    @Override
    public void initData() {
        mRecyclerAdapter = new CommonRecyclerAdapter<>(this, R.layout.item_bicycle_rv, mBicycleBeanList, new CommonRecyclerAdapter.OnBindViewHolder() {
            @Override
            public void bindViewHolder(CommonRecyclerVH holder, int position) {
                holder.setText(R.id.tv_model, mBicycleBeanList.get(position).getModel());
                holder.setImage(R.id.sdv_bicycle_image, mBicycleBeanList.get(position).getPic1());
                holder.setText(R.id.tv_type, mBicycleBeanList.get(position).getBikeType());
                String price = mBicycleBeanList.get(position).getPrice();
                if (price == "null" || price == null){
                    holder.setText(R.id.tv_price, getResources().getString(R.string.no_price));
                }else {
                    holder.setText(R.id.tv_price, "¥" + price);
                }

            }

            @Override
            public void onItemViewClick(View v, int position) {
                String bikeId = mRecyclerAdapter.getItem(position).getBikeId();
                EquipmentDetailsAty.startAction(EquipmentListActivity.this, bikeId);
            }
        });

        mContentRv.setLayoutManager(new GridLayoutManager(EquipmentListActivity.this, 2));
        mContentRv.setAdapter(mRecyclerAdapter);

        String startIndex = "0";
        getBicycleData(startIndex);
    }

    private void getBicycleData(String startIndex) {
        ServerUtil.getBicycleList(startIndex, new ServerUtil.OnStringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("bicycle List", e.getMessage());
            }

            @Override
            public void onSuccess(String response, int id) {
                Log.d("bicycle List", response);
                //TODO: 存缓存
                Type type = new TypeToken<BicycleListBean>(){}.getType();
                Gson gson = new Gson();

                BicycleListBean bicycleList = gson.fromJson(response, type);
                //BicycleListBean bicycleList = gson.fromJson(response, BicycleListBean.class);
                switch (bicycleList.result) {
                    case "200":
                        mTempList = bicycleList.bikes;
                        Message msg = Message.obtain();
                        msg.what = REFRESH_BICYCLE_LIST;
                        mHandler.sendMessage(msg);
                        break;
                    case "300":

                        break;
                }
            }
        });
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
        mContentSrl.setOnRefreshListener(this);
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

    @Override
    public void onRefresh() {
        String bikeId = mTempList.get(mTempList.size() - 1).getBikeId();
        getBicycleData(bikeId);
    }
}
