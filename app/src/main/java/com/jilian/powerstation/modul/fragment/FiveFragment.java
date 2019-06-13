package com.jilian.powerstation.modul.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.UserInfoDto;
import com.jilian.powerstation.modul.activity.EssListActivity;
import com.jilian.powerstation.modul.activity.StationDetailActivity;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.utils.StatusBarUtil;
import com.jilian.powerstation.utils.ToastUitl;
import com.jilian.powerstation.views.CircularImageView;


public class FiveFragment extends BaseFragment {

    private UserViewModel viewModel;
    private CircularImageView ivHead;
    private TextView tvName;
    private TextView tvId;
    private TextView tvChangePower;
    private TextView tvChangePwd;
    private View tvAbout;
    private TextView userLogout;


    @Override
    protected void loadData() {
        //根据状态栏颜色来决定状态栏文字用黑色还是白色
        StatusBarUtil.setStatusBarMode(getmActivity(), false, R.color.colorPrimary);
    }


    @Override
    protected void createViewModel() {
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_five;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.user_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(), IntelligentDeviceActivity.class));
                startActivity(new Intent(getContext(), StationDetailActivity.class));
            }
        });

        ivHead = (CircularImageView) view.findViewById(R.id.iv_head);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvId = (TextView) view.findViewById(R.id.tv_id);
        tvChangePower = (TextView) view.findViewById(R.id.tv_change_power);
        tvChangePwd = (TextView) view.findViewById(R.id.tv_change_pwd);
        tvAbout = (View) view.findViewById(R.id.tv_about);
        userLogout = (TextView) view.findViewById(R.id.user_logout);
    }

    @Override
    protected void initData() {
        initUserInfo();
    }

    private void initUserInfo() {
        viewModel.getUserInfo();
        viewModel.getUserliveData().observe(this, new Observer<BaseDto<UserInfoDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<UserInfoDto> userInfoDtoBaseDto) {
                if (userInfoDtoBaseDto.isSuccess()) {
                    if (EmptyUtils.isNotEmpty(userInfoDtoBaseDto.getData())) {
                        UserInfoDto userInfoDto = userInfoDtoBaseDto.getData();
                        Glide.with(getmActivity()).
                                load(userInfoDto.getPhotopath()).error(R.drawable.ic_launcher_background) //异常时候显示的图片
                                .placeholder(R.drawable.ic_launcher_background) //加载成功前显示的图片
                                .fallback(R.drawable.ic_launcher_background) //url为空的时候,显示的图片
                                .into(ivHead);//在RequestBuilder 中使用自定义的ImageViewTarge

                        tvName.setText(userInfoDto.getUser_cname());
                        tvId.setText(userInfoDto.getUser_email());
                    }
                } else {
                    ToastUitl.showImageToastTips(userInfoDtoBaseDto.getMsg());
                }
            }
        });
    }

    @Override
    protected void initListener() {
        tvChangePower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getmActivity(), EssListActivity.class));
                getmActivity().finish();
            }
        });
        tvChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getmActivity(), Resr.class));
            }
        });
    }


}
