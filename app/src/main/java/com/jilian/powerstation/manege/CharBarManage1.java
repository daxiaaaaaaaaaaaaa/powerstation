package com.jilian.powerstation.manege;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.BarLineChartTouchListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.jilian.powerstation.views.CostomBarMarket;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxz on 2019-07-08
 * <p>
 * Discrebe:
 */
public class CharBarManage1 extends BaseChar {
    private BarChart mBarChart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;
    private DecimalFormat mFormat;

    private CostomBarMarket mBarMarket;
    private float mYAxisTextSize = 12f;
    private float mXAxisTextSize = 12f;


    public CharBarManage1(BarChart barChart, CostomBarMarket mBarMarket) {
        this.mBarChart = barChart;
        this.mBarMarket = mBarMarket;
        leftAxis = mBarChart.getAxisLeft();
        rightAxis = mBarChart.getAxisRight();
        xAxis = mBarChart.getXAxis();
    }

    /**
     * 初始化LineChart
     */
    private void initLineChart() {
        mBarChart.setExtraOffsets(14f, 0f, 14f, 0f);

        mFormat = new DecimalFormat("#,###.##");
        //背景颜色
        mBarChart.setBackgroundColor(Color.WHITE);
        //是否显示网格背景
        mBarChart.setDrawGridBackground(false);
        //显示每条背景阴影
        mBarChart.setDrawBarShadow(false);


        //设置图标边框的颜色
        mBarChart.setBorderColor(Color.parseColor("#ff0000"));
//        mBarChart.setHighlightFullBarEnabled(false);
        mBarChart.setTouchEnabled(true); // 所有触摸事件,默认true
        mBarChart.setDragEnabled(true);    // 可拖动,默认true
        mBarChart.setScaleEnabled(false);   // 两个轴上的缩放,X,Y分别默认为true
        mBarChart.setScaleXEnabled(false);  // X轴上的缩放,默认true
        mBarChart.setScaleYEnabled(false);  // Y轴上的缩放,默认true
        mBarChart.setPinchZoom(false);  // X,Y轴同时缩放，false则X,Y轴单独缩放,默认false
        mBarChart.setDoubleTapToZoomEnabled(false); // 双击缩放,默认true
        mBarChart.setDragDecelerationEnabled(true);    // 抬起手指，继续滑动,默认true
        //设置X Y轴网格线为虚线（实体线长度、间隔距离、偏移量：通常使用 0）
        mBarChart.getAxisLeft().enableGridDashedLine(5f, 10f, 0f);
        //显示边界
        mBarChart.setDrawBorders(false);
        //设置XY动画效果
        mBarChart.animateY(1000, Easing.EasingOption.Linear);
        mBarChart.animateX(1000, Easing.EasingOption.Linear);
//      不显示描述信息
        mBarChart.getDescription().setEnabled(false);
//         图例设置
        Legend legend = mBarChart.getLegend();
        //不显示图例
        legend.setForm(Legend.LegendForm.NONE);
//        图例文字的大小
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setEnabled(false);
        //XY轴的设置


        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//      X轴最小间距
        xAxis.setGranularity(1f);
//      不绘制网格线
        xAxis.setDrawGridLines(false);
//      X轴字体样式
//        xAxis.setTypeface(Typeface.DEFAULT_BOLD);
//      设置X轴文字剧中对齐
        xAxis.setCenterAxisLabels(true);
        // 文本颜色为灰色
        xAxis.setTextColor(Color.BLACK);
// 文本大小为12dp
        xAxis.setTextSize(mXAxisTextSize);


//       保证Y轴从0开始，不然会上移一点
//        leftAxis.setDrawGridLines(false);
        // 线跟数据都不显示
        rightAxis.setEnabled(false); //右侧Y轴不显示
        rightAxis.setAxisMinimum(0f);

        leftAxis.setAxisMinimum(0f);
        leftAxis.setTextSize(mYAxisTextSize); // 文本大小为12dp
        leftAxis.setTextColor(Color.BLACK); // 文本颜色为灰色


        //不绘制左右的Y
        leftAxis.setDrawAxisLine(false);
        rightAxis.setDrawAxisLine(false);
        //是否展示网格线
        mBarChart.setDrawGridBackground(false);
        rightAxis.setDrawGridLines(false);
        leftAxis.setDrawGridLines(true);
        //设置X Y轴网格线为虚线（实体线长度、间隔距离、偏移量：通常使用 0）
        mBarChart.getAxisLeft().enableGridDashedLine(5f, 10f, 0f);


        if (mBarMarket != null) {
            mBarChart.setMarker(mBarMarket);
            mBarChart.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            x = event.getX();
                            y = event.getY();
                            mBarMarket.setIsClear();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if (Math.abs(x - event.getX()) > 15) {
                                mBarMarket.refreshContent();
                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            break;
                    }

                    return false;
                }
            });
            mBarChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    mBarMarket.refreshContent(e, h);
                }

                @Override
                public void onNothingSelected() {
                }
            });
        }
    }

    float x;
    float y;

    /**
     * 展示柱状图(一条)
     */
    public void showBarChart(String value, final List<Float> xAxisValues, List<Float> yAxisValues, String label, int color) {
        initLineChart();

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int j = 0; j < yAxisValues.size(); j++) {
            entries.add(new BarEntry(xAxisValues.get(j), yAxisValues.get(j)));
        }
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, label);
        barDataSet.setColor(color);
        //是否显示顶部的值
        barDataSet.setDrawValues(false);
//        文字的大小
        barDataSet.setValueTextSize(9f);

        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.0f);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        BarData data = new BarData(dataSets);
//      设置宽度
        data.setBarWidth(0.3f);
        xAxis.setLabelCount(xAxisValues.size(), true);
        xAxis.setDrawLabels(true);
        xAxis.setTextColor(Color.parseColor("#d5d5d5"));
        xAxis.setAxisLineColor(Color.parseColor("#d5d5d5"));
//        leftAxis.setLabelCount(yValues.length + 1, false);
        leftAxis.setAxisLineColor(Color.parseColor("#d5d5d5"));
//        设置Y轴的最小值和最大值
        leftAxis.setAxisMaximum(80f);
        leftAxis.setAxisMinimum(50f);
        mBarChart.setData(data);
    }


    public class MyYAxisValueFormatter implements IAxisValueFormatter {

        private String[] xValues;

        public MyYAxisValueFormatter(String[] yValues) {
            xValues = yValues;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
//            Log.e("TAG", "xValues[(int) value]====="+xValues[(int) value]);
            return mFormat.format(value) + "%";
        }
    }


    public class XAxisValueFormatter implements IAxisValueFormatter {

        private String[] xValues;

        public XAxisValueFormatter(String[] xValues) {
            this.xValues = xValues;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            Log.e("TAG", "============" + value);
            return xValues[(int) value];
        }

    }

    /**
     * 展示柱状图(多条)
     * +-+
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param labels
     * @param colours
     */
    public CharBarManage1 showMoreBarChart(String value, final List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours, IAxisValueFormatter iAxisValueFormatter) {
        initLineChart();
        BarData data = new BarData();
        if (mBarMarket != null) {
            mBarMarket.clear();
            mBarMarket.setmUnit(value);
        }
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<BarEntry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {
                entries.add(new BarEntry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
            }
            BarDataSet barDataSet = new BarDataSet(entries, labels.get(i));
            if (mBarMarket != null) {
                mBarMarket.putValue(labels.get(i), entries, colours.get(i));
            }
            barDataSet.setDrawValues(false);
            barDataSet.setColor(colours.get(i));
            barDataSet.setValueTextColor(colours.get(i));
            barDataSet.setValueTextSize(10f);
            barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            data.addDataSet(barDataSet);
        }
        int amount = yAxisValues.size();
        int barAmount = yAxisValues.size(); //需要显示柱状图的类别 数量

        if (barAmount > 1) {
            //设置组间距占比30% 每条柱状图宽度占比 70% /barAmount  柱状图间距占比 0%
            float groupSpace = 0.2f; //柱状图组之间的间距
            float barSpace = 0.05f;
            float barWidth = (1f - groupSpace) / amount - barSpace;
            //设置柱状图宽度
            data.setBarWidth(barWidth);
            //(起始点、柱状图组间距、柱状图之间间距)
            data.groupBars(0f, groupSpace, barSpace);
            xAxis.setCenterAxisLabels(true);
        }else {
            //      设置宽度
            data.setBarWidth(0.3f);
            xAxis.setCenterAxisLabels(false);
        }
        xAxis.setLabelCount(xAxisValues.size() - 1, false);
        xAxis.setValueFormatter(iAxisValueFormatter);
        mBarChart.setData(data);
        return this;
    }

    /**
     * 设置Y轴值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public CharBarManage1 setYAxis(float max, float min, int labelCount) {
        if (max < min) {
            return this;
        }
        leftAxis.setAxisMaximum(max);
        leftAxis.setAxisMinimum(min);
        leftAxis.setLabelCount(labelCount, false);

        rightAxis.setAxisMaximum(max);
        rightAxis.setAxisMinimum(min);
        rightAxis.setLabelCount(labelCount, false);

        leftAxis.setTextSize(mYAxisTextSize); // 文本大小为12dp
        leftAxis.setTextColor(Color.BLACK); // 文本颜色为灰色
        return this;
    }

    /**
     * 设置X轴的值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public CharBarManage1 setXAxis(float max, float min, int labelCount) {
        int value = 0;
        if (labelCount <= 3) {
            value = 2;
        } else if (labelCount <= 5) {
            value = 1;
        }
        xAxis.setAxisMaximum(max + value);
        xAxis.setAxisMinimum(min - value);
        xAxis.setLabelCount(labelCount + value * 2, false);
        xAxis.setTextColor(Color.BLACK); // 文本颜色为灰色
        xAxis.setTextSize(mXAxisTextSize); // 文本大小为12dp

        return this;

    }

    /**
     * 设置高限制线
     *
     * @param high
     * @param name
     */
    public void setHightLimitLine(float high, String name, int color) {
        if (name == null) {
            name = "高限制线";
        }
        LimitLine hightLimit = new LimitLine(high, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
        leftAxis.addLimitLine(hightLimit);
    }


    /**
     * 设置低限制线
     *
     * @param low
     * @param name
     */
    public void setLowLimitLine(int low, String name) {
        if (name == null) {
            name = "低限制线";
        }
        LimitLine hightLimit = new LimitLine(low, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        leftAxis.addLimitLine(hightLimit);
        mBarChart.invalidate();
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        mBarChart.setDescription(description);
    }

    /**
     * 刷新
     */
    public CharBarManage1 invalidate() {
        if (mBarMarket != null) {
            mBarMarket.refreshContent();
        }
        mBarChart.invalidate();
        return this;
    }

    /**
     * 清理数据
     */
    public void clear() {
        mBarChart.clear();
    }

    public void setScalX(int size) {
        Matrix m = new Matrix();
        m.postScale(scaleNum(size), 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        mBarChart.getViewPortHandler().refresh(m, mBarChart, false);//将图表动画显示之前进行缩放
        mBarChart.invalidate();
    }


    public void removeAll() {
        mBarChart.clear();
        mBarChart.notifyDataSetChanged();
        mBarChart.invalidate();
    }
}