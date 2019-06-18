package com.jilian.powerstation.modul.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.PowerDto;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;

/**
 * Created by cxz on 2019/6/2
 * <p>
 * Discrebe: 电站名片
 */
public class SiteCardFragment extends BaseFragment {
    private PowerDto powerDto;
    private UserViewModel viewModel;

    @Override
    protected void loadData() {

    }

    @Override
    protected void createViewModel() {
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_site_card;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        powerDto = (PowerDto) getActivity().getIntent().getSerializableExtra("data");
        gegtPowerInfo();
    }

    @Override
    protected void initListener() {

    }

    public void gegtPowerInfo() {
        /**
         * 我的电站列表
         */
        viewModel.getPowerInfo(powerDto.getSn(), "");
        viewModel.getPowerliveData().observe(this, new Observer<BaseDto<PowerDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<PowerDto> powerDtoBaseDto) {

            }
        });
    }

}
