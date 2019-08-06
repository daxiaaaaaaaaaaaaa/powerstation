package com.jilian.powerstation.modul.natives;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.dialog.nicedialog.BaseNiceDialog;
import com.jilian.powerstation.dialog.nicedialog.NiceDialog;
import com.jilian.powerstation.dialog.nicedialog.ViewConvertListener;
import com.jilian.powerstation.dialog.nicedialog.ViewHolder;

public class ManualOperationActivity extends BaseActivity
{
    private LinearLayout llOne;
    private TextView tv01;
    private RelativeLayout llTwo;
    private TextView tvTwo;
    private EditText tv02;
    private TextView tvRight;






    @Override
    protected void createViewModel() {

    }

    @Override
    public int intiLayout() {
        return R.layout.activity_manual_operation;
    }

    @Override
    public void initView() {
        setNormalTitle("Manual operation setting", v -> finish());
        setrightTitle("Submit", "#3298db", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        llOne = (LinearLayout) findViewById(R.id.ll_one);
        tv01 = (TextView) findViewById(R.id.tv_01);
        llTwo = (RelativeLayout) findViewById(R.id.ll_two);
        tvTwo = (TextView) findViewById(R.id.tv_two);
        tv02 = (EditText) findViewById(R.id.tv_02);
        tvRight = (TextView) findViewById(R.id.tv_right);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        llOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVoltResponseModeDialog();
            }
        });
    }

    /**
     * 安规标准
     */
    private void showVoltResponseModeDialog() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_manual_operation_select)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        dialog.setOutCancel(false);


                        TextView btnOne = (TextView) holder.getView(R.id.btn_one);
                        TextView btnTwo = (TextView) holder.getView(R.id.btn_two);
                        TextView btnThree= (TextView) holder.getView(R.id.btn_three);
                        TextView  btnFour = (TextView) holder.getView(R.id.btn_four);




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
                                tv01.setText(btnOne.getText().toString());
                                llTwo.setVisibility(View.GONE);
                                tvRight.setVisibility(View.GONE);

                            }
                        });
                        btnTwo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog.dismiss();
                                tv01.setText(btnTwo.getText().toString());
                                llTwo.setVisibility(View.VISIBLE);
                                tvTwo.setText(btnTwo.getText().toString());
                                tvRight.setVisibility(View.VISIBLE);
                            }
                        });

                        btnThree.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog.dismiss();
                                llTwo.setVisibility(View.VISIBLE);
                                tv01.setText(btnThree.getText().toString());
                                tvTwo.setText(btnThree.getText().toString());
                                tvRight.setVisibility(View.VISIBLE);
                            }
                        });
                        btnFour.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog.dismiss();
                                llTwo.setVisibility(View.VISIBLE);
                                tv01.setText(btnFour.getText().toString());
                                tvTwo.setText(btnFour.getText().toString());
                                tvRight.setVisibility(View.VISIBLE);
                            }
                        });




                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }
}
