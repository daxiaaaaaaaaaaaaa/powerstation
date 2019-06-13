package com.jilian.powerstation.modul.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.ToastUitl;

public class UpdatePwdActivity extends BaseActivity {
    private EditText etOldPwd;
    private EditText etNewPwd;
    private EditText etConfirmPwd;
    private TextView tvOk;
    private UserViewModel userViewModel;

    @Override
    protected void createViewModel() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_updatepwd;
    }

    @Override
    public void initView() {
        setNormalTitle("Change Password", v -> finish());
        etOldPwd = (EditText) findViewById(R.id.et_old_pwd);
        etNewPwd = (EditText) findViewById(R.id.et_new_pwd);
        etConfirmPwd = (EditText) findViewById(R.id.et_confirm_pwd);
        tvOk = (TextView) findViewById(R.id.tv_ok);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(etNewPwd.getText().toString().equals(etConfirmPwd.getText().toString()))) {
                    ToastUitl.showImageToastTips("Old and new passwords don't match");
                    return;
                }
                updatePwd();
            }
        });
        etOldPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                initButtonEnable();
            }
        });
        etNewPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                initButtonEnable();
            }
        });
        etConfirmPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                initButtonEnable();
            }
        });

    }

    private void updatePwd() {
        showLoadingDialog();
        userViewModel.resetPassword(etOldPwd.getText().toString(), etNewPwd.getText().toString(), etConfirmPwd.getText().toString());
        userViewModel.getResetPwdliveData().observe(this, new Observer<BaseDto>() {
            @Override
            public void onChanged(@Nullable BaseDto baseDto) {
                hideLoadingDialog();
                if (baseDto.isSuccess()) {
                    finish();
                    ToastUitl.showImageToastSuccess("Password changed successfully");
                } else {
                    ToastUitl.showImageToastTips(baseDto.getMsg());
                }
            }
        });
    }

    private void initButtonEnable() {
        if (TextUtils.isEmpty(etOldPwd.getText().toString())
                || TextUtils.isEmpty(etNewPwd.getText().toString())
                || TextUtils.isEmpty(etConfirmPwd.getText().toString())) {
            tvOk.setEnabled(false);
            tvOk.setBackgroundResource(R.drawable.shape_btn_login_dark);
        } else {
            tvOk.setEnabled(true);
            tvOk.setBackgroundResource(R.drawable.shape_btn_login_normal);
        }
    }
}
