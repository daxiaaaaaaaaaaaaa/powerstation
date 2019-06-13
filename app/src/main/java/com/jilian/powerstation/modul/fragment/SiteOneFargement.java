package com.jilian.powerstation.modul.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
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
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.utils.DateUtil;
import com.jilian.powerstation.views.TMarket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SiteOneFargement extends BaseFragment {
    private TextView tvCenter;



    private LineChart lc;
    TMarket tMarket = new TMarket();
    private TimePickerView pvCustomTime;
    @Override
    protected void loadData() {

    }


    @Override
    protected void createViewModel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_site_one;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        lc = view.findViewById(R.id.lineChart);
        lc.setMarker(tMarket);
        lc.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                tMarket.refreshContent(e, h);
            }

            @Override
            public void onNothingSelected() {
            }
        });
        tvCenter = (TextView)view. findViewById(R.id.tv_center);
        initCustomTimePicker();
    }

    @Override
    protected void initData() {
        // 设置上下左右偏移量
        lc.setExtraOffsets(24f, 24f, 24f, 0f);
        lc.animateXY(3000, 3000); // XY动画
        setLegend(); // 设置图例
        setYAxis(); // 设置Y轴
        setXAxis(); // 设置X轴
        setData();

    }

    @Override
    protected void initListener() {
        tvCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvCustomTime.show();
            }
        });
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
                    lineDataSet = setChartData("123",yVals1, R.color.color_chart_three, R.drawable.bg_color3); // 设置图标数据

                    break;
                case 1:
                    yVals1.add(new Entry(17f, 0f));
                    yVals1.add(new Entry(18f, 13f));
                    yVals1.add(new Entry(20f, 16f));
                    yVals1.add(new Entry(23f, 3f));
                    yVals1.add(new Entry(28f, 13f));
                    yVals1.add(new Entry(30f, 0f));
                    lineDataSet = setChartData("1233",yVals1, R.color.color_chart_one, R.drawable.bg_color1); // 设置图标数据
                    break;
                case 2:
                    yVals1.add(new Entry(20f, 0f));
                    yVals1.add(new Entry(22f, 13f));
                    yVals1.add(new Entry(26f, 22));
                    yVals1.add(new Entry(28f, 5f));
                    yVals1.add(new Entry(29f, 22));
                    yVals1.add(new Entry(30f, 0f));
                    lineDataSet = setChartData("12223",yVals1, R.color.color_chart_two, R.drawable.bg_color2); // 设置图标数据
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
                    lineDataSet = setChartData("我认为",yVals1, R.color.color_chart_four, R.drawable.bg_color4); // 设置图标数据
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

    public LineDataSet setChartData(String name,List<Entry> yVals1, int color, int fillColor) {
        // 1.获取一或多组Entry对象集合的数据
        // 模拟数据1

        // 2.分别通过每一组Entry对象集合的数据创建折线数据集
        LineDataSet lineDataSet1 = new LineDataSet(yVals1, name);
        lineDataSet1.setDrawCircles(false);// 不绘制圆点
        lineDataSet1.setDrawCircleHole(false); // 不绘制圆洞，即为实心圆点
        lineDataSet1.setColor(getContext().getResources().getColor(color)); // 设置为红色
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
            Drawable drawable = ContextCompat.getDrawable(getActivity(), fillColor);
            drawable.setAlpha(160);
            lineDataSet1.setFillDrawable(drawable);
        } else {
            lineDataSet1.setFillColor(Color.WHITE);
        }
        return lineDataSet1;

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
        endDate.set(2029,01,01);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvCenter.setText(DateUtil.dateToString(DateUtil.DATE_FORMAT, date));

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