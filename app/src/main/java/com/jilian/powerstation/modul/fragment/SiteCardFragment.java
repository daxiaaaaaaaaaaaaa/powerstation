package com.jilian.powerstation.modul.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.PowerCardDto;
import com.jilian.powerstation.common.dto.PowerDto;
import com.jilian.powerstation.modul.viewmodel.PowerViewModel;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;

/**
 * Created by cxz on 2019/6/2
 * <p>
 * Discrebe: 电站名片
 */
public class SiteCardFragment extends BaseFragment {
    private TextView tvSiteName;
    private TextView tvDeviceName;
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
        tvPowerTotal = (TextView) view.findViewById(R.id.tv_power_total);
        tvPowerVersion = (TextView) view.findViewById(R.id.tv_power_version);
        tvOnlineTime = (TextView) view.findViewById(R.id.tv_online_time);
        tvPowerIntroduction = (TextView) view.findViewById(R.id.tv_power_introduction);
    }

    @Override
    protected void initData() {
        powerDto = (PowerDto) getActivity().getIntent().getSerializableExtra("data");
        if (powerDto!=null){
            tvSiteName.setText(powerDto.getProductName());
            tvDeviceName.setText(powerDto.getProductName());
            tvPowerTotal.setText(powerDto.getHistoryPVproduction());
            gegtPowerInfo();
        }

    }

    @Override
    protected void initListener() {

    }

    public void gegtPowerInfo() {
        /**
         * 我的电站列表
         */
        viewModel.getPowerCard(powerDto.getSn(), "");
        viewModel.getPowerCardliveData().observe(this, new Observer<BaseDto<PowerCardDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<PowerCardDto> powerCardDtoBaseDto) {
                tvPowerVersion.setText(powerCardDtoBaseDto.getData().getVersionCode());
                tvOnlineTime.setText(powerCardDtoBaseDto.getData().getVersionIntro());
                tvPowerIntroduction.setText(powerCardDtoBaseDto.getData().getVersionIntro());
            }
        });
    }

}
