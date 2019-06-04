package com.jilian.powerstation.modul.fragment;


import android.os.Bundle;
import android.view.View;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.utils.StatusBarUtil;


public class FourFragment extends BaseFragment  {



    @Override
    protected void loadData() {
        StatusBarUtil.setStatusBarMode(getmActivity(), true, R.color.white);
    }


    @Override
    protected void createViewModel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_four;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        StatusBarUtil.setStatusBarMode(getmActivity(), true, R.color.white);
    }

    @Override
    protected void initListener() {

    }


}
