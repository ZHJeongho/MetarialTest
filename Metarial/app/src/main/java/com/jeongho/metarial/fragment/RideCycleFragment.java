package com.jeongho.metarial.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jeongho.metarial.R;
import com.jeongho.metarial.activity.RideInfoActivity;
import com.jeongho.metarial.activity.RideMeetDetailsActivity;

/**
 * Created by Jeongho on 16/6/16.
 */
public class RideCycleFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton mRideFab;

    private Button mRideMeetBtn;
    private Button mRoadBookBtn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ride, container, false);

        mRideFab = (FloatingActionButton) v.findViewById(R.id.fab_ride);
        mRideFab.setOnClickListener(this);

        mRideMeetBtn = (Button) v.findViewById(R.id.btn_ride_meet);
        mRideMeetBtn.setOnClickListener(this);
        mRoadBookBtn = (Button) v.findViewById(R.id.btn_road_book);
        mRoadBookBtn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_ride:
                RideInfoActivity.startAction(getContext());
                break;
            case R.id.btn_ride_meet:
                RideMeetDetailsActivity.startAcition(getContext());
                break;
            case R.id.btn_road_book:
                break;
        }
    }
}
