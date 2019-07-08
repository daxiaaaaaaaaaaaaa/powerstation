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
import com.github.mikephil.charting.charts.LineChart;
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
import com.jilian.powerstation.common.dto.ReportDto;
import com.jilian.powerstation.common.dto.ReportListDto;
import com.jilian.powerstation.manege.CharBarManage;
import com.jilian.powerstation.manege.CharDateManager;
import com.jilian.powerstation.modul.viewmodel.ReportViewModel;
import com.jilian.powerstation.utils.DateUtil;
import com.jilian.powerstation.views.TMarket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SiteThreeFargement extends BaseFragment implements IAxisValueFormatter {
    private TextView tvCenter;
    private TimePickerView pvCustomTime;
    private String sn;
    private long currDate;
    private ReportViewModel reportViewModel;

    private BarChart barChart;
    private TMarket tMarket;
    private CharBarManage charManager;
    private List<ReportDto> mReportDto;

    @Override
    protected void loadData() {
    }

    @Override
    protected void createViewModel() {
        reportViewModel = ViewModelProviders.of(this).get(ReportViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_site_three;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        barChart = view.findViewById(R.id.lineChart);
//        tMarket = new CostomMarket(getContext(), DisplayUtil.getScreenWidth(getContext()), getContext().getResources().getDimension(R.dimen.widget_size_350), 0);
        tMarket = new TMarket();
        charManager = new CharBarManage(barChart,tMarket,getContext());

        tvCenter = (TextView) view.findViewById(R.id.tv_center);
        initCustomTimePicker();
    }

    @Override
    protected void initData() {
        // 设置上下左右偏移量
        charManager.setLegend(); // 设置图例
        setXAxis(); // 设置X轴
        sn = getActivity().getIntent().getStringExtra("sn");
        currDate = System.currentTimeMillis();
        getData();
    }

    /**
     *
     */
    public void getData() {
        if (sn == null) return;
        String startTime = DateUtil.getDayBegin(currDate);
        String endTime = DateUtil.getDayEnd(currDate);
        reportViewModel.addReportData(sn, startTime, endTime, 2);
        reportViewModel.getReportData().observe(this, new Observer<BaseDto<ReportListDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<ReportListDto> reportListDtoBaseDto) {
                setData(reportListDtoBaseDto.getData().getRows());
            }
        });
    }

    @Override
    protected void initListener() {
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                tMarket.refreshContent(e, h);
            }

            @Override
            public void onNothingSelected() {
            }
        });
        tvCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvCustomTime.show();
            }
        });
    }


    public void setData(List<ReportDto> rows) {
        BarData barData = new BarData();
        float totalValue1 = 0;
        float totalValue2 = 0;
        float totalValue3 = 0;
        float totalValue4 = 0;
        if (rows != null && !rows.isEmpty()) {
            mReportDto = rows;
            charManager.setXAxis(rows.size(), 0, 1, rows.size(), this); // 设置X轴
            List<BarEntry> yVals1 = new ArrayList<>();
            List<BarEntry> yVals2 = new ArrayList<>();
            List<BarEntry> yVals3 = new ArrayList<>();
            List<BarEntry> yVals4 = new ArrayList<>();
            int maxValue = 50;
            int minValue = 0;
            for (int index = 0, len = rows.size(); index < len; index++) {
                ReportDto dto = rows.get(index);
                int value1 = dto.getPvProduction();
                int value2 = dto.getLoadProduction();
                int value3 = dto.getGridPower();
                int value4 = dto.getCdPower();

                yVals1.add(new BarEntry(index + 1, value1));
                yVals2.add(new BarEntry(index + 1, value2));
                yVals3.add(new BarEntry(index + 1, value3));
                yVals4.add(new BarEntry(index + 1, value4));

                maxValue = maxValue > value1 ? maxValue : value1;
                maxValue = maxValue > value2 ? maxValue : value2;
                maxValue = maxValue > value3 ? maxValue : value3;
                maxValue = maxValue > value4 ? maxValue : value4;


                minValue = minValue < value1 ? minValue : value1;
                minValue = minValue < value2 ? minValue : value2;
                minValue = minValue < value3 ? minValue : value3;
                minValue = minValue < value4 ? minValue : value4;

                totalValue1 += value1;
                totalValue2 += value2;
                totalValue3 += value3;
                totalValue4 += value4;
            }
//            tvTotal1.setText(totalValue1 + "");
//            tvTotal2.setText(totalValue2 + "");
//            tvTotal3.setText(totalValue3 + "");
//            tvTotal4.setText(totalValue4 + "");
            barData.addDataSet(charManager.setChartData("PV", yVals1, R.color.color_chart_three, R.drawable.bg_color3));
            barData.addDataSet(charManager.setChartData("Grid", yVals2, R.color.color_chart_two, R.drawable.bg_color2));
//            barData.addDataSet(charManager.setChartData("Load", yVals3, R.color.color_chart_one, R.drawable.bg_color1));
//            barData.addDataSet(charManager.setChartData("Battery", yVals4, R.color.color_chart_four, R.drawable.bg_color4));
            charManager.setYAxis(maxValue * 2, minValue, (maxValue - minValue) / 10); // 设置Y轴
            charManager.setData(barData);

        }else {
            charManager.removeAll();
        }

    }

    private void setXAxis() {
        // X轴
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 在底部
        xAxis.setDrawGridLines(false); // 不绘制网格线
        xAxis.setLabelCount(7); // 设置标签数量
        xAxis.setTextColor(Color.BLACK); // 文本颜色为灰色
        xAxis.setTextSize(12f); // 文本大小为12dp
        xAxis.setGranularity(2f); // 设置间隔尺寸
        xAxis.setAxisMinimum(0f); // 设置X轴最小值
        xAxis.setAxisMaximum(14f); // 设置X轴最大值
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            public String getFormattedValue(float value, AxisBase axis) {
                return "" + value;
            }
        });
        //是否展示网格线
        xAxis.setDrawGridLines(false);

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

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return CharDateManager.getDates(mReportDto, value, "YYYY");
    }
}
