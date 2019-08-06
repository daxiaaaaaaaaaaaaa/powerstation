package com.jilian.powerstation.modul.natives;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.common.dto.ConfigInfoDto;
import com.jilian.powerstation.dialog.nicedialog.BaseNiceDialog;
import com.jilian.powerstation.dialog.nicedialog.NiceDialog;
import com.jilian.powerstation.dialog.nicedialog.ViewConvertListener;
import com.jilian.powerstation.dialog.nicedialog.ViewHolder;
import com.jilian.powerstation.utils.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class SiteSettingActivity extends BaseActivity {
    private TextView tvSiteValue;
    private View viewLint;
    private LinearLayout llSite;
    private EditText etSoc;
    private LinearLayout llOne;
    private TextView siteTitle1;
    private LinearLayout llSite1;
    private EditText etSiteValue1;
    private TextView tvSiteStartime1;
    private TextView tvSiteEndtime1;
    private LinearLayout llSite2;
    private EditText etSiteValue2;
    private TextView tvSiteStartime2;
    private TextView tvSiteEndtime2;
    private TextView siteTitle2;
    private LinearLayout llSite3;
    private EditText etSiteValue3;
    private TextView tvSiteStartime3;
    private TextView tvSiteEndtime3;
    private LinearLayout llSite4;
    private EditText etSiteValue4;
    private TextView tvSiteStartime4;
    private TextView tvSiteEndtime4;

    @Override
    protected void createViewModel() {

    }

    @Override
    public int intiLayout() {
        return R.layout.activity_site_setting;
    }

    @Override
    public void initView() {
        setNormalTitle("SiteSetting", v -> finish());
        setrightTitle("Submit", "#3298db", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvSiteValue = (TextView) findViewById(R.id.tv_site_value);
        viewLint = (View) findViewById(R.id.view_lint);
        llSite = (LinearLayout) findViewById(R.id.ll_site);
        etSoc = (EditText) findViewById(R.id.et_soc);
        llOne = (LinearLayout) findViewById(R.id.ll_one);
        siteTitle1 = (TextView) findViewById(R.id.site_title1);
        llSite1 = (LinearLayout) findViewById(R.id.ll_site1);
        etSiteValue1 = (EditText) findViewById(R.id.et_site_value1);
        tvSiteStartime1 = (TextView) findViewById(R.id.tv_site_startime1);
        tvSiteEndtime1 = (TextView) findViewById(R.id.tv_site_endtime1);
        llSite2 = (LinearLayout) findViewById(R.id.ll_site2);
        etSiteValue2 = (EditText) findViewById(R.id.et_site_value2);
        tvSiteStartime2 = (TextView) findViewById(R.id.tv_site_startime2);
        tvSiteEndtime2 = (TextView) findViewById(R.id.tv_site_endtime2);
        siteTitle2 = (TextView) findViewById(R.id.site_title2);
        llSite3 = (LinearLayout) findViewById(R.id.ll_site3);
        etSiteValue3 = (EditText) findViewById(R.id.et_site_value3);
        tvSiteStartime3 = (TextView) findViewById(R.id.tv_site_startime3);
        tvSiteEndtime3 = (TextView) findViewById(R.id.tv_site_endtime3);
        llSite4 = (LinearLayout) findViewById(R.id.ll_site4);
        etSiteValue4 = (EditText) findViewById(R.id.et_site_value4);
        tvSiteStartime4 = (TextView) findViewById(R.id.tv_site_startime4);
        tvSiteEndtime4 = (TextView) findViewById(R.id.tv_site_endtime4);
    }

    @Override
    public void initData() {
        initCustomTimePicker();
    }
    private int timeType;//时间类型
    private TimePickerView pvCustomTime;
    @Override
    public void initListener() {
        tvSiteValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectSettingTypeDialog();
            }
        });


        tvSiteStartime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeType = 1;
                pvCustomTime.show();
            }
        });

        tvSiteEndtime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeType = 2;
                pvCustomTime.show();
            }
        });


        tvSiteStartime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeType = 3;
                pvCustomTime.show();
            }
        });

        tvSiteEndtime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeType = 4;
                pvCustomTime.show();
            }
        });


        tvSiteStartime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeType = 5;
                pvCustomTime.show();
            }
        });

        tvSiteEndtime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeType = 6;
                pvCustomTime.show();
            }
        });


        tvSiteStartime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeType = 7;
                pvCustomTime.show();
            }
        });

        tvSiteEndtime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeType = 8;
                pvCustomTime.show();
            }
        });

    }

    private int WorkingType;

    /**
     * 选择设置配置类型对话框
     */
    private void showSelectSettingTypeDialog() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_setting_select)
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
                                WorkingType = 2;
                                dialog.dismiss();
                                initConfigView(null);

                            }
                        });
                        btnTwo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WorkingType = 3;
                                dialog.dismiss();
                                initConfigView(null);

                            }
                        });

                        btnThree.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WorkingType = 4;
                                dialog.dismiss();
                                initConfigView(null);

                            }
                        });


                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    /**
     * 初始化设置的界面
     *
     * @param dto
     */
    private void initConfigView(ConfigInfoDto dto) {

       // WorkingType = dto.getWorkingType();
        //（2:自发自用，3:错峰用电，4:应急电源）
        if (WorkingType == 2) {
            llSite.setVisibility(View.VISIBLE);
            llOne.setVisibility(View.GONE);
            tvSiteValue.setText("self-use");
            //并网截止soc
           // etSoc.setText(dto.getSoc());

        }
        if (WorkingType == 3) {
            llSite.setVisibility(View.GONE);
            llOne.setVisibility(View.VISIBLE);
            tvSiteValue.setText("Off-peak power consumption");
//            //并网截止soc
//            etSoc.setText(dto.getSoc());
//            //充电功率一
//            etSiteValue1.setText(dto.getRechargePower_One());
//            //放电功率一
//            etSiteValue2.setText(dto.getDischargePower_One());
//            //充电功率二
//            etSiteValue3.setText(dto.getRechargePower_Two());
//            //放电功率三
//            etSiteValue4.setText(dto.getDischargePower_Two());
//
//            //充电开始时间1
//            tvSiteStartime1.setText(dto.getRechargeStartTime_One());
//            //充电结束时间1
//            tvSiteEndtime1.setText(dto.getRechargeEndTime_One());
//
//            //放电开始时间1
//            tvSiteStartime2.setText(dto.getDischargeStartTime_One());
//            //放电结束时间1
//            tvSiteEndtime2.setText(dto.getDischargeEndTime_One());
//
//
//            //充电开始时间2
//            tvSiteStartime3.setText(dto.getRechargeStartTime_Two());
//            //充电结束时间2
//            tvSiteEndtime3.setText(dto.getRechargeEndTime_Two());
//
//            //放电开始时间2
//            tvSiteStartime4.setText(dto.getDischargeStartTime_Two());
//            //放电结束时间2
//            tvSiteEndtime4.setText(dto.getDischargeEndTime_Two());

        }

        if (WorkingType == 4) {
            llSite.setVisibility(View.GONE);
            llOne.setVisibility(View.GONE);
           tvSiteValue.setText("Emergency power supply");
        }
    }


    /**
     * 初始化时间数据
     */
    private void initCustomTimePicker() {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1918, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2029, 01, 01);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                String dateStr = DateUtil.dateToString("HH:mm", date);
                switch (timeType) {
                    case 1:
                        //充电开始时间1
                        tvSiteStartime1.setText(dateStr);
                        break;
                    case 2:
                        //充电结束时间1
                        tvSiteEndtime1.setText(dateStr);
                        break;
                    case 3:
                        //放电开始时间1
                        tvSiteStartime2.setText(dateStr);
                        break;
                    case 4:
                        //放电结束时间1
                        tvSiteEndtime2.setText(dateStr);
                        break;
                    case 5:
                        //充电开始时间2
                        tvSiteStartime3.setText(dateStr);
                        break;
                    case 6:
                        //充电结束时间2
                        tvSiteEndtime3.setText(dateStr);
                        break;
                    case 7:
                        //放电开始时间2
                        tvSiteStartime4.setText(dateStr);
                        break;
                    case 8:
                        //放电结束时间2
                        tvSiteEndtime4.setText(dateStr);
                        break;
                }

            }
        })
                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentTextSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
                /*.animGravity(Gravity.RIGHT)// default is center*/
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_hour_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        final TextView tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                //各个部分是否显示
                .setType(new boolean[]{false, false, false, true, true, false})
                //时间格式
                .setLabel("", "", "", " ", " ", " ")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(40, 0, -40, 0, 0, -0)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFFe0e0e0)
                .setLineSpacingMultiplier(2f)
                .build();
    }

}
