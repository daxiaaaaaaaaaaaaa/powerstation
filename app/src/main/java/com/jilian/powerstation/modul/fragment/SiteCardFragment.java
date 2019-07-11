package com.jilian.powerstation.modul.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.common.dto.PowerCardDto;
import com.jilian.powerstation.common.dto.PowerDto;
import com.jilian.powerstation.modul.viewmodel.PowerViewModel;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.DateUtil;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.utils.ToastUitl;

import java.util.Date;

/**
 * Created by cxz on 2019/6/2
 * <p>
 * Discrebe: 电站名片
 */
public class SiteCardFragment extends BaseFragment {
    private TextView tvSiteName;
    private TextView tvDeviceName;
    private TextView tvOne;
    private TextView tvPowerTotal;
    private TextView tvPowerVersion;
    private TextView tvOnlineTime;
    private TextView tvPowerIntroduction;


    private PowerDto powerDto;
    private PowerViewModel viewModel;

    @Override
    protected void loadData() {

    }

    @Override
    protected void createViewModel() {
        viewModel = ViewModelProviders.of(this).get(PowerViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_site_card;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tvSiteName = (TextView) view.findViewById(R.id.tv_site_name);
        tvDeviceName = (TextView) view.findViewById(R.id.tv_device_name);
        tvOne = (TextView) view.findViewById(R.id.tv_one);
        tvPowerTotal = (TextView) view.findViewById(R.id.tv_power_total);
        tvPowerVersion = (TextView) view.findViewById(R.id.tv_power_version);
        tvOnlineTime = (TextView) view.findViewById(R.id.tv_online_time);
        tvPowerIntroduction = (TextView) view.findViewById(R.id.tv_power_introduction);

    }

    @Override
    protected void initData() {
        powerDto = (PowerDto) getActivity().getIntent().getSerializableExtra("data");
        if (powerDto != null) {
            tvSiteName.setText(powerDto.getProductName());
            tvDeviceName.setText(powerDto.getProductName());
            tvPowerTotal.setText(powerDto.getHistoryPVproduction());
            getPowerCard();
        }


    }

    /**
     * 获取电站名片数据
     */
    private void getPowerCard() {
        showLoadingDialog();
        viewModel.getPowerCard(powerDto.getSn());
        viewModel.getPowerCardliveData().observe(this, new Observer<BaseDto<PowerCardDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<PowerCardDto> dtoBaseDto) {
                hideLoadingDialog();
                if (dtoBaseDto.isSuccess()) {
                    if (EmptyUtils.isNotEmpty(dtoBaseDto.getData())) {
                        initDataView(dtoBaseDto.getData());
                    }
                } else {
                    ToastUitl.showImageToastTips(dtoBaseDto.getMsg());
                }
            }
        });

    }

    /**
     * 展示卡片数据
     *
     * @param data
     */
    private void initDataView(PowerCardDto data) {
        tvOne.setText(data.getSpecification());//电站规格
        tvPowerVersion.setText(data.getVersion());//版本
        tvOnlineTime.setText(DateUtil.dateToString("yyyy/MM/dd HH:mm:ss", new Date(data.getTime())));
        tvPowerIntroduction.setText(data.getRemark());//电站简介

    }


    @Override
    protected void initListener() {
        tvPowerVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePowerFirmwareInfo();
            }
        });

    }

    /**
     * 更新固件
     */
    private void updatePowerFirmwareInfo() {
        showLoadingDialog();
        viewModel.updatePowerFirmwareInfo(powerDto.getSn());
        viewModel.getUpdateliveData().observe(this, new Observer<BaseDto<BaseResultDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<BaseResultDto> resultDtoBaseDto) {
                hideLoadingDialog();
                if (resultDtoBaseDto.isSuccess()) {
                    if ("1".equals(resultDtoBaseDto.getData().getResult())) {
                        getPowerCard();
                        ToastUitl.showImageToastTips("update success");
                    } else {
                        ToastUitl.showImageToastTips("update failuer");
                    }
                } else {
                    ToastUitl.showImageToastTips(resultDtoBaseDto.getMsg());
                }
            }
        });
    }


}
