package com.jeongho.metarial.adapter.listview;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jeongho on 2016/7/29.
 */
public class CommonViewHolder {

    private View mConvertView;

    private final SparseArray<View> mViews;

    /**
     * 私有构造
     * @param context
     * @param layoutId
     * @param parent
     */
    private CommonViewHolder(Context context, int layoutId, ViewGroup parent){

        mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    /**
     * 静态工厂
     * @return
     */
    public static CommonViewHolder get(Context context, View convertView, int layoutId, ViewGroup parent, int position){
        if (convertView == null){
            return new CommonViewHolder(context, layoutId, parent);
        }

        return (CommonViewHolder) convertView.getTag();
    }

    public <T extends View>T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView(){
        return mConvertView;
    }

    public CommonViewHolder setText(int viewId, String value){
        TextView tv = getView(viewId);
        tv.setText(value);
        return this;
    }
}
