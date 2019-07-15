package com.jilian.powerstation.modul.fragment;


import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jilian.powerstation.Constant;
import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.UserInfoDto;
import com.jilian.powerstation.modul.activity.AboutActivity;
import com.jilian.powerstation.modul.activity.EssListActivity;
import com.jilian.powerstation.modul.activity.LoginActivity;
import com.jilian.powerstation.modul.activity.UpdatePwdActivity;
import com.jilian.powerstation.modul.activity.UpdateUerActivity;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.utils.PinziDialogUtils;
import com.jilian.powerstation.utils.RxTimerUtil;
import com.jilian.powerstation.utils.SPUtil;
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
    private ImageView userInfoEditor;




    @Override
    protected void loadData() {
        //根据状态栏颜色来决定状态栏文字用黑色还是白色
        StatusBarUtil.setStatusBarMode(getmActivity(), false, R.color.colorPrimary);

        initUserInfo();
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

        ivHead = (CircularImageView) view.findViewById(R.id.iv_head);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvId = (TextView) view.findViewById(R.id.tv_id);
        tvChangePower = (TextView) view.findViewById(R.id.tv_change_power);
        tvChangePwd = (TextView) view.findViewById(R.id.tv_change_pwd);
        tvAbout = (View) view.findViewById(R.id.tv_about);
        userLogout = (TextView) view.findViewById(R.id.user_logout);
        userInfoEditor = (ImageView)view. findViewById(R.id.user_info_editor);

    }

    @Override
    protected void initData() {

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
        userInfoEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getmActivity(), UpdateUerActivity.class);
                intent.putExtra("name",tvName.getText().toString());

                startActivity(intent);
            }
        });
        tvAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getmActivity(), AboutActivity.class));
            }
        });
        tvChangePower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getmActivity(), EssListActivity.class));
            }
        });
        tvChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getmActivity(), UpdatePwdActivity.class));
            }
        });
        userLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });

    }

    /**
     * 退出登录对话框
     */
    private void showLogoutDialog() {
        Dialog dialog = PinziDialogUtils.getDialogNotTouchOutside(getmActivity(), R.layout.dialog_confirm);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) dialog.findViewById(R.id.tv_content);
        TextView tvNo = (TextView) dialog.findViewById(R.id.tv_no);
        TextView tvOk = (TextView) dialog.findViewById(R.id.tv_ok);

        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                getLoadingDialog().showDialog();
                RxTimerUtil.timer(500, new RxTimerUtil.IRxNext() {
                    @Override
                    public void doNext() {
                        getLoadingDialog().dismiss();
                        MyApplication.getInstance().setPowerDto(null);
                        ToastUitl.showImageToastSuccess("Exit the success");
                        SPUtil.clearData(Constant.SP_VALUE.SP);
                        startActivity(new Intent(getmActivity(), LoginActivity.class));
                        getmActivity().finish();
                        MyApplication.clearAllActivitys();
                    }
                });
            }
        });
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();


    }
}
