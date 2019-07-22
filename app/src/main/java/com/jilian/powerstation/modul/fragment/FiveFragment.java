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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jilian.powerstation.Constant;
import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.UserInfoDto;
import com.jilian.powerstation.common.event.MessageEvent;
import com.jilian.powerstation.modul.activity.AboutActivity;
import com.jilian.powerstation.modul.activity.EssListActivity;
import com.jilian.powerstation.modul.activity.LoginActivity;
import com.jilian.powerstation.modul.activity.MyInfoActivity;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


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
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        ivHead = (CircularImageView) view.findViewById(R.id.iv_head);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvId = (TextView) view.findViewById(R.id.tv_id);
        tvChangePower = (TextView) view.findViewById(R.id.tv_change_power);
        tvChangePwd = (TextView) view.findViewById(R.id.tv_change_pwd);
        tvAbout = (View) view.findViewById(R.id.tv_about);
        userLogout = (TextView) view.findViewById(R.id.user_logout);
        userInfoEditor = (ImageView) view.findViewById(R.id.user_info_editor);

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
                        Glide.with(getmActivity()).load(userInfoDto.getPhotopath())
                                .skipMemoryCache(true) // 不使用内存缓存
                                .diskCacheStrategy(DiskCacheStrategy.NONE)//// 不使用磁盘缓存
                                .into(ivHead);
                        tvName.setText(userInfoDto.getUser_cname());
                        tvId.setText(userInfoDto.getUser_email());
                    }
                } else {
                    ToastUitl.showImageToastTips(userInfoDtoBaseDto.getMsg());
                }
            }
        });
    }

    /**
     * //监听外来是否要去成功的界面
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        /* Do something */
        if (EmptyUtils.isNotEmpty(event)
                && EmptyUtils.isNotEmpty(event.getUserMessage())
                && event.getUserMessage().getCode() == 200
        ) {
            initUserInfo();
        }
    }

    @Override
    protected void initListener() {


        ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getmActivity(), MyInfoActivity.class);
                startActivity(intent);
            }
        });
        userInfoEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getmActivity(), UpdateUerActivity.class);
                intent.putExtra("name", tvName.getText().toString());
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
