package com.jilian.powerstation.modul.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jilian.powerstation.Constant;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.AlarmInfoDto;
import com.jilian.powerstation.common.dto.PowerInfoDetailDto;
import com.jilian.powerstation.modul.activity.MainActivity;
import com.jilian.powerstation.modul.activity.WarningDetailActivity;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.utils.StatusBarUtil;
import com.jilian.powerstation.utils.ToastUitl;


public class OneFragment extends BaseFragment {

    private UserViewModel userViewModel;
    private TextView tvNumber1;
    private TextView tvNumber2;
    private TextView tvNumber3;
    private TextView tvNumber4;
    private TextView tvNumber5;
    private TextView tvNumber6;
    private MainActivity activity;


    @Override
    protected void loadData() {
        StatusBarUtil.setStatusBarMode(getmActivity(), true, R.color.white);
    }


    @Override
    protected void createViewModel() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        activity = (MainActivity) getmActivity();
        tvNumber1 = (TextView) view.findViewById(R.id.tv_number_1);
        tvNumber2 = (TextView) view.findViewById(R.id.tv_number_2);
        tvNumber3 = (TextView) view.findViewById(R.id.tv_number_3);
        tvNumber4 = (TextView) view.findViewById(R.id.tv_number_4);
        tvNumber5 = (TextView) view.findViewById(R.id.tv_number_5);
        tvNumber6 = (TextView) view.findViewById(R.id.tv_number_6);

        setrightImageOne(R.drawable.image_right_one, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        setrightImageTwo(R.drawable.image_right_two, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //警告
                getPowerAlarmInfo();

            }
        });
        setNormalTitle(activity.getData().getProductName(), v -> getActivity().finish());

    }

    /**
     * 电站详情 获取警告
     */
    private void getPowerAlarmInfo() {
        showLoadingDialog();
        userViewModel.getPowerAlarmInfo(getActivity().getIntent().getStringExtra("sn"));
        userViewModel.getAlarmliveData().observe(this, new Observer<BaseDto<AlarmInfoDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<AlarmInfoDto> alarmInfoDtoBaseDto) {
                hideLoadingDialog();
                if (alarmInfoDtoBaseDto.isSuccess()) {
                    if (alarmInfoDtoBaseDto.getData().getTotal() > 0) {

                        Intent intent = new Intent(getmActivity(), WarningDetailActivity.class);
                        intent.putExtra("sn", getmActivity().getIntent().getStringExtra("sn"));
                        intent.putExtra("type", Constant.BATTERY_TYPE);
                        getmActivity().startActivity(intent);
                    } else {
                        ToastUitl.showImageToastTips("No warning message yet");
                    }
                } else {
                    ToastUitl.showImageToastTips(alarmInfoDtoBaseDto.getMsg());
                }
            }
        });
    }

    @Override
    protected void initData() {

        getPowerInfo();
    }

    /**
     * 获取电站详情
     */
    private void getPowerInfo() {
        userViewModel.getPowerInfo(getActivity().getIntent().getStringExtra("sn"));
        userViewModel.getPowerliveData().observe(this, new Observer<BaseDto<PowerInfoDetailDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<PowerInfoDetailDto> detailDtoBaseDto) {
                if (detailDtoBaseDto.isSuccess()) {
                    if (EmptyUtils.isNotEmpty(detailDtoBaseDto)) {
                        initDetailView(detailDtoBaseDto.getData());
                    }
                } else {
                    ToastUitl.showImageToastTips(detailDtoBaseDto.getMsg());
                }
            }
        });

    }

    /**
     * 初始化電站詳情视图
     *
     * @param data
     */
    private void initDetailView(PowerInfoDetailDto data) {
        Log.e(TAG, "initDetailView: " + data.toString());
        tvNumber1.setText(data.getToday_pv_production());
        tvNumber2.setText(data.getToday_consumption());
        tvNumber3.setText(data.getToday_own_consumption_rate());
        tvNumber4.setText(data.getToday_own_consumption());
        tvNumber5.setText(data.getHistory_estimated_refund());
        tvNumber6.setText(data.getHistory_carbon_offset());
    }


    @Override
    protected void initListener() {

    }


}
