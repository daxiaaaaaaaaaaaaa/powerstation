package com.jilian.powerstation.modul.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.modul.activity.StationDetailActivity;
import com.jilian.powerstation.utils.StatusBarUtil;


public class FiveFragment extends BaseFragment  {



    @Override
    protected void loadData() {
        //根据状态栏颜色来决定状态栏文字用黑色还是白色
        StatusBarUtil.setStatusBarMode(getmActivity(), false, R.color.colorPrimary);
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
//                startActivity(new Intent(getContext(), IntelligentDeviceActivity.class));
                startActivity(new Intent(getContext(), StationDetailActivity.class));
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
