package com.jilian.powerstation.modul.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.BaseMainActivity;
import com.jilian.powerstation.base.CommonActivity;

public class RegistActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.removeActivity(this);
    }
    @Override
    protected void createViewModel() {

    }

    @Override
    public int intiLayout() {
        return R.layout.activity_regist;
    }

    @Override
    public void initView() {
        setNormalTitle("Register", v -> finish());
     findViewById(R.id.tv_regist).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(RegistActivity.this, BaseMainActivity.class));
         }
     });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

}
