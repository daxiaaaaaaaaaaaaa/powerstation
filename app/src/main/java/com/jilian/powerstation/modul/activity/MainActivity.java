package com.jilian.powerstation.modul.activity;

import com.jilian.powerstation.MyApplication;

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
