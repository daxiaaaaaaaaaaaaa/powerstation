package com.jilian.powerstation.modul.activity;

import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.base.BaseMainActivity;
import com.jilian.powerstation.common.dto.PowerDto;

public class MainActivity extends BaseMainActivity {
    private PowerDto data;

    public PowerDto getData() {
        return data;
    }

    public void setData(PowerDto data) {
        this.data = data;
    }

    @Override
    protected void createViewModel() {
    }

    @Override
    protected void init() {
        data = (PowerDto) getIntent().getSerializableExtra("data");
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.clearSpecifyActivities(new Class[]{WelcomeActivity.class});
    }
}
