package com.jeongho.metarial.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jeongho.metarial.R;
import com.jeongho.metarial.adapter.ContentPagerAdapter;
import com.jeongho.metarial.adapter.HomeFrmAdapter;

import java.util.LinkedList;

/**
 * Created by Jeongho on 16/6/16.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRecyclerView;
    private HomeFrmAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private SwipeRefreshLayout mRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.home_rv);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //初始化cardView
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0 ; i < 50; i++) {
            list.add("galigeigei" + i);
        }
        mAdapter = new HomeFrmAdapter(list);
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.item_header, mRecyclerView, false);

        //初始化ViewPager
        ViewPager topVp = (ViewPager) headerView.findViewById(R.id.top_vp);

        LinkedList<View> views = new LinkedList<>();
        LinkedList<String> titles = new LinkedList<>();
        for (int i = 0; i < 4; i++){
            ImageView iv = new ImageView(getContext());
            iv.setImageResource(R.drawable.card_bg);
            iv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            views.add(iv);
            titles.add(i + "");
        }

        ContentPagerAdapter cpa = new ContentPagerAdapter(views, titles);
        topVp.setAdapter(cpa);

        //recycleView加头布局 尾布局
        mAdapter.setHeaderView(headerView);
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.item_footer, mRecyclerView, false);
        mAdapter.setFooterView(footerView);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.home_swipe_refresh);
        mRefreshLayout.setOnRefreshListener(this);
        return v;
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
                Toast.makeText(getContext(), "haha", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }
}
