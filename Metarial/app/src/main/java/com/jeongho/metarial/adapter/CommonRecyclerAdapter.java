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
        CommonRecyclerVH holder = new CommonRecyclerVH(itemView);
        //holder.setOnItemViewClick(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(CommonRecyclerVH holder, int position) {
        mBindViewHolder.bindViewHolder(holder, position);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    //    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        private SparseArray<View> mViewSparseArray;
//        private View mItemView;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            mItemView = itemView;
//            mViewSparseArray = new SparseArray<>();
//        }
//
//        public View getView(int viewId){
//            View view = mViewSparseArray.get(viewId);
//            if (view == null){
//                view = mItemView.findViewById(viewId);
//                mViewSparseArray.put(viewId, view);
//            }
//            return view;
//        }
//
//        public ViewHolder setText(int viewId, String value){
//            TextView tv = (TextView) getView(viewId);
//            tv.setText(value);
//            return this;
//        }
//
//        public ViewHolder addClickListener(int viewId){
//            View v = getView(viewId);
//            v.setOnClickListener(this);
//            return this;
//        }
//
//        @Override
//        public void onClick(View v) {
//            mBindViewHolder.onViewClick(v);
//        }
//    }

    public interface OnBindViewHolder{
        void bindViewHolder(CommonRecyclerVH holder, int position);
        void onItemViewClick(View v, int position);
    }
}
