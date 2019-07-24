package com.jilian.powerstation.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TMarket implements IMarker {
    BarChart barChart;
    private Entry mEntry;
    private List<Highlight> mHighlights;
    private RectF rect;
    private Path path;
    private  Paint mPaint1l;
    private  Paint mPaint12;

    public TMarket() {
        this(null);
    }

    public TMarket(BarChart barChart) {
        this.barChart = barChart;
        mHighlights = new ArrayList<>();
        rect = new RectF();
        path = new Path();
        mPaint1l = new Paint();
        mPaint12 = new Paint();
    }

    @Override
    public MPPointF getOffset() {
        return null;
    }

    @Override
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        return null;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        this.mEntry = e;
        if (barChart != null) {
            mHighlights = getHighlightsAtXValue(e.getX(), highlight.getYPx(), highlight.getYPx());
            float minX = 100000;
            float maxX = 0;
            for (Highlight highlight1 : mHighlights) {
                minX = minX > highlight1.getXPx() ? highlight1.getXPx() : minX;
                maxX = maxX < highlight1.getXPx() ? highlight1.getXPx() : maxX;
            }
            BarEntry barEntry = (BarEntry) e;
            RectF rectF = barChart.getBarBounds(barEntry);
            float barWidthHalf = rectF.right - rectF.left;
            float left = minX - barWidthHalf / 2 - 10;
            float right = maxX + barWidthHalf / 2 + 10;
            float top = 5;
            float bottom = rectF.bottom;
            rect.set(left, top, right, bottom);

            path.moveTo(rect.left, rect.bottom);
            path.lineTo(rect.left, rect.top);
            path.lineTo(rect.right, rect.top);
            path.lineTo(rect.right, rect.bottom);
        }
    }

    @Override
    public void draw(Canvas canvas, float posX, float posY) {
        Log.e("TAG_INFO","Canvas--------->"+posX+"/"+ mEntry.getX());
//        Paint paint = new Paint();
////        paint.setColor(Color.YELLOW);
////        // 绘制倒等腰三角线
////        Path path = new Path();
////        path.moveTo(posX, posY - Utils.convertDpToPixel(5));
////        path.lineTo(posX - Utils.convertDpToPixel(8), posY - Utils.convertDpToPixel(18));
////        path.lineTo(posX + Utils.convertDpToPixel(8), posY - Utils.convertDpToPixel(18));
////        path.close();
////        canvas.drawPath(path, paint);
////        // 绘制矩形
////        RectF rect = new RectF(
////                posX - Utils.convertDpToPixel(34),
////                posY - Utils.convertDpToPixel(51),
////                posX + Utils.convertDpToPixel(14),
////                posY - Utils.convertDpToPixel(17));
////        canvas.drawRect(rect, paint);
////        // 绘制文字，居于矩形框正中间
////        if (mEntry != null) {
////            String str = String.valueOf(mEntry.getX()+"W");
////            Paint textPaint = new Paint();
////            textPaint.setColor(Color.RED);
////            textPaint.setTextSize(Utils.convertDpToPixel(18));
////            textPaint.setTypeface(Typeface.DEFAULT_BOLD);
////            canvas.drawText(str,
////                    // x = 矩形左坐标 + （矩形宽度 - 文本宽度）/ 2
////                    rect.left + (Utils.convertDpToPixel(48) - Utils.calcTextWidth(textPaint, str)) / 2,
////                    // y = 矩形下坐标 + （矩形高度 - 文本高度）/ 2
////                    rect.bottom - (Utils.convertDpToPixel(24) - Utils.calcTextHeight(textPaint, str)) / 2,
////                    textPaint);
////        }

        mPaint1l.setColor(Color.WHITE);
        mPaint1l.setAlpha(120);
        mPaint12.setColor(Color.RED);
        mPaint12.setStrokeWidth(5);
        mPaint12.setStyle(Paint.Style.STROKE);

        canvas.drawRect(rect, mPaint1l);
        canvas.drawPath(path, mPaint12);
    }










    protected List<Highlight> mHighlightBuffer = new ArrayList<Highlight>();

    protected List<Highlight> getHighlightsAtXValue(float xVal, float x, float y) {

        mHighlightBuffer.clear();

        BarLineScatterCandleBubbleData data = barChart.getData();

        if (data == null)
            return mHighlightBuffer;

        for (int i = 0, dataSetCount = data.getDataSetCount(); i < dataSetCount; i++) {

            IDataSet dataSet = data.getDataSetByIndex(i);

            // don't include DataSets that cannot be highlighted
            if (!dataSet.isHighlightEnabled())
                continue;

            mHighlightBuffer.addAll(buildHighlights(dataSet, i, xVal, DataSet.Rounding.CLOSEST));
        }

        return mHighlightBuffer;
    }

    /**
     * An array of `Highlight` objects corresponding to the selected xValue and dataSetIndex.
     *
     * @param set
     * @param dataSetIndex
     * @param xVal
     * @param rounding
     * @return
     */
    protected List<Highlight> buildHighlights(IDataSet set, int dataSetIndex, float xVal, DataSet.Rounding rounding) {

        ArrayList<Highlight> highlights = new ArrayList<>();

        //noinspection unchecked
        List<Entry> entries = set.getEntriesForXValue(xVal);
        if (entries.size() == 0) {
            // Try to find closest x-value and take all entries for that x-value
            final Entry closest = set.getEntryForXValue(xVal, Float.NaN, rounding);
            if (closest != null) {
                //noinspection unchecked
                entries = set.getEntriesForXValue(closest.getX());
            }
        }

        if (entries.size() == 0)
            return highlights;

        for (Entry e : entries) {
            MPPointD pixels = barChart.getTransformer(
                    set.getAxisDependency()).getPixelForValues(e.getX(), e.getY());

            highlights.add(new Highlight(
                    e.getX(), e.getY(),
                    (float) pixels.x, (float) pixels.y,
                    dataSetIndex, set.getAxisDependency()));
        }

        return highlights;
    }
}
