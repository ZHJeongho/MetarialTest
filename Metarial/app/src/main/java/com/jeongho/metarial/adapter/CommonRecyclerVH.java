package com.jeongho.metarial.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Jeongho on 2016/7/29.
 */
public class CommonRecyclerVH extends RecyclerView.ViewHolder implements View.OnClickListener{
    private SparseArray<View> mViewSparseArray;
    private View mItemView;
    private OnRecyclerItemClick mOnItemViewClick;

    public CommonRecyclerVH(View itemView) {
        super(itemView);
        mItemView = itemView;
        mViewSparseArray = new SparseArray<>();
    }

    public View getView(int viewId){
        View view = mViewSparseArray.get(viewId);
        if (view == null){
            view = mItemView.findViewById(viewId);
            mViewSparseArray.put(viewId, view);
        }
        return view;
    }

    public CommonRecyclerVH setText(int viewId, String value){
        TextView tv = (TextView) getView(viewId);
        tv.setText(value);
        return this;
    }

    public CommonRecyclerVH addClickListener(int viewId){
        View v = getView(viewId);
        v.setOnClickListener(this);
        return this;
    }

    @Override
    public void onClick(View v) {
        mOnItemViewClick.onItemViewClick(v, getAdapterPosition());
    }

    public interface OnRecyclerItemClick {
        void onItemViewClick(View child, int position);
    }

    public void setOnItemViewClick(OnRecyclerItemClick onItemViewClick){
        mOnItemViewClick = onItemViewClick;
    }

}
