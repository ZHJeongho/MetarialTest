package com.jeongho.metarial.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeongho.metarial.R;
import com.jeongho.metarial.adapter.HomeFrmAdapter;

import java.util.LinkedList;

/**
 * Created by Jeongho on 16/6/16.
 */
public class HomeFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.home_rv);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0 ; i < 50; i++) {
            list.add("galigeigei" + i);
        }
        mAdapter = new HomeFrmAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }
}
