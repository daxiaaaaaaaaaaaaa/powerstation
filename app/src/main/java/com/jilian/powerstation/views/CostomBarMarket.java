package com.jilian.powerstation.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
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
import com.google.gson.Gson;
import com.jilian.powerstation.R;
import com.jilian.powerstation.manege.CharDateManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class CostomBarMarket implements IMarker {
    final String TAG = "CostomBarMarket";
    BarChart barChart;
    private HashMap<String, List<Entry>> entryMap;
    private Entry mEntry;
    private Highlight highlight;
    private HashMap<String, Integer> colorMap;
    private HashMap<String, String> valueMap;
    private HashMap<String, Highlight> mHighlightMap;
    private List<Highlight> mHighlights;
    private float width, height;
    private Context context;
    private Paint paint;
    private RectF rect;

    private int bgColor;


    public CostomBarMarket(Context context, float width, float height) {
        this.width = width;
        this.height = height;
        this.context = context.getApplicationContext();
        entryMap = new HashMap<>();
        colorMap = new HashMap<>();
        valueMap = new HashMap<>();
        mHighlightMap = new HashMap<>();
        mHighlights = new ArrayList<>();

        init();
    }

    public CostomBarMarket(Context context, float width, float height, int type) {
        this(context, width, height);
    }

    public CostomBarMarket(Context context, BarChart barChart, float width, float height, int type) {
        this(context, width, height, type);
        this.barChart = barChart;
    }

    private void init() {
        bgColor = context.getResources().getColor(R.color.color_5A8CC6);
        paint = new Paint();
        rect = new RectF();
    }

    public void putValue(String key, List<Entry> value, int color) {
        entryMap.put(key, value);
        colorMap.put(key, color);
    }

    @Override
    public MPPointF getOffset() {
        return null;
    }

    @Override
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        return null;
    }


    public void refreshContent(Entry e, Highlight highlight, boolean isUser) {
        refreshContent(e, highlight);
    }

    int indext = 10;
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        this.mEntry = e;
        this.highlight = highlight;
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
            Log.e(TAG, "11111111111------------>" + minX+"---"+maxX);
            Log.e(TAG, "22222222222------------>" + new Gson().toJson(mHighlights));
        }

    }

    @Override
    public void draw(Canvas canvas, float posX, float posY) {
        paint.setColor(bgColor);
        paint.setAlpha(120);
        canvas.drawRect(rect, paint);
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
