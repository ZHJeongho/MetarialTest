package com.jeongho.metarial.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeongho.metarial.R;
import com.jeongho.metarial.activity.RideInfoActivity;

/**
 * Created by Jeongho on 16/6/16.
 */
public class RideCycleFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton mRideFab;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ride, container, false);

        mRideFab = (FloatingActionButton) v.findViewById(R.id.fab_ride);
        mRideFab.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_ride:
                RideInfoActivity.startAction(getContext());
                break;
        }
    }
}
