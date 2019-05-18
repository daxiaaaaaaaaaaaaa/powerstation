package com.jilian.powerstation.modul.activity;

import android.content.Intent;
import android.view.View;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.CommonActivity;

public class RegistActivity extends CommonActivity {

    @Override
    protected void createViewModel() {

    }

    @Override
    public int intiLayout() {
        return R.layout.activity_regist;
    }

    @Override
    public void initView() {
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
