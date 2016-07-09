package com.jeongho.metarial.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Jeongho on 2016/6/15.
 */
public class ContentPagerAdapter extends PagerAdapter{

    private List<View> mViewList;
    private List<String> mTitles;

    public ContentPagerAdapter(List<View> viewList, List<String> titles) {
        mViewList = viewList;
        mTitles = titles;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position), 0);
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    public void refresh(List<View> views, List<String> list){
        mViewList = views;
        mTitles = list;
        notifyDataSetChanged();
    }
}
