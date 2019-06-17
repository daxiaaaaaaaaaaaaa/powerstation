package com.jilian.powerstation.modul.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.ReportListDto;
import com.jilian.powerstation.modul.viewmodel.ReportViewModel;
import com.jilian.powerstation.utils.DateUtil;
import com.jilian.powerstation.views.TMarket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SiteFourFargement extends BaseFragment {
    private BarChart barChart;
    private TMarket tMarket = new TMarket();
    private TextView tvCenter;
    private TimePickerView pvCustomTime;
    private String sn;
    private long currDate;
    private ReportViewModel reportViewModel;

    @Override
    protected void loadData() {

    }


    @Override
    protected void createViewModel() {
        reportViewModel = ViewModelProviders.of(this).get(ReportViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_site_four;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        barChart = view.findViewById(R.id.lineChart);
        barChart.setMarker(tMarket);
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                tMarket.refreshContent(e, h);
            }

            @Override
            public void onNothingSelected() {
            }
        });
        tvCenter = (TextView) view.findViewById(R.id.tv_center);
        initCustomTimePicker();
    }

    @Override
    protected void initData() {
        // 设置上下左右偏移量
        barChart.setExtraOffsets(24f, 24f, 24f, 0f);
        barChart.animateXY(3000, 3000); // XY动画
        setLegend(); // 设置图例
        setYAxis(); // 设置Y轴
        setXAxis(); // 设置X轴
        setData();

        sn = getActivity().getIntent().getStringExtra("sn");
        currDate = System.currentTimeMillis();
        getData();
    }
    /**
     *
     */
    public void getData() {
        if (sn == null) return;
        String startTime = DateUtil.getDayBegin(currDate) + "";
        String endTime = DateUtil.getDayBegin(currDate) + "";
        reportViewModel.addReportData(sn, startTime, endTime, 0);
        reportViewModel.getReportData().observe(this, new Observer<BaseDto<ReportListDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<ReportListDto> reportListDtoBaseDto) {

            }
        });
    }

    @Override
    protected void initListener() {

    }

    private void setLegend() {
        Legend legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE); // 图形：线
        legend.setFormSize(14f); // 图形大小
        legend.setFormLineWidth(9f); // 线宽小于如下大小绘制出平躺长方形
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // 图例在水平线上的对齐方式：右对齐
        legend.setTextColor(Color.WHITE);
    }


    private void setYAxis() {
        // 左边Y轴
        final YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setAxisMaximum(25.5f); // 设置Y轴最大值
        yAxisLeft.setAxisMinimum(0); // 设置Y轴最小值
        yAxisLeft.setGranularity(2f); // 设置间隔尺寸
        yAxisLeft.setTextSize(12f); // 文本大小为12dp
        yAxisLeft.setTextColor(Color.BLACK); // 文本颜色为灰色
        yAxisLeft.setDrawGridLines(false); // 不绘制网格线
        // 右侧Y轴
        barChart.getAxisRight().setEnabled(false); // 不启用
        //是否展示网格线
        barChart.setDrawGridBackground(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(true);
        //设置X Y轴网格线为虚线（实体线长度、间隔距离、偏移量：通常使用 0）
        barChart.getAxisLeft().enableGridDashedLine(10f, 10f, 0f);
    }

    private void setXAxis() {
        // X轴
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 在底部
        xAxis.setDrawGridLines(false); // 不绘制网格线
        xAxis.setLabelCount(20); // 设置标签数量
        xAxis.setTextColor(Color.BLACK); // 文本颜色为灰色
        xAxis.setTextSize(12f); // 文本大小为12dp
        xAxis.setGranularity(2f); // 设置间隔尺寸
        xAxis.setAxisMinimum(0f); // 设置X轴最小值
        xAxis.setAxisMaximum(10f); // 设置X轴最大值
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            public String getFormattedValue(float value, AxisBase axis) {
                return "9:" + value;
            }
        });
        //是否展示网格线
        xAxis.setDrawGridLines(false);

    }

    public void setData() {

        BarData lineData = new BarData();
        for (int i = 0; i < 2; i++) {
            List<BarEntry> yVals1 = new ArrayList<>();
            BarDataSet lineDataSet = null;
            switch (i) {
                case 0:
                    yVals1.add(new BarEntry(1f, 18f));
                    yVals1.add(new BarEntry(4f, 8f));
                    yVals1.add(new BarEntry(8f, 18f));
                    yVals1.add(new BarEntry(12f, 8f));
                    lineDataSet = setChartData("123", yVals1, R.color.color_chart_three, R.drawable.bg_color3); // 设置图标数据

                    break;
                case 1:
                    yVals1.add(new BarEntry(1f, 8f));
                    yVals1.add(new BarEntry(4f, 14f));
                    yVals1.add(new BarEntry(8f, 19f));
                    yVals1.add(new BarEntry(12f, 7f));
                    lineDataSet = setChartData("1233", yVals1, R.color.color_chart_one, R.drawable.bg_color1); // 设置图标数据
                    break;
                case 2:
                    yVals1.add(new BarEntry(1f, 13f));
                    yVals1.add(new BarEntry(4f, 4f));
                    yVals1.add(new BarEntry(8f, 11f));
                    yVals1.add(new BarEntry(12f, 2f));
                    lineDataSet = setChartData("12223", yVals1, R.color.color_chart_two, R.drawable.bg_color2); // 设置图标数据
                    break;
                case 3:
                    yVals1.add(new BarEntry(1f, 2f));
                    yVals1.add(new BarEntry(4f, 6f));
                    yVals1.add(new BarEntry(8f, 19f));
                    yVals1.add(new BarEntry(12f, 5f));
                    lineDataSet = setChartData("我认为", yVals1, R.color.color_chart_four, R.drawable.bg_color4); // 设置图标数据
                    break;
            }
            lineData.addDataSet(lineDataSet);
            // 3.将每一组折线数据集添加到折线数据中
            lineData.setDrawValues(false);

            // 4.将折线数据设置给图表
        }
        int barAmount = lineData.getDataSets().size(); //需要显示柱状图的类别 数量
//设置组间距占比30% 每条柱状图宽度占比 70% /barAmount  柱状图间距占比 0%
        float groupSpace = 0.28f; //柱状图组之间的间距
        float barSpace = 0.02f;
        float barWidth = ((1f - groupSpace) / barAmount)-barSpace;

//设置柱状图宽度
        lineData.setBarWidth(barWidth);
//(起始点、柱状图组间距、柱状图之间间距)
        lineData.groupBars(0f, groupSpace, barSpace);
        barChart.setData(lineData);
        barChart.invalidate();
    }

    public BarDataSet setChartData(String name, List<BarEntry> yVals1, int color, int fillColor) {
        BarDataSet barDataSet = new BarDataSet(yVals1, name);
        barDataSet.setColor(getContext().getResources().getColor(color));
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.f);
        //不显示柱状图顶部值
        barDataSet.setDrawValues(false);

        return barDataSet;


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
        pvCustomTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvCenter.setText(DateUtil.dateToString(DateUtil.DATE_FORMAT, date));
                currDate = date.getTime();
                getData();
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



}