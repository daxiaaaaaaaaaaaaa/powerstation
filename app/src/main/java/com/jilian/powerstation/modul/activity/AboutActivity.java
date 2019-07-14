package com.jilian.powerstation.modul.activity;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;

public class AboutActivity extends BaseActivity {
    @Override
    protected void createViewModel() {

    }

    @Override
    public int intiLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void initView() {
        setNormalTitle("About", v -> finish());
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
