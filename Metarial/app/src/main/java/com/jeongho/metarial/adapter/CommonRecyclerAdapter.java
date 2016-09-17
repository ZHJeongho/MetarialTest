package com.jeongho.metarial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Jeongho on 2016/7/29.
 */
public class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<CommonRecyclerVH> {

    private Context mContext;
    private int mItemLayoutId;
    private List<T> mList;
    private OnBindViewHolder mBindViewHolder;

    public CommonRecyclerAdapter(Context context, int layoutId, List<T> list, OnBindViewHolder bindViewHolder) {
        mContext = context;
        mItemLayoutId = layoutId;
        mList = list;
        mBindViewHolder = bindViewHolder;
    }

    @Override
    public CommonRecyclerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(mItemLayoutId, parent, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        CommonRecyclerVH holder = new CommonRecyclerVH(itemView);
        //holder.setOnItemViewClick(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(CommonRecyclerVH holder, final int position) {
        mBindViewHolder.bindViewHolder(holder, position);
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBindViewHolder.onItemViewClick(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public T getItem(int position){
        return mList.get(position);
    }

    public void addMoreDatas(List<T> moreDatas){
        int positionStart = mList.size();
        mList.addAll(moreDatas);
        notifyItemRangeInserted(positionStart, moreDatas.size());
    }


    public void refreshNewData(List<T> newDatas){
        mList.addAll(newDatas);
        notifyItemRangeInserted(0, newDatas.size());
    }


    public interface OnBindViewHolder{
        void bindViewHolder(CommonRecyclerVH holder, int position);
        void onItemViewClick(View v, int position);
    }
}
