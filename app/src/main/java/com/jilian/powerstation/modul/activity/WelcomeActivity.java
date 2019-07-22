package com.jilian.powerstation.modul.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.jilian.powerstation.Constant;
import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.utils.PermissionsObserver;
import com.jilian.powerstation.utils.SPUtil;
import com.jilian.powerstation.utils.ToastUitl;
import com.tbruyelle.rxpermissions.RxPermissions;

public class WelcomeActivity extends FragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.addActivity(this);
        RxPermissions.getInstance(this).request(
                Manifest.permission.RECORD_AUDIO,//录音权限
                Manifest.permission.ACCESS_COARSE_LOCATION, //定位权限
                Manifest.permission.ACCESS_FINE_LOCATION,//定位权限
                Manifest.permission.WRITE_EXTERNAL_STORAGE,//写权限
                Manifest.permission.READ_EXTERNAL_STORAGE, //读权限
                Manifest.permission.CAMERA,//相机权限,
                Manifest.permission.READ_PHONE_STATE//相机权限


        ).subscribe(new PermissionsObserver() {
            @Override
            protected void onGetPermissionsSuccess() {
                WelcomeActivity.this.goToMain();
            }

            @Override
            protected void onGetPermissionsSuccessFailuer() {
                finish();
                ToastUitl.showImageToastTips("获取权限失败");
            }
        });

    }

    private void goToMain() {

        //判断 session是否存在
        String cookieStr = SPUtil.getData(Constant.SP_VALUE.SP, Constant.SP_VALUE.SESSION_ID, String.class, null);
        //如果 session 不存在 则需要登录
        if (TextUtils.isEmpty(cookieStr) || !cookieStr.contains("JSESSIONID" +
                "")) {
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        }
        //已经登录
        else {
            if (EmptyUtils.isNotEmpty(MyApplication.getInstance().getPowerDto())) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.putExtra("sn", MyApplication.getInstance().getPowerDto().getSn());
                intent.putExtra("name", MyApplication.getInstance().getPowerDto().getProductName());
                intent.putExtra("data", MyApplication.getInstance().getPowerDto());
                startActivity(intent);

            } else {
                startActivity(new Intent(this, EssListActivity.class));
            }


        }


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
