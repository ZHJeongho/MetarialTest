package com.jeongho.metarial.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.LinkedList;
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

    public ContentPagerAdapter(Context context, List<Bitmap> bitmapList, List<String> titleList) {
        mTitles = titleList;
        mViewList = new LinkedList<>();
        for (int i = 0; i < bitmapList.size(); i++) {
            final ImageView iv = new ImageView(context);
            iv.setImageBitmap(bitmapList.get(i));
            iv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            mViewList.add(iv);
        }
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
