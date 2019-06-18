package com.jilian.powerstation.modul.fragment;


import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.jilian.powerstation.modul.activity.EssListActivity;
import com.jilian.powerstation.modul.activity.LoginActivity;
import com.jilian.powerstation.modul.activity.StationDetailActivity;
import com.jilian.powerstation.modul.activity.UpdatePwdActivity;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.utils.PinziDialogUtils;
import com.jilian.powerstation.utils.RxTimerUtil;
import com.jilian.powerstation.utils.SPUtil;
import com.jilian.powerstation.utils.StatusBarUtil;
import com.jilian.powerstation.utils.ToastUitl;
import com.jilian.powerstation.views.CircularImageView;

import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;


public class FiveFragment extends BaseFragment {

    private UserViewModel viewModel;
    private CircularImageView ivHead;
    private TextView tvName;
    private TextView tvId;
    private TextView tvChangePower;
    private TextView tvChangePwd;
    private View tvAbout;
    private TextView userLogout;
    private ImageView img_view;


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

    public void shareImg(View view) {
        //打开图像缓存
        view.setDrawingCacheEnabled(true);
// 必须要调用measure和layout方法才能成功保存可视组件的截图到png图像文件
// 测量View的大小
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
// 发送位置和尺寸到View及其所有的子View
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        try {
// 获取可视组件的截图
            Bitmap bitmap = view.getDrawingCache();
// 将截图保存在SD卡根目录的test.png图像文件中
            FileOutputStream fos = new FileOutputStream("/sdcard/test.png");
// 将Bitmap对象中的图像数据压缩成png格式的图像数据，并将这些数据保存在test.png文件中
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
// 关闭文件输出流
            fos.close();
            img_view.setImageBitmap(bitmap);
        } catch (Exception e) {
        }

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
        img_view = view.findViewById(R.id.img_view);
        view.findViewById(R.id.user_info_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareImg(getView());
            }
        });
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
