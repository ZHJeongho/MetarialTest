package com.jeongho.metarial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeongho.metarial.R;
import com.jeongho.metarial.bean.AttentionUserBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jeongho on 2016/7/29.
 */
public class AttentionRecyclerAdapter extends RecyclerView.Adapter<AttentionRecyclerAdapter.ViewHolder>{

    private Context mContext;
    private int mItemLayoutId;
    private List<AttentionUserBean> mList;

    public AttentionRecyclerAdapter(Context context, int layoutId, List<AttentionUserBean> list) {
        mContext = context;
        mItemLayoutId = layoutId;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(mItemLayoutId, parent, false);
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mUserName.setText(mList.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView mCivHead;

        private TextView mUserName;

        public ViewHolder(View itemView) {
            super(itemView);
            mCivHead = (CircleImageView) itemView.findViewById(R.id.civ_head);
            mUserName = (TextView) itemView.findViewById(R.id.tv_user_name);
        }
    }
}
