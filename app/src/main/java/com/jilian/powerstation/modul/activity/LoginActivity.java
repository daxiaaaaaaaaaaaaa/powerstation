package com.jilian.powerstation.modul.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Paint;
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

import com.jilian.powerstation.Constant;
import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.CommonActivity;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.factory.Factoty;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.SPUtil;
import com.jilian.powerstation.utils.ToastUitl;
import com.jilian.powerstation.utils.Utils;
import com.jilian.powerstation.views.ClearEditText;

/**
 * 登录fff
 * weishixiong
 * 2019-03-17
 */
public class LoginActivity extends BaseActivity {
    private RelativeLayout rlCancel;
    private ClearEditText etMail;
    private ClearEditText etPwd;
    //    private RelativeLayout rlEye;
//    private ImageView ivEye;
    private TextView tvLoginRegister;
    private TextView tvLoginForgetPwd;
    private TextView tvLogin;
    private boolean mbDisplayFlg;
    private UserViewModel userViewModel;

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
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        rlCancel = (RelativeLayout) findViewById(R.id.rl_cancel);
        etMail = (ClearEditText) findViewById(R.id.et_mail);
        etPwd = (ClearEditText) findViewById(R.id.et_pwd);
//        rlEye = (RelativeLayout) findViewById(R.id.rl_eye);
//        ivEye = (ImageView) findViewById(R.id.iv_eye);
        tvLoginRegister = (TextView) findViewById(R.id.tv_login_register);
        tvLoginForgetPwd = (TextView) findViewById(R.id.tv_login_forget_pwd);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        tvLoginForgetPwd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
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
                login();

            }
        });
        tvLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
            }
        });
        etMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(etMail.getText().toString()) && !TextUtils.isEmpty(etPwd.getText().toString())) {
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
                if (!TextUtils.isEmpty(etMail.getText().toString()) && !TextUtils.isEmpty(etPwd.getText().toString())) {
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


        tvLoginForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
            }
        });

    }

    /**
     * 登录
     */
    private void login() {
        //校验邮箱
//        if(!Utils.checkPhoneNo(etMail.getText().toString())){
//            ToastUitl.showImageToastTips(getResources().getString(R.string.error_phone));
//            return;
//        }
        showLoadingDialog();
        tvLogin.setEnabled(false);
        userViewModel.login(etMail.getText().toString(), etPwd.getText().toString());
        userViewModel.getLoginliveData().observe(this, new Observer<BaseDto<LoginDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<LoginDto> loginDtoBaseDto) {
                hideLoadingDialog();
                tvLogin.setEnabled(true);
                if (loginDtoBaseDto.isSuccess()) {
                    ToastUitl.showImageToastSuccess("Login successful");
                    //保存token到前端
                    SPUtil.putData(Constant.SP_VALUE.SP, Constant.SP_VALUE.TOKEN, loginDtoBaseDto.getData().getToken());
                    startActivity(new Intent(LoginActivity.this, EssListActivity.class));
                    finish();
                } else {
                    ToastUitl.showImageToastTips(loginDtoBaseDto.getMsg());
                }
            }
        });
        showLoadingDialog();
    }

}
