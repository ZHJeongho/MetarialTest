package com.jeongho.metarial.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeongho.metarial.R;
import com.jeongho.metarial.adapter.CommonRecyclerAdapter;
import com.jeongho.metarial.adapter.CommonRecyclerVH;
import com.jeongho.metarial.bean.AttentionUserBean;
import com.jeongho.metarial.widge.AttentionDecoration;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jeongho on 16/6/16.
 */
public class MyAttentionFragment extends Fragment implements CommonRecyclerAdapter.OnBindViewHolder, CommonRecyclerVH.OnRecyclerItemClick {
    private RecyclerView mAttentionLv;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private List<AttentionUserBean> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_attention, container, false);

        mAttentionLv = (RecyclerView) root.findViewById(R.id.rv_attention);
        mAttentionLv.addItemDecoration(new AttentionDecoration(getContext()));
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        mList = new LinkedList<>();
        for (int i = 0; i < 20; i++){
            AttentionUserBean bean = new AttentionUserBean();
            bean.setUrl("我叫吴明峰" + i);
            bean.setUserName("我叫吴明峰" + i);
            mList.add(bean);
        }

//        mAdapter = new AttentionRecyclerAdapter(getContext(), R.layout.item_attention_user, mList);
//        mAttentionLv.setAdapter(mAdapter);

//        mAdapter = new CommonRecyclerAdapter<>(getContext(),
//                R.layout.item_attention_user, mList, new CommonRecyclerAdapter.OnBindViewHolder() {
//            @Override
//            public void bindViewHolder(CommonRecyclerAdapter.ViewHolder holder, int position) {
//                holder.setText(R.id.tv_user_name, mList.get(position).getUserName());
//            }
//        });

        mAdapter = new CommonRecyclerAdapter<>(getContext(), R.layout.item_attention_user, mList, this);
        mAttentionLv.setAdapter(mAdapter);
        mAttentionLv.setLayoutManager(mLayoutManager);
        return root;
    }

    @Override
    public void bindViewHolder(CommonRecyclerVH holder, int position) {
        holder.setText(R.id.tv_user_name, mList.get(position).getUserName());
        holder.setOnItemViewClick(this);
        holder.addClickListener(R.id.tv_user_name);
        holder.addClickListener(R.id.civ_head);
    }

    @Override
    public void onItemViewClick(View v, int position) {

    }


}
