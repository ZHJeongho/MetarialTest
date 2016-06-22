package com.jeongho.metarial.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeongho.metarial.R;

import java.util.List;

/**
 * Created by Jeongho on 2016/6/18.
 */
public class HomeFrmAdapter extends RecyclerView.Adapter<HomeFrmAdapter.ViewHolder>{

    private RecyclerView mRecyclerView;
    private List<String> mTitles;
    private View mHeaderView;
    private View mFooterView;

    private final static int TYPE_HEADER = 1;
    private final static int TYPE_FOOTER = 2;
    private final static int TYPE_CONTENT = 3;

    public HomeFrmAdapter(List<String> list) {
        mTitles = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("HomeFrmAdapter", "onCreateViewHolder");
        ViewHolder holder = null;
        int viewId = 0;
        View v = null;
        switch (viewType){
            case TYPE_HEADER:
                v = mHeaderView;
                break;
            case TYPE_FOOTER:
                v = mFooterView;
                break;
            case TYPE_CONTENT:
                viewId = R.layout.item;
                v = LayoutInflater.from(parent.getContext()).inflate(viewId, parent, false);
                break;
        }

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);
        holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("HomeFrmAdapter", "onBindViewHolder");
        switch (getItemViewType(position)){
            case TYPE_CONTENT:
                holder.mTextView.setText(mTitles.get(position - 1));
                return;
            case TYPE_FOOTER:
                return;
            case TYPE_HEADER:
                return;
        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null){
            return mTitles.size();
        }else if (mHeaderView != null && mFooterView == null){
            return mTitles.size() + 1;
        }else if (mHeaderView == null && mFooterView != null){
            return mTitles.size() + 1;
        }else{
            return mTitles.size() + 2;
        }
    }


    public void setHeaderView(View headerView){
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void setFooterView(View footerView){
        mFooterView = footerView;
        notifyItemChanged(getItemCount() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("HomeFrmAdapter", "getItemViewType");
        if (mHeaderView == null && mFooterView == null){
            return TYPE_CONTENT;
        }

        if (mHeaderView != null && position == 0){
            return TYPE_HEADER;
        }

        if (mFooterView != null && position == getItemCount() - 1){
            return TYPE_FOOTER;
        }
        return TYPE_CONTENT;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView){
                return;
            }
            if (itemView == mFooterView){
                return;
            }
            mTextView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}
