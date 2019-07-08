package com.jilian.powerstation.manege;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.baidu.mapapi.map.Marker;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import com.jilian.powerstation.views.CostomMarket;
import com.jilian.powerstation.views.TMarket;

import java.util.List;

/**
 * Created by cxz on 2019-07-08
 * <p>
 * Discrebe:
 */
public class CharBarManage {

    private BarChart lc;
    private TMarket tMarket;
    private Context context;
    private float mYAxisTextSize = 12f;
    private float mXAxisTextSize = 12f;

    public CharBarManage(BarChart lc, TMarket tMarket, Context context) {
        this.lc = lc;
        this.tMarket = tMarket;
        this.context = context;
        setLineChar();
    }

    private void setLineChar(){
        lc.setExtraOffsets(24f, 0f, 24f, 0f);
        lc.animateXY(3000, 3000); // XY动画
        lc.setDrawBorders(false);
        //不绘制左右的Y
        lc.getAxisLeft().setDrawAxisLine(false);
        lc.getAxisRight().setDrawAxisLine(false);
        //不缩放
        lc.setScaleXEnabled(false);
        lc.setScaleYEnabled(false);

        lc.getLegend().setEnabled(false);
        lc.getDescription().setText("");
    }

    public void removeAll(){
        if (lc.getBarData()==null) return;
        lc.getBarData().clearValues();
        lc.invalidate();
    }

    /**
     * 图例
     */
    public CharBarManage setLegend() {
        Legend legend = lc.getLegend();
        legend.setForm(Legend.LegendForm.LINE); // 图形：线
        legend.setFormSize(14f); // 图形大小
        legend.setFormLineWidth(9f); // 线宽小于如下大小绘制出平躺长方形
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // 图例在水平线上的对齐方式：右对齐
        legend.setTextColor(Color.WHITE);
        return this;
    }


    /**
     * Y轴
     */
    public CharBarManage setYAxis(float maximum, float minimum, float granularity) {
        // 左边Y轴
        final YAxis yAxisLeft = lc.getAxisLeft();
        yAxisLeft.setAxisMaximum(maximum); // 设置Y轴最大值
        yAxisLeft.setAxisMinimum(minimum); // 设置Y轴最小值
        yAxisLeft.setGranularity(granularity); // 设置间隔尺寸
        yAxisLeft.setTextSize(mYAxisTextSize); // 文本大小为12dp
        yAxisLeft.setTextColor(Color.BLACK); // 文本颜色为灰色
        yAxisLeft.setDrawGridLines(false); // 绘制网格线
//        lc.getAxisLeft().enableGridDashedLine(5f, 10f, 0f);

        // 右侧Y轴
        lc.getAxisRight().setEnabled(false); // 不启用
        //是否展示网格线
        lc.setDrawGridBackground(false);
        lc.getAxisRight().setDrawGridLines(false);
        lc.getAxisLeft().setDrawGridLines(true);
        //设置X Y轴网格线为虚线（实体线长度、间隔距离、偏移量：通常使用 0）
        lc.getAxisLeft().enableGridDashedLine(5f, 10f, 0f);
        return this;
    }

    public CharBarManage setXAxis(float maximum, float minimum, float granularity, int labelCount, IAxisValueFormatter iAxisValueFormatter) {
        // X轴
        XAxis xAxis = lc.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 在底部
        xAxis.setAvoidFirstLastClipping(false);
        xAxis.setDrawGridLines(false); // 不绘制网格线
        xAxis.setLabelCount(labelCount); // 设置标签数量
        xAxis.setTextColor(Color.BLACK); // 文本颜色为灰色
        xAxis.setTextSize(mXAxisTextSize); // 文本大小为12dp
        xAxis.setGranularity(granularity); // 设置间隔尺寸
        xAxis.setAxisMinimum(minimum); // 设置X轴最小值
        xAxis.setAxisMaximum(maximum); // 设置X轴最大值
        xAxis.setLabelRotationAngle(-30); //X轴旋转
        // 设置标签的显示格式
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(){});
        if (iAxisValueFormatter != null) {
            xAxis.setValueFormatter(iAxisValueFormatter);
        }
        //是否展示网格线
        xAxis.setDrawGridLines(false);
        return this;
    }

    public BarDataSet setChartData(String name, List<BarEntry> yVals1, int color, int fillColor) {
        BarDataSet barDataSet = new BarDataSet(yVals1, name);
        barDataSet.setColor(context.getResources().getColor(color));
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.f);
        //不显示柱状图顶部值
        barDataSet.setDrawValues(false);

        return barDataSet;


    }

    public void setData(BarData lineData) {
        int barAmount = lineData.getDataSets().size(); //需要显示柱状图的类别 数量
        //设置组间距占比30% 每条柱状图宽度占比 70% /barAmount  柱状图间距占比 0%
        float groupSpace = 0.28f; //柱状图组之间的间距
        float barSpace = 0.02f;
        float barWidth = ((1f - groupSpace) / barAmount)-barSpace;

        //设置柱状图宽度
        lineData.setBarWidth(barWidth);
        //(起始点、柱状图组间距、柱状图之间间距)
        lineData.groupBars(0f, groupSpace, barSpace);
        lineData.setDrawValues(false);
        lc.setData(lineData);
        lc.invalidate();
    }
}
