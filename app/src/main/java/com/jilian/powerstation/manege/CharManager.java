package com.jilian.powerstation.manege;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import com.jilian.powerstation.views.CostomMarket;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by cxz on 2019/6/15
 * <p>
 * Discrebe:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CharManager extends BaseChar {
    private LineChart lc;
    private CostomMarket tMarket;
    private Context context;
    private float mYAxisTextSize = 12f;
    private float mXAxisTextSize = 12f;

    public CharManager(LineChart lc, CostomMarket tMarket, Context context) {
        this.lc = lc;
        this.tMarket = tMarket;
        this.context = context;
        setLineChar();
    }

    private void setLineChar() {
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
        Description description = lc.getDescription();
        description.setTextAlign(Paint.Align.CENTER);
        description.setText("");
    }

    public void removeAll() {
        lc.clear();
        lc.notifyDataSetChanged();
        lc.invalidate();
    }

    /**
     * 图例
     */
    public CharManager setLegend() {
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
    public CharManager setYAxis(float maximum, float minimum, float granularity) {
        // 左边Y轴
        final YAxis yAxisLeft = lc.getAxisLeft();
        if (maximum == 0) {
            yAxisLeft.setAxisMaximum(10); // 设置Y轴最大值
        }
        yAxisLeft.setAxisMinimum(minimum); // 设置Y轴最小值
//        yAxisLeft.setGranularity(granularity); // 设置间隔尺寸
        yAxisLeft.setTextSize(mYAxisTextSize); // 文本大小为12dp
        yAxisLeft.setTextColor(Color.BLACK); // 文本颜色为灰色
        yAxisLeft.setDrawGridLines(false); // 绘制网格线
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

    public CharManager setXAxis(float maximum, float minimum, float granularity, int labelCount, IAxisValueFormatter iAxisValueFormatter) {
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
        xAxis.setAxisMaximum(maximum + 1); // 设置X轴最大值
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

    public LineDataSet setChartData(String name, List<Entry> yVals1, int color, int fillColor) {
        int mColor = context.getResources().getColor(color);
        tMarket.putValue(name, yVals1, mColor);
        // 2.分别通过每一组Entry对象集合的数据创建折线数据集
        LineDataSet lineDataSet1 = new LineDataSet(yVals1, name);
        lineDataSet1.setDrawCircles(false);// 不绘制圆点
        lineDataSet1.setDrawCircleHole(false); // 不绘制圆洞，即为实心圆点
        lineDataSet1.setColor(mColor); // 设置为红色
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
            Drawable drawable = ContextCompat.getDrawable(context, fillColor);
            drawable.setAlpha(160);
            lineDataSet1.setFillDrawable(drawable);
        } else {
            lineDataSet1.setFillColor(Color.WHITE);
        }
        return lineDataSet1;
    }

    public void setData(LineData lineData) {
        lineData.setDrawValues(false);
        lc.setData(lineData);
        lc.invalidate();
    }
}
