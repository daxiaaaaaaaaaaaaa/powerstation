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
import com.jilian.powerstation.common.event.MessageEvent;
import com.jilian.powerstation.common.event.UpdateUserMessage;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.ToastUitl;

import org.greenrobot.eventbus.EventBus;

/**
 * 修改用户名
 */
public class UpdateUerActivity extends BaseActivity {
    private EditText etName;
    private TextView tvOk;
    private UserViewModel userViewModel;


    @Override
    protected void createViewModel() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_updat_user;
    }

    @Override
    public void initView() {
        setNormalTitle("Modify User Name", v -> finish());
        etName = (EditText) findViewById(R.id.et_name);
        tvOk = (TextView) findViewById(R.id.tv_ok);
        etName.setText(getIntent().getStringExtra("name"));
        initButtonEnable();

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etName.getText().toString())) {
                    return;
                }
                updateUser();
            }
        });

        etName.addTextChangedListener(new TextWatcher() {
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

    /**
     * 修改用户名
     */
    private void updateUser() {
        showLoadingDialog();
        userViewModel.updateUserInfo(etName.getText().toString());
        userViewModel.getUpdateUserliveData().observe(this, new Observer<BaseDto>() {
            @Override
            public void onChanged(@Nullable BaseDto baseDto) {
                hideLoadingDialog();
                if (baseDto.isSuccess()) {
                    MessageEvent messageEvent = new MessageEvent();
                    UpdateUserMessage message = new UpdateUserMessage();
                    message.setCode(200);
                    messageEvent.setUserMessage(message);
                    EventBus.getDefault().post(messageEvent);

                    ToastUitl.showImageToastSuccess("Modify the success");
                } else {
                    ToastUitl.showImageToastTips(baseDto.getMsg());
                }
                finish();
            }
        });
    }


    private void initButtonEnable() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            tvOk.setEnabled(false);
            tvOk.setBackgroundResource(R.drawable.shape_btn_login_dark);
        } else {
            tvOk.setEnabled(true);
            tvOk.setBackgroundResource(R.drawable.shape_btn_login_normal);
        }
    }
}
