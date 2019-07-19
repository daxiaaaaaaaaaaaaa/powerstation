package com.jilian.powerstation.modul.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.contrarywind.view.WheelView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.common.dto.BatteryDataDto;
import com.jilian.powerstation.common.dto.BatteryDataListDto;
import com.jilian.powerstation.common.dto.BatteryDetailDto;
import com.jilian.powerstation.common.dto.BatteryfoDto;
import com.jilian.powerstation.common.dto.PcsHistoryDataListDto;
import com.jilian.powerstation.dialog.nicedialog.BaseNiceDialog;
import com.jilian.powerstation.dialog.nicedialog.NiceDialog;
import com.jilian.powerstation.dialog.nicedialog.ViewConvertListener;
import com.jilian.powerstation.dialog.nicedialog.ViewHolder;
import com.jilian.powerstation.modul.adapter.WheelAdapter;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.DateUtil;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.utils.ToastUitl;
import com.jilian.powerstation.views.TMarket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.ToString;

/**
 * 电池详情
 */
public class BatteryDetailActivity extends BaseActivity {

    private UserViewModel userViewModel;

    private LineChart lc;
    TMarket tMarket = new TMarket();
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
    private TextView tvType;

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
        tvType = (TextView) findViewById(R.id.tv_type);
        tvSelectDate = (TextView) findViewById(R.id.tv_select_date);
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
        data = (BatteryfoDto) getIntent().getSerializableExtra("data");
        tvName.setText("battery" + data.getId());

        Glide.with(this).
                load(data.getBcmuPhoto())
                .into(ivHead);//在RequestBuilder 中使用自定义的ImageViewTarge

        tvDate.setText(DateUtil.dateToString("yyyy/MM/dd HH:mm:ss", new Date(data.getTime())));
        initCustomTimePicker();
        tvSelectDate.setText(DateUtil.dateToString(DateUtil.DATE_FORMAT, new Date()));
    }

    @Override
    public void initData() {
        typeList.add("PV input voltage");
        typeList.add("PV input current");
        typeList.add("PV input power");
        typeList.add("Glid output voltage");
        typeList.add("Glid output frequency");
        // 设置上下左右偏移量
        lc.setExtraOffsets(24f, 24f, 24f, 0f);
        lc.animateXY(3000, 3000); // XY动画
        setLegend(); // 设置图例
        setYAxis(); // 设置Y轴
        setXAxis(); // 设置X轴
        setData();
        //获取电池详情
        getBatteryInfo();
        //获取电池历史数据
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

    private void setLegend() {
        Legend legend = lc.getLegend();
        legend.setForm(Legend.LegendForm.LINE); // 图形：线
        legend.setFormSize(14f); // 图形大小
        legend.setFormLineWidth(9f); // 线宽小于如下大小绘制出平躺长方形
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // 图例在水平线上的对齐方式：右对齐
        legend.setTextColor(Color.WHITE);
    }


    private void setYAxis() {
        // 左边Y轴
        final YAxis yAxisLeft = lc.getAxisLeft();
        yAxisLeft.setAxisMaximum(25.5f); // 设置Y轴最大值
        yAxisLeft.setAxisMinimum(2); // 设置Y轴最小值
        yAxisLeft.setGranularity(2f); // 设置间隔尺寸
        yAxisLeft.setTextSize(12f); // 文本大小为12dp
        yAxisLeft.setTextColor(Color.BLACK); // 文本颜色为灰色
        yAxisLeft.setDrawGridLines(false); // 不绘制网格线
//        yAxisLeft.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return value == yAxisLeft.getAxisMinimum() ? (int) value + "" : (int) value + "";
//            }
//        });
        // 右侧Y轴
        lc.getAxisRight().setEnabled(false); // 不启用
        //是否展示网格线
        lc.setDrawGridBackground(false);
        lc.getAxisRight().setDrawGridLines(false);
        lc.getAxisLeft().setDrawGridLines(true);
        //设置X Y轴网格线为虚线（实体线长度、间隔距离、偏移量：通常使用 0）
        lc.getAxisLeft().enableGridDashedLine(10f, 10f, 0f);
    }

    private void setXAxis() {
        // X轴
        XAxis xAxis = lc.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 在底部
        xAxis.setDrawGridLines(false); // 不绘制网格线
        xAxis.setLabelCount(20); // 设置标签数量
        xAxis.setTextColor(Color.BLACK); // 文本颜色为灰色
        xAxis.setTextSize(12f); // 文本大小为12dp
        xAxis.setGranularity(6f); // 设置间隔尺寸
        xAxis.setAxisMinimum(0f); // 设置X轴最小值
        xAxis.setAxisMaximum(30f); // 设置X轴最大值
        // 设置标签的显示格式
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(){});
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            public String getFormattedValue(float value, AxisBase axis) {
                return "9:" + value;
            }
        });
        //是否展示网格线
        xAxis.setDrawGridLines(false);

    }

    public void setData() {
        float[] ys1 = new float[]{
                0f, 9f, 9f, 7f, 6f, 7f, 9f,
                1f, 1f, 4f, 0f};
        LineData lineData = new LineData();
        for (int i = 0; i < 4; i++) {
            List<Entry> yVals1 = new ArrayList<>();
            LineDataSet lineDataSet = null;
            switch (i) {
                case 0:
                    yVals1.add(new Entry(0f, 0f));
                    yVals1.add(new Entry(8f, 6f));
                    yVals1.add(new Entry(13f, 16f));
                    yVals1.add(new Entry(23f, 3f));
                    yVals1.add(new Entry(28f, 11f));
                    yVals1.add(new Entry(30f, 0f));
                    lineDataSet = setChartData("123", yVals1, R.color.color_chart_three, R.drawable.bg_color3); // 设置图标数据

                    break;
                case 1:
                    yVals1.add(new Entry(17f, 0f));
                    yVals1.add(new Entry(18f, 13f));
                    yVals1.add(new Entry(20f, 16f));
                    yVals1.add(new Entry(23f, 3f));
                    yVals1.add(new Entry(28f, 13f));
                    yVals1.add(new Entry(30f, 0f));
                    lineDataSet = setChartData("1233", yVals1, R.color.color_chart_one, R.drawable.bg_color1); // 设置图标数据
                    break;
                case 2:
                    yVals1.add(new Entry(20f, 0f));
                    yVals1.add(new Entry(22f, 13f));
                    yVals1.add(new Entry(26f, 22));
                    yVals1.add(new Entry(28f, 5f));
                    yVals1.add(new Entry(29f, 22));
                    yVals1.add(new Entry(30f, 0f));
                    lineDataSet = setChartData("12223", yVals1, R.color.color_chart_two, R.drawable.bg_color2); // 设置图标数据
                    break;
                case 3:
                    yVals1.add(new Entry(0f, 0f));
                    yVals1.add(new Entry(3f, 4f));
                    yVals1.add(new Entry(5f, 2f));
                    yVals1.add(new Entry(8f, 5f));
                    yVals1.add(new Entry(10f, 0f));
                    yVals1.add(new Entry(20f, 4f));
                    yVals1.add(new Entry(24f, 3f));
                    yVals1.add(new Entry(26f, 0f));
                    lineDataSet = setChartData("我认为", yVals1, R.color.color_chart_four, R.drawable.bg_color4); // 设置图标数据
                    break;
            }
            lineData.addDataSet(lineDataSet);
            // 3.将每一组折线数据集添加到折线数据中
            lineData.setDrawValues(false);
            // 4.将折线数据设置给图表
        }
        lc.setData(lineData);
        lc.invalidate();
    }

    public LineDataSet setChartData(String name, List<Entry> yVals1, int color, int fillColor) {
        // 1.获取一或多组Entry对象集合的数据
        // 模拟数据1

        // 2.分别通过每一组Entry对象集合的数据创建折线数据集
        LineDataSet lineDataSet1 = new LineDataSet(yVals1, name);
        lineDataSet1.setDrawCircles(false);// 不绘制圆点
        lineDataSet1.setDrawCircleHole(false); // 不绘制圆洞，即为实心圆点
        lineDataSet1.setColor(getResources().getColor(color)); // 设置为红色
        lineDataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER); // 设置为贝塞尔曲线
        lineDataSet1.setCubicIntensity(0.15f); // 强度
        lineDataSet1.setCircleColor(Color.RED); // 设置圆点为颜色
        lineDataSet1.setCircleRadius(0f);
        lineDataSet1.setLineWidth(2f); // 设置线宽为2

        lineDataSet1.setDrawFilled(true); // 启用填充
        lineDataSet1.setFillAlpha(95); // 透明度
        //设置曲线图渐填充渐变色
        if (Utils.getSDKInt() >= 18) {
            // fill drawable only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(this, fillColor);
            drawable.setAlpha(160);
            lineDataSet1.setFillDrawable(drawable);
        } else {
            lineDataSet1.setFillColor(Color.WHITE);
        }
        return lineDataSet1;


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
                                dialog.dismiss();
                                getBatteryData();

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
                tvSelectDate.setText(DateUtil.dateToString(DateUtil.DATE_FORMAT, date));
                getBatteryData();
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
    }

    /**
     * 有数据的时候  初始化图形
     *
     * @param rows
     */
    private void initDataView(List<BatteryDataDto> rows) {
        Log.e(TAG, "initDataView: " + rows.size());
    }

}
