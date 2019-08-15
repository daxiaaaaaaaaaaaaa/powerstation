package com.jilian.powerstation.modul.activity;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jilian.powerstation.Constant;
import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.common.dto.UserInfoDto;
import com.jilian.powerstation.common.event.MessageEvent;
import com.jilian.powerstation.common.event.UpdateUserMessage;
import com.jilian.powerstation.dialog.nicedialog.BaseNiceDialog;
import com.jilian.powerstation.dialog.nicedialog.NiceDialog;
import com.jilian.powerstation.dialog.nicedialog.ViewConvertListener;
import com.jilian.powerstation.dialog.nicedialog.ViewHolder;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.utils.GlideCacheUtil;
import com.jilian.powerstation.utils.PermissionsObserver;
import com.jilian.powerstation.utils.ToastUitl;
import com.jilian.powerstation.utils.selectphoto.SelectPhotoUtils;
import com.jilian.powerstation.views.CircularImageView;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyInfoActivity extends BaseActivity {
    private UserViewModel viewModel;
    private CircularImageView ivHead;
    private TextView tvEdit;
    private TextView tvUserName;
    private LinearLayout llUpdateName;
    private TextView tvName;
    private String path;
    //相机
    private final int FROM_CAPTURE = 10001;
    //相册
    private final int FROM_ALBUM = 10002;


    @Override
    protected void createViewModel() {
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_myinfo;
    }

    @Override
    public void initView() {
        setNormalTitle("Personal information", v -> finish());
        ivHead = (CircularImageView) findViewById(R.id.iv_head);
        tvEdit = (TextView) findViewById(R.id.tv_edit);
        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        llUpdateName = (LinearLayout) findViewById(R.id.ll_update_name);
        tvName = (TextView) findViewById(R.id.tv_name);
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
    public void initData() {
        initUserInfo();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.addActivity(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        MyApplication.removeActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initUserInfo() {
        viewModel.getUserInfo();
        viewModel.getUserliveData().observe(this, new Observer<BaseDto<UserInfoDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<UserInfoDto> userInfoDtoBaseDto) {
                if (userInfoDtoBaseDto.isSuccess()) {
                    if (EmptyUtils.isNotEmpty(userInfoDtoBaseDto.getData())) {
                        UserInfoDto userInfoDto = userInfoDtoBaseDto.getData();
                        Glide.with(MyInfoActivity.this).load(userInfoDto.getPhotopath())
                                .skipMemoryCache(true) // 不使用内存缓存
                                .diskCacheStrategy(DiskCacheStrategy.NONE)//// 不使用磁盘缓存
                                .error(R.drawable.img_head)
                                .placeholder(R.drawable.img_head)
                                .into(ivHead);
                        tvName.setText(userInfoDto.getUser_cname());
                        tvUserName.setText(userInfoDto.getUser_email());
                    }
                } else {
                    ToastUitl.showImageToastTips(userInfoDtoBaseDto.getMsg());
                }
            }
        });
    }

    @Override
    public void initListener() {
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectPhotoTypeDialog();
            }
        });
        llUpdateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, UpdateUerActivity.class);
                intent.putExtra("name", tvName.getText().toString());
                startActivity(intent);
            }
        });
        ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectPhotoTypeDialog();
            }
        });

    }


    /**
     * 选中照片
     */
    private void showSelectPhotoTypeDialog() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_photo_select)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        dialog.setOutCancel(false);

                        TextView btnOne = (TextView) holder.getView(R.id.btn_one);
                        TextView btnTwo = (TextView) holder.getView(R.id.btn_two);
                        TextView btnCancel = (TextView) holder.getView(R.id.btn_cancel);
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        //拍照
                        btnOne.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                RxPermissions.getInstance(MyInfoActivity.this).request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new PermissionsObserver() {
                                    @Override
                                    protected void onGetPermissionsSuccess() {
                                        SelectPhotoUtils.fromCapture(MyInfoActivity.this, Constant.FINALVALUE.FILE_PROVIDER, FROM_CAPTURE);
                                    }

                                    @Override
                                    protected void onGetPermissionsSuccessFailuer() {
                                        //ToastUitl.showImageToastFail("相机权限被拒绝，无法使用拍照功能");
                                    }
                                });

                            }
                        });
                        //相册
                        btnTwo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();

                                RxPermissions.getInstance(MyInfoActivity.this).request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new PermissionsObserver() {
                                    @Override
                                    protected void onGetPermissionsSuccess() {
                                        SelectPhotoUtils.fromAlbum(MyInfoActivity.this, Constant.FINALVALUE.FILE_PROVIDER, 1, FROM_ALBUM);
                                    }

                                    @Override
                                    protected void onGetPermissionsSuccessFailuer() {
                                        // ToastUitl.showImageToastFail("相机权限被拒绝，无法使用拍照功能");
                                    }
                                });

                            }

                        });

                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //图库
                case FROM_ALBUM:
                    List<Uri> uriList = SelectPhotoUtils.albumResult(data);
                    Uri uri = uriList.get(0);
                    path = SelectPhotoUtils.getRealPathFromURI(this, uri);
                    uploadFile();
                    break;
                //相机
                case FROM_CAPTURE:
                    path = SelectPhotoUtils.capturePathResult();
                    //ivHead.setImageBitmap(BitmapFactory.decodeFile(path));
                    uploadFile();
                    break;
            }

        }
    }

    /**
     * 上传图片
     */
    private void uploadFile() {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        List<File> fileList = new ArrayList<>();
        fileList.add(new File(path));
        getLoadingDialog().showDialog();
        viewModel.uploadHeadPortrait(fileList);
        viewModel.getUploadData().observe(this, new Observer<BaseDto>() {
            @Override
            public void onChanged(@Nullable BaseDto stringBaseDto) {
                getLoadingDialog().dismiss();
                if (stringBaseDto.getCode() == Constant.Server.SUCCESS_CODE) {
                    ToastUitl.showImageToastSuccess("update success");
                    MessageEvent messageEvent = new MessageEvent();
                    UpdateUserMessage message = new UpdateUserMessage();
                    message.setCode(200);
                    messageEvent.setUserMessage(message);
                    EventBus.getDefault().post(messageEvent);
                    initUserInfo();
                } else {
                    ToastUitl.showImageToastFailuer(stringBaseDto.getMsg());
                }
            }
        });
    }
}
