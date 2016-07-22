package com.jeongho.metarial.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeongho.metarial.R;
import com.jeongho.metarial.adapter.ContentFragmentAdapter;
import com.jeongho.metarial.adapter.ContentPagerAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jeongho on 2016/6/17.
 */
public class MainFragment extends Fragment{

    private TabLayout mTab;
    private ViewPager mContentVp;
    private ContentPagerAdapter mPagerAdapter;
    private ContentFragmentAdapter mContentFragmentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        //初始化ViewPager
        mContentVp = (ViewPager) v.findViewById(R.id.vp_content);

        List<String> titles = new LinkedList<>();
        titles.add("首页");
        titles.add("骑圈");
        titles.add("发现");

        List<Fragment> fragmentList = new LinkedList<>();
        HomeFragment homeFragment = new HomeFragment();
        RideCycleFragment rideCycleFragment = new RideCycleFragment();
        DiscoveryFragment discoveryFragment = new DiscoveryFragment();

        fragmentList.add(homeFragment);
        fragmentList.add(rideCycleFragment);
        fragmentList.add(discoveryFragment);

        mContentFragmentAdapter = new ContentFragmentAdapter(getActivity().getSupportFragmentManager(), fragmentList, titles);
        mContentVp.setAdapter(mContentFragmentAdapter);

        //TabLayout与ViewPager绑定
        mTab = (TabLayout) v.findViewById(R.id.tab);
        mTab.setTabTextColors(getResources().getColor(R.color.colorText),
                getResources().getColor(R.color.colorPrimaryLight));
        mTab.setupWithViewPager(mContentVp);

        return v;
    }
}
