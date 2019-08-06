package com.jilian.powerstation.modul.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.contrarywind.view.WheelView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.common.dto.BatteryDataDto;
import com.jilian.powerstation.common.dto.BatteryDataListDto;
import com.jilian.powerstation.common.dto.BatteryDetailDto;
import com.jilian.powerstation.common.dto.BatteryfoDto;
import com.jilian.powerstation.common.dto.PcsHistoryDataListDto;
import com.jilian.powerstation.common.dto.ReportDto;
import com.jilian.powerstation.dialog.nicedialog.BaseNiceDialog;
import com.jilian.powerstation.dialog.nicedialog.NiceDialog;
import com.jilian.powerstation.dialog.nicedialog.ViewConvertListener;
import com.jilian.powerstation.dialog.nicedialog.ViewHolder;
import com.jilian.powerstation.manege.BaseMarket;
import com.jilian.powerstation.manege.CharManager;
import com.jilian.powerstation.modul.adapter.WheelAdapter;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.DateUtil;
import com.jilian.powerstation.utils.DisplayUtil;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.utils.ToastUitl;
import com.jilian.powerstation.utils.Utils;
import com.jilian.powerstation.views.CostomMarket;
import com.jilian.powerstation.views.TMarket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.ToString;

/**
 * 电池详情
 */
public class BatteryDetailActivity extends BaseActivity implements IAxisValueFormatter {

    private UserViewModel userViewModel;

    private LineChart lc;
    private LinearLayout llDesc;
    private ImageView ivHead;
    private TextView tvName;
    private TextView tvDate;
    private TextView tvOne;
    private TextView tvTwo;
    private TextView tvThree;
    private TextView tvFour;
    private TextView tvFive;
    private TextView tvSix;
    private BatteryfoDto data;
    private TextView tvSelectDate;
    private TextView tvLeft;
    private TextView tvRight;
    private TextView tvType;
    private TextView tvCenterTitle;
    private ScrollView scrollView;

    private CostomMarket tMarket;
    private CharManager charManager;

    private List<BatteryDataDto> mDataDto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.removeActivity(this);
    }

    @Override
    protected void createViewModel() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public int intiLayout() {
        return R.layout.activity_battery_detail;
    }

    @Override
    public void initView() {
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        tvType = (TextView) findViewById(R.id.tv_type);
        llDesc = findViewById(R.id.ll_desc);
        tvCenterTitle = (TextView) findViewById(R.id.tv_center_title);
        tvSelectDate = (TextView) findViewById(R.id.tv_select_date);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);
        ivHead = (ImageView) findViewById(R.id.iv_head);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvOne = (TextView) findViewById(R.id.tv_one);
        tvTwo = (TextView) findViewById(R.id.tv_two);
        tvThree = (TextView) findViewById(R.id.tv_three);
        tvFour = (TextView) findViewById(R.id.tv_four);
        tvFive = (TextView) findViewById(R.id.tv_five);
        tvSix = (TextView) findViewById(R.id.tv_six);
        lc = findViewById(R.id.lineChart);
        lc.setMarker(tMarket);
        setNormalTitle(MyApplication.getInstance().getPowerDto().getProductName(), v -> finish());
        setrightImageOne(R.drawable.image_right_one, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShareDialog(scrollView,null,null,null);
            }
        });
        lc.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                tMarket.refreshContent(e, h);
            }

            @Override
            public void onNothingSelected() {
            }
        });

        tMarket = new CostomMarket(this, lc, DisplayUtil.getScreenWidth(this), getResources().getDimension(R.dimen.widget_size_250), 0);
        lc.setMarker(tMarket);
        // 设置上下左右偏移量
        charManager = new CharManager(lc, tMarket, this);
        charManager.setLegend(); // 设置图例


        data = (BatteryfoDto) getIntent().getSerializableExtra("data");
        tvName.setText("battery" + data.getId());

        Glide.with(this).
                load(data.getBcmuPhoto())
                .into(ivHead);//在RequestBuilder 中使用自定义的ImageViewTarge

        tvDate.setText(DateUtil.dateToString("yyyy/MM/dd HH:mm:ss", new Date(data.getTime())));
        initCustomTimePicker();
    }

    @Override
    public void initData() {
        typeList.add("PV input voltage");
        typeList.add("PV input current");
        typeList.add("PV input power");
        typeList.add("Glid output voltage");
        typeList.add("Glid output frequency");
        //获取电池详情
        getBatteryInfo();
        //获取电池历史数据
        loadDatas( new Date());

    }
    private Date currDate;
    private void loadDatas(Date currTime) {
        currDate = currTime;
        tvRight.setVisibility(DateUtil.isBeforCurrentDate(currDate, 3) ? View.VISIBLE : View.INVISIBLE);
        tvSelectDate.setText(DateUtil.dateToString(DateUtil.DATE_FORMAT, currDate));
        getBatteryData();
    }

    /**
     * 获取电池详情
     */
    private void getBatteryInfo() {
        showLoadingDialog();
        userViewModel.getBatteryInfo(getIntent().getStringExtra("sn"), getIntent().getStringExtra("id"));
        userViewModel.getBatteryDetailData().observe(this, new Observer<BaseDto<BatteryDetailDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<BatteryDetailDto> batteryDetailDtoBaseDto) {
                hideLoadingDialog();
                if (batteryDetailDtoBaseDto.isSuccess()) {
                    BatteryDetailDto detailDto = batteryDetailDtoBaseDto.getData();
                    if (EmptyUtils.isNotEmpty(detailDto)) {
                        initDetailView(detailDto);
                    } else {
                        ToastUitl.showImageToastTips("no data");
                    }
                } else {
                    ToastUitl.showImageToastTips(batteryDetailDtoBaseDto.getMsg());
                }
            }
        });
    }

    /**
     * 初始化电池详情
     *
     * @param detailDto
     */
    private void initDetailView(BatteryDetailDto detailDto) {
        tvDate.setText(DateUtil.dateToString("yyyy/MM/dd HH:mm:ss", new Date(detailDto.getTime())));
        tvOne.setText(detailDto.getVolt());//电池电压
        tvTwo.setText(detailDto.getCurrent());//电池电流
        tvThree.setText(detailDto.getPower());//电池功率
        tvFour.setText(detailDto.getAvgTemp());//电池平均温度
        tvFive.setText(detailDto.getMaxTemp());//电池最高温度
        tvSix.setText(detailDto.getSoc());//电池电量
    }

    public void setData(List<BatteryDataDto> rows) {
        LineData lineData = new LineData();
        charManager.removeAll();
        if (rows != null && !rows.isEmpty()) {
            mDataDto = rows;
            charManager.setXAxis(rows.size(), 0, 1, rows.size(), this); // 设置X轴
            List<Entry> yVals1 = new ArrayList<>();
            List<Entry> yVals2 = new ArrayList<>();
            tMarket.clear();
            for (int index = 0, len = rows.size(); index < len; index++) {
                BatteryDataDto dto = rows.get(index);
                int value1 = 0;
                int value2 = 0;
                switch (type){
                    case 0:
                        value1 = charManager.getIntValue(dto.getVolt());
                        break;
                    case 1:
                        value1 = charManager.getIntValue(dto.getCurrent());
                        break;
                    case 2:
                        value1 = charManager.getIntValue(dto.getAvgTemp());
                        value2 = charManager.getIntValue(dto.getMaxTemp());
                        break;
                    case 3:
                        value1 = charManager.getIntValue(dto.getPower());
                        break;
                    case 4:
                        value1 = charManager.getIntValue(dto.getSoc());
                        break;
                }
                yVals1.add(new Entry(index, value1));
                yVals2.add(new Entry(index, value2));

            }
            tMarket.setEnable(true);
            lineData.setHighlightEnabled(true);
            switch (type){
                case 0:
                    lineData.addDataSet(charManager.setChartData("Voltage", yVals1, R.color.color_chart_three, R.drawable.bg_color3));
                    break;
                case 1:
                    lineData.addDataSet(charManager.setChartData("Frequency", yVals1, R.color.color_chart_one, R.drawable.bg_color1));
                    break;
                case 2:
                    tMarket.setEnable(false);
                    lineData.setHighlightEnabled(false);
                    lineData.addDataSet(charManager.setChartData("Average temperature", yVals1, R.color.color_chart_four, R.drawable.bg_color4));
                    lineData.addDataSet(charManager.setChartData("Maximum temperature", yVals2, R.color.color_chart_one, R.drawable.bg_color1));
                    break;
                case 3:
                    lineData.addDataSet(charManager.setChartData("Power", yVals1, R.color.color_chart_four, R.drawable.bg_color4));
                    break;
                case 4:
                    lineData.addDataSet(charManager.setChartData("Power", yVals1, R.color.color_chart_three, R.drawable.bg_color3));
                    break;
            }

            charManager.setYAxis(mDataDto.size()-1,0, mDataDto.size()); // 设置Y轴
            charManager.setData(lineData, "kw", mDataDto.size());

        }


    }

    @Override
    public void initListener() {
        tvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTypeDialog();
            }
        });
        tvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvCustomTime.show();
            }
        });
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatas(DateUtil.getBeforeDay(currDate));
            }
        });

        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatas(DateUtil.getAfterDay(currDate));
            }
        });

        tMarket.setOnMarketBackCall(new BaseMarket.OnMarketBackCall() {
            @Override
            public String onBackCall(int position) {
                if (Utils.isInBound(mDataDto, position)) {
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    return format.format(new Date(mDataDto.get(position).getTime()));
                }
                return "";
            }
        });
    }

    private List<String> typeList = new ArrayList<>();
    private int type = 0;//统计类型（0:电池电压，1:电池电流，2：温度，3:电池功率，4:电池SOC百分比）


    private void showTypeDialog() {

        NiceDialog.init()
                .setLayoutId(R.layout.dialog_select_inver)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        dialog.setOutCancel(false);
                        WheelView wheelview = (WheelView) holder.getView(R.id.wheelview);
                        wheelview.setCyclic(false);
                        TextView tvTitle = (TextView) holder.getView(R.id.tv_title);
                        TextView tvCancel = (TextView) holder.getView(R.id.tv_cancel);

                        TextView tvFinish = (TextView) holder.getView(R.id.tv_finish);
                        wheelview.setAdapter(new WheelAdapter(typeList));

                        tvFinish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tvType.setText(typeList.get(wheelview.getCurrentItem()));
                                type = wheelview.getCurrentItem();
                                llDesc.setVisibility(View.INVISIBLE);
                                switch (type){
                                    case 0:
                                        tvCenterTitle.setText("Voltage（V）");
                                        break;
                                    case 1:
                                        tvCenterTitle.setText("Current（A）");
                                        break;
                                    case 2:
                                        llDesc.setVisibility(View.VISIBLE);
                                        tvCenterTitle.setText("Temperature（℃）");
                                        break;
                                    case 3:
                                        tvCenterTitle.setText("Power（W）");
                                        break;
                                    case 4:
                                        tvCenterTitle.setText("Percentage（%）");
                                        break;
                                }
                                dialog.dismiss();
                                loadDatas(currDate);

                            }
                        });
                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        wheelview.setCurrentItem(0);
                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    private TimePickerView pvCustomTime;

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
                loadDatas(date);
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

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
                .setType(new boolean[]{true, true, true, false, false, false})
                //时间格式
                .setLabel("", "", "", ":00", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(40, 0, -40, 0, 0, -0)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFFe0e0e0)
                .setLineSpacingMultiplier(2f)
                .setSubmitColor(0xFFe0e0e0)
                .setCancelColor(getResources().getColor(R.color.color_text_dark))
                .build();
    }

    /**
     * 获取逆变器图形数据 历史数据
     */
    private void getBatteryData() {

        showLoadingDialog();
        userViewModel.getBatteryData(getIntent().getStringExtra("sn"), getIntent().getStringExtra("id"), type, tvSelectDate.getText().toString());
        userViewModel.getBtyHistoryData().observe(this, new Observer<BaseDto<BatteryDataListDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<BatteryDataListDto> dtoBaseDto) {
                hideLoadingDialog();
                if (dtoBaseDto.isSuccess()) {
                    if (EmptyUtils.isNotEmpty(dtoBaseDto.getData()) && EmptyUtils.isNotEmpty(dtoBaseDto.getData().getRows())) {
                        initDataView(dtoBaseDto.getData().getRows());
                    } else {
                        initNodataView();
                    }
                } else {
                    initNodataView();
                    ToastUitl.showImageToastTips(dtoBaseDto.getMsg());
                }
            }
        });


    }

    /**
     * 没数据的时候
     */
    private void initNodataView() {
        setData(null);
    }

    /**
     * 有数据的时候  初始化图形
     *
     * @param rows
     */
    private void initDataView(List<BatteryDataDto> rows) {
        Log.e(TAG, "initDataView: " + rows.size());
        setData(rows);
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int currItem = (int) value;
        if (currItem >= 0 && currItem < mDataDto.size()) {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            return format.format(new Date(mDataDto.get(currItem).getTime()));
        } else {
            return "";
        }
    }
}
