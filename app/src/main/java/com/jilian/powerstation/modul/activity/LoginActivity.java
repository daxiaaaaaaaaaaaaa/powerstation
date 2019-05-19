package com.jilian.powerstation.modul.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.CommonActivity;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.factory.Factoty;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.ToastUitl;
import com.jilian.powerstation.utils.Utils;
import com.jilian.powerstation.views.ClearEditText;

/**
 * 登录fff
 * weishixiong
 * 2019-03-17
 *
 */
public class LoginActivity extends CommonActivity {
    private RelativeLayout rlCancel;
    private ClearEditText etPhone;
    private ClearEditText etPwd;
//    private RelativeLayout rlEye;
//    private ImageView ivEye;
    private TextView tvLoginRegister;
    private TextView tvLoginForgetPwd;
    private TextView tvLogin;
    private boolean mbDisplayFlg;

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
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        rlCancel = (RelativeLayout) findViewById(R.id.rl_cancel);
        etPhone = (ClearEditText) findViewById(R.id.et_phone);
        etPwd = (ClearEditText) findViewById(R.id.et_pwd);
//        rlEye = (RelativeLayout) findViewById(R.id.rl_eye);
//        ivEye = (ImageView) findViewById(R.id.iv_eye);
        tvLoginRegister = (TextView) findViewById(R.id.tv_login_register);
        tvLoginForgetPwd = (TextView) findViewById(R.id.tv_login_forget_pwd);
        tvLogin = (TextView) findViewById(R.id.tv_login);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        rlCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                login();
                startActivity(new Intent(LoginActivity.this,AddESSActivity.class));
            }
        });
        tvLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistActivity.class));
            }
        });
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(etPhone.getText().toString()) && !TextUtils.isEmpty(etPwd.getText().toString())) {
                    tvLogin.setBackgroundResource(R.drawable.shape_btn_login_normal);
                    tvLogin.setEnabled(true);
                } else {
                    tvLogin.setBackgroundResource(R.drawable.shape_btn_login_dark);
                    tvLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(etPhone.getText().toString()) && !TextUtils.isEmpty(etPwd.getText().toString())) {
                    tvLogin.setBackgroundResource(R.drawable.shape_btn_login_normal);
                    tvLogin.setEnabled(true);
                } else {
                    tvLogin.setBackgroundResource(R.drawable.shape_btn_login_dark);
                    tvLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//        rlEye.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!mbDisplayFlg) {
//                    // display password text, for example "123456"
//                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                    ivEye.setImageResource(R.drawable.image_login_open);
//                } else {
//                    // hide password, display "."
//                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    ivEye.setImageResource(R.drawable.image_login_close);
//                }
//                etPwd.setSelection(etPwd.getText().toString().length());//将光标移至文字末尾
//                mbDisplayFlg = !mbDisplayFlg;
//                etPwd.postInvalidate();
//
//            }
//        });

        tvLoginForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    /**
     * 登录
     */
    private void login() {
        if(!Utils.checkPhoneNo(etPhone.getText().toString())){
            ToastUitl.showImageToastTips(getResources().getString(R.string.error_phone));
            return;
        }
        tvLogin.setEnabled(false);
        Factoty.getViewModel(UserViewModel.class,LoginActivity.this).login(etPhone.getText().toString(),etPwd.getText().toString(),"");
//        Factoty.getViewModel(UserViewModel.class,LoginActivity.this).getLoginliveData().observe(this, new Observer<BaseDto<LoginDto>>() {
//            @Override
//            public void onChanged(@Nullable BaseDto<LoginDto> dto) {
//
//            }
//        });
        showLoadingDialog();
    }

}
