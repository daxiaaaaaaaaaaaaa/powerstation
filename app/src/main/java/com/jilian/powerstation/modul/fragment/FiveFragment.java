package com.jilian.powerstation.modul.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.modul.activity.BaseMainActivity;
import com.jilian.powerstation.modul.activity.IntelligentDeviceActivity;
import com.jilian.powerstation.modul.activity.RegistActivity;
import com.jilian.powerstation.modul.activity.StationDetailActivity;

import java.lang.reflect.Field;


public class FiveFragment extends BaseFragment  {



    @Override
    protected void loadData() {

    }


    @Override
    protected void createViewModel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_five;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.user_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), IntelligentDeviceActivity.class));
//                startActivity(new Intent(getContext(), StationDetailActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        
    }

    @Override
    protected void initListener() {

    }


}
