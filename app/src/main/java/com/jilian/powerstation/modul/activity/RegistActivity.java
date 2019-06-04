package com.jilian.powerstation.modul.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jilian.powerstation.Constant;
import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseMainActivity;
import com.jilian.powerstation.base.CommonActivity;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.factory.Factoty;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.RxTimerUtil;
import com.jilian.powerstation.utils.ToastUitl;
import com.jilian.powerstation.utils.Utils;
import com.jilian.powerstation.views.ClearEditText;

public class RegistActivity extends BaseActivity {
    private UserViewModel userViewModel;
    private ClearEditText etUserName;
    private ClearEditText etEmail;
    private ClearEditText etCode;
    private ClearEditText etPwd;
    private ClearEditText etPwdOk;

    private int time;
    private TextView tvOk;
    private TextView tvGetCode;


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
        return R.layout.activity_regist;
    }

    @Override
    public void initView() {
        setNormalTitle("Register", v -> finish());
        etUserName = (ClearEditText) findViewById(R.id.et_user_name);
        etEmail = (ClearEditText) findViewById(R.id.et_email);
        etCode = (ClearEditText) findViewById(R.id.et_code);
        etPwd = (ClearEditText) findViewById(R.id.et_pwd);
        etPwdOk = (ClearEditText) findViewById(R.id.et_pwd_ok);
        tvOk = (TextView) findViewById(R.id.tv_ok);
        tvGetCode = (TextView) findViewById(R.id.tv_get_code);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

        etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                updateEnableStatus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etPwdOk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                updateEnableStatus();
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

                updateEnableStatus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                updateEnableStatus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(etEmail.getText().toString())) {
                    tvGetCode.setEnabled(false);
                    tvGetCode.setTextColor(getResources().getColor(R.color.color_blue_dark));
                } else {
                    tvGetCode.setEnabled(true);
                    tvGetCode.setTextColor(getResources().getColor(R.color.color_blue));
                }
                updateEnableStatus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //此处校验邮箱

//                if (!Utils.checkPhoneNo(etEmail.getText().toString())) {
//                    ToastUitl.showImageToastTips(getResources().getString(R.string.error_phone));
//                    return;
//                }
                getMsgCode();

            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(etPwd.getText().toString().equals(etPwdOk.getText().toString()))) {
                    ToastUitl.showImageToastTips("Two different passwords");
                    return;
                }
                regist();
            }
        });
    }

    /**
     * 注册
     */
    private void regist() {
        showLoadingDialog();
        userViewModel.addUserInfo(etUserName.getText().toString(), etEmail.getText().toString(), etCode.getText().toString(), etPwdOk.getText().toString());
        userViewModel.getAddUserliveData().observe(this, new Observer<BaseDto>() {
            @Override
            public void onChanged(@Nullable BaseDto baseDto) {
                hideLoadingDialog();
                finish();
                if (baseDto.isSuccess()) {
                    ToastUitl.showImageToastTips("Registered successfully");
                } else {
                    ToastUitl.showImageToastTips(baseDto.getMsg());
                }
            }
        });
    }

    /**
     * 获取验证码
     */
    private void getMsgCode() {
        getLoadingDialog().showDialog();
        userViewModel.getVerificationCode(etEmail.getText().toString());
        userViewModel.getCodeliveData().observe(RegistActivity.this, new Observer<BaseDto<BaseResultDto>>() {
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


    /**
     * 更新按钮的可用状态
     */
    private void updateEnableStatus() {
        if (!TextUtils.isEmpty(etUserName.getText().toString())
                && !TextUtils.isEmpty(etPwd.getText().toString())
                && !TextUtils.isEmpty(etCode.getText().toString())
                && !TextUtils.isEmpty(etEmail.getText().toString())
                && !TextUtils.isEmpty(etPwdOk.getText().toString())
                ) {
            tvOk.setEnabled(true);
            tvOk.setBackgroundResource(R.drawable.shape_btn_login_normal);
        } else {
            tvOk.setEnabled(false);
            tvOk.setBackgroundResource(R.drawable.shape_btn_login_dark);
        }
    }
}
