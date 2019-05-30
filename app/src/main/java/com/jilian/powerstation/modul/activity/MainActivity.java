package com.jilian.powerstation.modul.activity;

import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.base.BaseMainActivity;

public class MainActivity extends BaseMainActivity {
    @Override
    protected void createViewModel() {
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.clearSpecifyActivities(new Class[]{WelcomeActivity.class});
    }
}
