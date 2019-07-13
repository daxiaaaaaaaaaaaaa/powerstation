package com.jilian.powerstation.modul.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseEventMsg;
import com.jilian.powerstation.common.event.AddPowersEvent;
import com.jilian.powerstation.event.RxBus;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.utils.ToastUitl;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class AddESSActivity extends BaseActivity {
    private TextView addEssSerial;
    private EditText addEssName;
    private TextView tvOk;
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
        return R.layout.activity_add_ess;
    }

    @Override
    public void initView() {
        setNormalTitle("Add an ESS", v -> finish());
        addEssSerial = (TextView) findViewById(R.id.add_ess_serial);
        addEssName = (EditText) findViewById(R.id.add_ess_name);
        tvOk = (TextView) findViewById(R.id.tv_ok);
    }

    @Override
    public void initData() {
        addEssSerial.setText(getIntent().getStringExtra(CodeUtils.RESULT_STRING));


    }

    @Override
    public void initListener() {
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPowerInfo();
            }
        });
        addEssName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (EmptyUtils.isNotEmpty(addEssSerial.getText().toString()) && EmptyUtils.isNotEmpty(addEssSerial.getText().toString())) {
                    tvOk.setEnabled(true);
                    tvOk.setBackgroundResource(R.drawable.shape_btn_login_normal);
                } else {
                    tvOk.setEnabled(false);
                    tvOk.setBackgroundResource(R.drawable.shape_btn_login_dark);
                }
            }
        });
    }

    /**
     * 添加电站
     */
    private void addPowerInfo() {
        showLoadingDialog();
        userViewModel.addPowerInfo(addEssSerial.getText().toString(), addEssName.getText().toString());
        userViewModel.getAddPowerliveData().observe(this, new Observer<BaseDto>() {
            @Override
            public void onChanged(@Nullable BaseDto baseDto) {
                hideLoadingDialog();
                if (baseDto.isSuccess()) {
                    ToastUitl.showImageToastSuccess("添加成功");
                    AddPowersEvent event = new AddPowersEvent();
                    event.setCode(200);
                    BaseEventMsg baseEventMsg = new BaseEventMsg();
                    baseEventMsg.setAddPowersEvent(event);
                    RxBus.getInstance().post(baseEventMsg);
                    finish();
                } else {
                    ToastUitl.showImageToastSuccess(baseDto.getMsg());
                }

            }
        });
    }
}
