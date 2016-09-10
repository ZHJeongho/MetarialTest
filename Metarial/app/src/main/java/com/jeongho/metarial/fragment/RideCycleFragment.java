package com.jeongho.metarial.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jeongho.metarial.R;
import com.jeongho.metarial.activity.RideBookListActivity;
import com.jeongho.metarial.activity.RideClubListActivity;
import com.jeongho.metarial.activity.RideHistoryActivity;
import com.jeongho.metarial.activity.RideInfoActivity;
import com.jeongho.metarial.activity.RideMeetDetailsActivity;
import com.jeongho.metarial.adapter.ContentPagerAdapter;
import com.jeongho.metarial.adapter.HomeFrmAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jeongho on 16/6/16.
 */
public class RideCycleFragment extends Fragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private HomeFrmAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private SwipeRefreshLayout mRefreshLayout;
    private FloatingActionButton mRideFab;

    private Button mRideMeetBtn;
    private Button mRoadBookBtn;
    private Button mRideHistoryBtn;
    private Button mRideClubBtn;

    private ViewPager mRideCycleTopVp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ride, container, false);

        mRideFab = (FloatingActionButton) root.findViewById(R.id.fab_ride);
        mRideFab.setOnClickListener(this);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.rv_ride);
        mLayoutManager = new LinearLayoutManager(getContext());

        mGridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        //初始化cardView
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0 ; i < 10; i++) {
            list.add("galigeigei" + i);
        }
        mAdapter = new HomeFrmAdapter(getActivity(), list);
        View header = inflater.inflate(R.layout.header_fragment_ride, null);
        mAdapter.setHeaderView(header);
        //初始化TopViewPager
        mRideCycleTopVp = (ViewPager) header.findViewById(R.id.vp_ride);

        List<View> viewList = new LinkedList<>();
        List<String> titleList = new LinkedList<>();
        for (int i = 0; i < 5; i++){
            View v = LayoutInflater.from(getContext()).inflate(R.layout.item_top_vp, null);
            viewList.add(v);
            titleList.add(i + "");
        }

        ContentPagerAdapter adapter = new ContentPagerAdapter(viewList, titleList);
        mRideCycleTopVp.setAdapter(adapter);
        //加底部
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.item_footer, mRecyclerView, false);
        mAdapter.setFooterView(footerView);

        mRideMeetBtn = (Button) header.findViewById(R.id.btn_ride_meet);
        mRideMeetBtn.setOnClickListener(this);
        mRoadBookBtn = (Button) header.findViewById(R.id.btn_road_book);
        mRoadBookBtn.setOnClickListener(this);
        mRideHistoryBtn = (Button) header.findViewById(R.id.btn_ride_history);
        mRideHistoryBtn.setOnClickListener(this);
        mRideClubBtn = (Button) header.findViewById(R.id.btn_ride_club);
        mRideClubBtn.setOnClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_ride:
                RideInfoActivity.startAction(getContext());
                break;
            case R.id.btn_ride_meet:
                RideMeetDetailsActivity.startAction(getContext());
                break;
            case R.id.btn_ride_history:
                RideHistoryActivity.startAction(getContext());
                break;
            case R.id.btn_ride_club:
                RideClubListActivity.startAction(getContext());
                break;
            case R.id.btn_road_book:
                RideBookListActivity.startAction(getContext());
                break;
        }
    }
}
