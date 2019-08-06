package com.jilian.powerstation.modul.natives;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;

public class WindowActivity extends BaseActivity {
    @Override
    protected void createViewModel() {

    }

    @Override
    public int intiLayout() {
        return R.layout.activity_window;
    }

    @Override
    public void initView() {
        setNormalTitle("Monitoring window", v -> finish());
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
