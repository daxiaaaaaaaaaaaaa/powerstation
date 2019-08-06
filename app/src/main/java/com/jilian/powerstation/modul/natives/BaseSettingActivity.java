package com.jilian.powerstation.modul.natives;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.dialog.nicedialog.BaseNiceDialog;
import com.jilian.powerstation.dialog.nicedialog.NiceDialog;
import com.jilian.powerstation.dialog.nicedialog.ViewConvertListener;
import com.jilian.powerstation.dialog.nicedialog.ViewHolder;
import com.jilian.powerstation.utils.ToastUitl;

public class BaseSettingActivity extends BaseActivity {
    private LinearLayout llOne;
    private LinearLayout llTwo;
    private TextView tvOne;
    private LinearLayout llThree;
    private RelativeLayout llFour;
    private EditText etOne;
    private RelativeLayout llFive;
    private EditText etTwo;
    private RelativeLayout llSix;
    private RelativeLayout llSeven;
    private EditText etThree;
    private RelativeLayout llEight;
    private EditText etFour;
    private RelativeLayout llNine;
    private EditText etFive;
    private ImageView ivOpenClose;




    @Override
    protected void createViewModel() {

    }

    @Override
    public int intiLayout() {
        return R.layout.activity_basesetting;
    }

    @Override
    public void initView() {
        llOne = (LinearLayout) findViewById(R.id.ll_one);
        llTwo = (LinearLayout) findViewById(R.id.ll_two);
        tvOne = (TextView) findViewById(R.id.tv_one);
        llThree = (LinearLayout) findViewById(R.id.ll_three);
        llFour = (RelativeLayout) findViewById(R.id.ll_four);
        etOne = (EditText) findViewById(R.id.et_one);
        llFive = (RelativeLayout) findViewById(R.id.ll_five);
        etTwo = (EditText) findViewById(R.id.et_two);
        llSix = (RelativeLayout) findViewById(R.id.ll_six);
        llSeven = (RelativeLayout) findViewById(R.id.ll_seven);
        etThree = (EditText) findViewById(R.id.et_three);
        llEight = (RelativeLayout) findViewById(R.id.ll_eight);
        etFour = (EditText) findViewById(R.id.et_four);
        llNine = (RelativeLayout) findViewById(R.id.ll_nine);
        etFive = (EditText) findViewById(R.id.et_five);
        ivOpenClose = (ImageView) findViewById(R.id.iv_open_close);
        setNormalTitle("Basic setting", v -> finish());
        setrightTitle("Submit", "#3298db", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initData() {

    }
    private boolean isOpen;
    @Override
    public void initListener() {
        llOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUitl.showImageToastFailuer("跳转到网页");
            }
        });
        llTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectSettingTypeDialog();
            }
        });
        llThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaseSettingActivity.this,SiteSettingActivity.class));
            }
        });
        ivOpenClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen){
                   ivOpenClose.setImageResource(R.drawable.image_setting_close);
                }
                else{
                    ivOpenClose.setImageResource(R.drawable.image_setting_open);
                }
                isOpen=!isOpen;
            }
        });

    }


    private void showSelectSettingTypeDialog() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_mppt_select)
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
                        btnOne.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                tvOne.setText("Parallel connection");


                            }
                        });
                        btnTwo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog.dismiss();

                                tvOne.setText("Independent");
                            }
                        });




                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }
}
