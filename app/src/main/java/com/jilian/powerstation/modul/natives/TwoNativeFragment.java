package com.jilian.powerstation.modul.natives;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseFragment;

public class TwoNativeFragment extends BaseFragment {
    private TextView tvOne;
    private TextView tvTwo;
    private TextView tvThree;


    @Override
    protected void loadData() {

    }

    @Override
    protected void createViewModel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_twonative;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setNormalTitle("Real-time flow graph", v -> getActivity().finish());
        tvOne = (TextView) view.findViewById(R.id.tv_one);
        tvTwo = (TextView) view.findViewById(R.id.tv_two);
        tvThree = (TextView) view.findViewById(R.id.tv_three);
    }

    @Override
    protected void initData() {
        tvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getmActivity(),BaseSettingActivity.class));
            }
        });
        tvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getmActivity(),AdvancedSettingActivity.class));

            }
        });

        tvThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getmActivity(),WindowActivity.class));



            }
        });


    }

    @Override
    protected void initListener() {

    }
}
