package com.jeongho.metarial.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Jeongho on 16/6/16.
 */
public class ContentFragmentAdapter extends FragmentPagerAdapter{

    private List<Fragment> mFragments;
    private List<String> mTitles;
    public ContentFragmentAdapter(FragmentManager fm, List<Fragment> list, List<String> titles) {
        super(fm);
        mFragments = list;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

}
