package com.jeongho.metarial.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jeongho.metarial.R;
import com.jeongho.metarial.activity.DiscoveryDetailsAty;

/**
 * Created by Jeongho on 16/6/16.
 */
public class DiscoveryFragment extends Fragment implements View.OnClickListener {

    private Button mGuideBtn;
    private Button mNewsBtn;
    private Button mMaintenanceBtn;
    private Button mDryBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_discover, container, false);

        mGuideBtn = (Button) v.findViewById(R.id.btn_ride_history);
        mGuideBtn.setOnClickListener(this);
        mNewsBtn = (Button) v.findViewById(R.id.btn_ride_club);
        mNewsBtn.setOnClickListener(this);
        mMaintenanceBtn = (Button) v.findViewById(R.id.btn_maintenance);
        mMaintenanceBtn.setOnClickListener(this);
        mDryBtn = (Button) v.findViewById(R.id.btn_dry);
        mDryBtn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_ride_history:
                intent.setClass(getContext(), DiscoveryDetailsAty.class);
                intent.putExtra("TAG", 1);
                startActivity(intent);
                break;
            case R.id.btn_ride_club:
                intent.setClass(getContext(), DiscoveryDetailsAty.class);
                intent.putExtra("TAG", 2);
                startActivity(intent);
                break;
            case R.id.btn_maintenance:
                intent.setClass(getContext(), DiscoveryDetailsAty.class);
                intent.putExtra("TAG", 3);
                startActivity(intent);
                break;
            case R.id.btn_dry:
                intent.setClass(getContext(), DiscoveryDetailsAty.class);
                intent.putExtra("TAG", 4);
                startActivity(intent);
                break;
        }
    }
}
