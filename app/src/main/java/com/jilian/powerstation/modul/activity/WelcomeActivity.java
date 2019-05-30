package com.jilian.powerstation.modul.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.jilian.powerstation.MyApplication;

public class WelcomeActivity extends FragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this,LoginActivity.class));
        MyApplication.addActivity(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.removeActivity(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
