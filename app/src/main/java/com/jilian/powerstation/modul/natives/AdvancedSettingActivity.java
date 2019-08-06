package com.jilian.powerstation.modul.natives;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.dialog.nicedialog.BaseNiceDialog;
import com.jilian.powerstation.dialog.nicedialog.NiceDialog;
import com.jilian.powerstation.dialog.nicedialog.ViewConvertListener;
import com.jilian.powerstation.dialog.nicedialog.ViewHolder;

public class AdvancedSettingActivity extends BaseActivity {
    private EditText tv01;
    private EditText tv02;
    private EditText tv03;
    private TextView tv04;
    private EditText tv05;
    private TextView tv06;
    private LinearLayout llThree;
    private TextView tv07;
    private EditText tv08;
    private EditText tv09;
    private EditText tv10;
    private EditText tv11;

    @Override
    protected void createViewModel() {

    }

    @Override
    public int intiLayout() {
        return R.layout.activity_advancedsetting;
    }

    @Override
    public void initView() {
        setNormalTitle("Advanced setting", v -> finish());
        setrightTitle("Submit", "#3298db", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv01 = (EditText) findViewById(R.id.tv_01);
        tv02 = (EditText) findViewById(R.id.tv_02);
        tv03 = (EditText) findViewById(R.id.tv_03);
        tv04 = (TextView) findViewById(R.id.tv_04);
        tv05 = (EditText) findViewById(R.id.tv_05);
        tv06 = (TextView) findViewById(R.id.tv_06);
        llThree = (LinearLayout) findViewById(R.id.ll_three);
        tv07 = (TextView) findViewById(R.id.tv_07);
        tv08 = (EditText) findViewById(R.id.tv_08);
        tv09 = (EditText) findViewById(R.id.tv_09);
        tv10 = (EditText) findViewById(R.id.tv_10);
        tv11 = (EditText) findViewById(R.id.tv_11);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        //安规标准
        tv06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSatetyStandardssDialog();
            }
        });
        //电网电压响应设置
        tv07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdvancedSettingActivity.this, VoltResponseModeActivity.class));
            }
        });
        //手动操作
        tv04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdvancedSettingActivity.this, ManualOperationActivity.class));

            }
        });
    }


    /**
     * 安规标准
     */
    private void showSatetyStandardssDialog() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_satety_standards_select)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        dialog.setOutCancel(false);


                        TextView btnOne = (TextView) holder.getView(R.id.btn_one);
                        TextView btnTwo = (TextView) holder.getView(R.id.btn_two);
                        TextView btnThree = (TextView) holder.getView(R.id.btn_three);
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
                                tv06.setText(btnOne.getText().toString());


                            }
                        });
                        btnTwo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog.dismiss();

                                tv06.setText(btnTwo.getText().toString());
                            }
                        });

                        btnThree.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog.dismiss();

                                tv06.setText(btnThree.getText().toString());
                            }
                        });


                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }
}
