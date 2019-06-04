package com.jilian.powerstation.modul.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jilian.powerstation.Constant;
import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.CommonActivity;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.RxTimerUtil;
import com.jilian.powerstation.utils.ToastUitl;
import com.jilian.powerstation.views.ClearEditText;

import java.io.File;

public class ForgetPasswordActivity extends BaseActivity {
    private LinearLayout llTop;
    private ClearEditText etEmail;
    private ClearEditText etCode;
    private TextView tvOk;
    private LinearLayout llBottom;
    private TextView tvSubmit;
    private ClearEditText etPwd;
    private ClearEditText etPwdOk;
    private TextView tvGetCode;
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
        return R.layout.activity_forget_password;
    }

    @Override
    public void initView() {
        setNormalTitle("Forget password", v -> finish());
        llTop = (LinearLayout) findViewById(R.id.ll_top);
        etEmail = (ClearEditText) findViewById(R.id.et_email);
        etCode = (ClearEditText) findViewById(R.id.et_code);
        tvOk = (TextView) findViewById(R.id.tv_ok);
        llBottom = (LinearLayout) findViewById(R.id.ll_bottom);
        tvSubmit = (TextView) findViewById(R.id.tv_submit);
        etPwd = (ClearEditText) findViewById(R.id.et_pwd);
        etPwdOk = (ClearEditText) findViewById(R.id.et_pwd_ok);
        tvGetCode = (TextView) findViewById(R.id.tv_get_code);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                initOkEnable();
            }
        });
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                initOkEnable();
            }
        });
        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                initSubEnable();
            }
        });
        etPwdOk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                initSubEnable();
            }
        });

        tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMsgCode();

            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llBottom.setVisibility(View.VISIBLE);
                llTop.setVisibility(View.GONE);
            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(etPwd.getText().toString().equals(etPwdOk.getText().toString()))) {
                    ToastUitl.showImageToastTips("Two different passwords");
                    return;
                }
                forgetAndResetPassword();

            }
        });

    }

    /**
     * 忘记密码
     */
    private void forgetAndResetPassword() {
        showLoadingDialog();
        userViewModel.forgetAndResetPassword(etEmail.getText().toString(), etCode.getText().toString(), etPwdOk.getText().toString());
        userViewModel.getForgetliveData().observe(this, new Observer<BaseDto>() {
            @Override
            public void onChanged(@Nullable BaseDto baseDto) {
                hideLoadingDialog();
                if (baseDto.isSuccess()) {
                    ToastUitl.showImageToastTips("Password changed successfully");
                    finish();
                } else {
                    ToastUitl.showImageToastTips(baseDto.getMsg());
                }
            }
        });

    }

    private void initSubEnable() {
        if (TextUtils.isEmpty(etPwd.getText().toString()) || TextUtils.isEmpty(etPwdOk.getText().toString())) {
            tvSubmit.setEnabled(false);
            tvSubmit.setBackgroundResource(R.drawable.shape_btn_login_dark);
        } else {
            tvSubmit.setEnabled(true);
            tvSubmit.setBackgroundResource(R.drawable.shape_btn_login_normal);
        }
    }

    private void initOkEnable() {
        if (TextUtils.isEmpty(etEmail.getText().toString()) || TextUtils.isEmpty(etCode.getText().toString())) {
            tvOk.setEnabled(false);
            tvOk.setBackgroundResource(R.drawable.shape_btn_login_dark);
        } else {
            tvOk.setEnabled(true);
            tvOk.setBackgroundResource(R.drawable.shape_btn_login_normal);
        }
    }

    private int time;

    /**
     * 获取验证码
     */
    private void getMsgCode() {
        getLoadingDialog().showDialog();
        userViewModel.getVerificationCode(etEmail.getText().toString());
        userViewModel.getCodeliveData().observe(ForgetPasswordActivity.this, new Observer<BaseDto<BaseResultDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<BaseResultDto> dto) {
                getLoadingDialog().dismiss();
                if (dto.getCode() == Constant.Server.SUCCESS_CODE) {
                    time = Integer.parseInt(dto.getData().getResult()) * 60;
                    ToastUitl.showImageToastSuccess(dto.getMsg());
                    tvGetCode.setText("验证码(" + String.valueOf(time + "s") + ")");
                    tvGetCode.setEnabled(false);
                    tvGetCode.setTextColor(getResources().getColor(R.color.color_blue_dark));
                    RxTimerUtil.interval(1000, new RxTimerUtil.IRxNext() {
                        @Override
                        public void doNext() {

                            if (time == 1) {
                                tvGetCode.setEnabled(true);
                                tvGetCode.setTextColor(getResources().getColor(R.color.color_blue));
                                tvGetCode.setText("获取验证码");
                                RxTimerUtil.cancel();
                                time = Integer.parseInt(dto.getData().getResult()) * 60;
                                //设置手机号可编辑
                                etEmail.setEnabled(true);
                                //恢复图标
                                etEmail.setClearIconVisible(true);
                            } else {
                                time--;
                                tvGetCode.setText("验证码(" + String.valueOf(time + "s") + ")");
                                tvGetCode.setEnabled(false);
                                tvGetCode.setTextColor(getResources().getColor(R.color.color_blue_dark));
                                //设置手机号不可编辑
                                etEmail.setEnabled(false);
                                //清除图标
                                etEmail.setClearIconVisible(false);
                            }

                        }
                    });
                } else {
                    ToastUitl.showImageToastTips(dto.getMsg());
                }
            }
        });
    }


}
