package com.jilian.powerstation.modul.natives;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.dialog.nicedialog.BaseNiceDialog;
import com.jilian.powerstation.dialog.nicedialog.NiceDialog;
import com.jilian.powerstation.dialog.nicedialog.ViewConvertListener;
import com.jilian.powerstation.dialog.nicedialog.ViewHolder;

public class OneNativeFragment extends BaseFragment {
    private LinearLayout llTop;
    private LinearLayout llCenter;
    private LinearLayout llLeft;
    private LinearLayout llRight;
    private LinearLayout llBottom;



    @Override
    protected void loadData() {

    }

    @Override
    protected void createViewModel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_onenative;
}

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setNormalTitle("Real-time flow graph", v -> getActivity().finish());
        llTop = (LinearLayout) view.findViewById(R.id.ll_top);
        llCenter = (LinearLayout)  view.findViewById(R.id.ll_center);
        llLeft = (LinearLayout)  view.findViewById(R.id.ll_left);
        llRight = (LinearLayout)  view.findViewById(R.id.ll_right);
        llBottom = (LinearLayout)  view.findViewById(R.id.ll_bottom);

    }

    @Override
    protected void initData() {

    }

    private void showSelectSettingTypeDialog() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_native_select)
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


                            }
                        });
                        btnTwo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog.dismiss();


                            }
                        });

                        btnThree.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog.dismiss();


                            }
                        });


                    }
                })
                .setShowBottom(true)
                .show(getActivity().getSupportFragmentManager());
    }
    @Override
    protected void initListener() {
        llLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectSettingTypeDialog();
            }
        });
        llRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectSettingTypeDialog();
            }
        });

        llTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectSettingTypeDialog();
            }
        });

        llBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectSettingTypeDialog();
            }
        });

        llCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectSettingTypeDialog();
            }
        });


    }
}
