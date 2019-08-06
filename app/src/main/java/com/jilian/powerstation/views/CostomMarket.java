package com.jilian.powerstation.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.IMarker;
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
import com.jilian.powerstation.manege.BaseMarket;
import com.jilian.powerstation.manege.CharDateManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class CostomMarket extends BaseMarket {
    private LineChart lineChart;
    private HashMap<String, List<Entry>> entryMap;
    private Entry mEntry;
    private HashMap<String, Integer> colorMap;
    private HashMap<String, String> valueMap;
    private List<MarkerInfo> markerInfos;
    private HashMap<String, Highlight> mHighlightMap;
    private List<Highlight> mHighlights;
    private float width, height;
    private Context context;
    private String title = "title";
    private Paint textPaint;
    private Paint paint;
    private RectF rect;
    private RectF rect1;

    private int type = -1; //X轴的数据类型
    private int bgColor;
    private int strokerColor;
    private int textColor;
    private float groupSpace = 0;
    private String mUnit = "";


    public CostomMarket(Context context, float width, float height) {
        this.width = width;
        this.height = height;
        this.type = -1;
        this.context = context.getApplicationContext();
        entryMap = new HashMap<>();
        colorMap = new HashMap<>();
        valueMap = new HashMap<>();
        mHighlightMap = new HashMap<>();
        mHighlights = new ArrayList<>();
        markerInfos = new ArrayList<>();

        init();
    }

    public CostomMarket(Context context, float width, float height, int type) {
        this(context, width, height);
        this.type = type;
    }

    public CostomMarket(Context context, LineChart lineChart, float width, float height, int type) {
        this(context, width, height, type);
        this.lineChart = lineChart;
    }

    private void init() {
        bgColor = context.getResources().getColor(R.color.color_white);
        strokerColor = context.getResources().getColor(R.color.color_C78A92);
        textColor = context.getResources().getColor(R.color.color_222222);

        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(Utils.convertDpToPixel(12));
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);

        paint = new Paint();
        rect = new RectF();
        rect1 = new RectF();
        rect.left = Utils.convertDpToPixel(100);
        rect.top = Utils.convertDpToPixel(100);
        rect.right = width - Utils.convertDpToPixel(100);
        rect.bottom = height - Utils.convertDpToPixel(100);
    }

    public void clear() {
        entryMap.clear();
        colorMap.clear();
    }


    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
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

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if (isEnable) {
            this.mEntry = e;
            if (lineChart != null) {
                if (onMarketBackCall != null) {
                    title = (String) onMarketBackCall.onBackCall((int) mEntry.getX());
                }
                mHighlights = getHighlightsAtXValue(e.getX(), highlight.getYPx(), highlight.getYPx());
            }
            markerInfos = getEntrys(mEntry);
        }
    }


    @Override
    public void draw(Canvas canvas, float posX, float posY) {
        if (isEnable) {
            Set<String> keys = colorMap.keySet();
            int len = keys.size();
            //文字区域上下距离 8dp 文字一行是20dp
            float textHeight = 0;
            if (TextUtils.isEmpty(title)) {
                textHeight = Utils.convertDpToPixel(8) * 2 + Utils.convertDpToPixel(20) * len;
            } else {
                textHeight = Utils.convertDpToPixel(8) * 2 + Utils.convertDpToPixel(20) * (len + 1);
            }
            rect.bottom = height / 2 + textHeight / 2;
            rect.top = height / 2 - textHeight / 2;
            paint.setColor(bgColor);
            paint.setAlpha(120);
            canvas.drawRect(rect, paint);

            if (!TextUtils.isEmpty(title)) {
                textPaint.setColor(context.getResources().getColor(R.color.color_838383));
                setText(canvas, "", 0);
            }
            textPaint.setColor(textColor);

            paint.reset();
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(strokerColor);
            canvas.drawRect(rect, paint);

            paint.reset();
            int position = 0;
            for (String key : keys) {
                int color = colorMap.get(key);
                if (lineChart != null) {
                    setCircle(canvas, key, posX, color, len - position);
                } else {
                    setCircle(canvas, posX, color, len - position);
                }

                if (TextUtils.isEmpty(title)) {
                    setNumberCircle(canvas, color, len - position - 1);
                    setText(canvas, key, position);
                } else {
                    setNumberCircle(canvas, color, len - position);
                    setText(canvas, key, position + 1);
                }

                position++;
            }
        }
    }


    protected List<Highlight> mHighlightBuffer = new ArrayList<Highlight>();

    protected List<Highlight> getHighlightsAtXValue(float xVal, float x, float y) {

        mHighlightBuffer.clear();

        BarLineScatterCandleBubbleData data = lineChart.getData();

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
            MPPointD pixels = lineChart.getTransformer(
                    set.getAxisDependency()).getPixelForValues(e.getX(), e.getY());

            highlights.add(new Highlight(
                    e.getX(), e.getY(),
                    (float) pixels.x, (float) pixels.y,
                    dataSetIndex, set.getAxisDependency()));
        }

        return highlights;
    }


    /**
     * @param canvas
     * @param posX
     * @param color
     * @param position
     */
    private void setCircle(Canvas canvas, float posX, int color, int position) {
        paint.setColor(color);
        paint.setAlpha(95);
        float y = height - Utils.convertDpToPixel(42) - Utils.convertDpToPixel(22) * position;

        canvas.drawCircle(posX, y, Utils.convertDpToPixel(10), paint);
        paint.reset();
        paint.setColor(color);
        canvas.drawCircle(posX, y, Utils.convertDpToPixel(6), paint);
    }

    /**
     * @param canvas
     * @param color
     * @param position
     */
    private void setCircle(Canvas canvas, String key, float posX, int color, int position) {
        paint.setColor(color);
        paint.setAlpha(95);
        Log.e("TAG_LLLL", "Gson------------>4" + key);
        Highlight highlights = mHighlightMap.get(key);
        if (highlights==null){
            return;
        }
        float y = height - Utils.convertDpToPixel(42) - Utils.convertDpToPixel(22) * position;
        canvas.drawCircle(posX, highlights.getYPx(), Utils.convertDpToPixel(10), paint);
        paint.reset();
        paint.setColor(color);
        canvas.drawCircle(posX, highlights.getYPx(), Utils.convertDpToPixel(6), paint);
    }

    /**
     * 圆点
     *
     * @param canvas
     * @param color
     * @param position
     */
    private void setNumberCircle(Canvas canvas, int color, int position) {
        paint.setColor(color);
        float x = rect.left + Utils.convertDpToPixel(16);
        float y = rect.top + Utils.convertDpToPixel(8) + Utils.convertDpToPixel(20) * position + Utils.convertDpToPixel(10);
        canvas.drawCircle(x, y, Utils.convertDpToPixel(6), paint);
    }

    /**
     * 值
     *
     * @param canvas
     * @param key
     * @param position
     */
    private void setText(Canvas canvas, String key, int position) {
        String value = valueMap.get(key);
        float x = rect.left + Utils.convertDpToPixel(32);
        float y = rect.top + Utils.convertDpToPixel(8) + Utils.convertDpToPixel(20) * position + Utils.convertDpToPixel(14);
        if (position == 0 && !TextUtils.isEmpty(title)) {
            value = title;
            x = rect.left + Utils.convertDpToPixel(10);
        } else if (TextUtils.isEmpty(value)) {
            value = "0" + mUnit;
        }
        canvas.drawText(value, x, y, textPaint);
    }

    public List<MarkerInfo> getEntrys(Entry mEntry) {
        mHighlightMap.clear();
        valueMap.clear();
        List<MarkerInfo> markerInfos = new ArrayList<>();
        Set<String> kays = entryMap.keySet();
        for (String key : kays) {
            List<Entry> entries = entryMap.get(key);
            if (entries == null || entries.isEmpty()) {
                continue;
            }

            if (lineChart != null && mHighlights != null) {
                for (Highlight highlight : mHighlights) {
                    for (Entry entry : entries) {
                        if (entry.getX() == mEntry.getX()) {
                            if (entry.getX() == highlight.getX() && entry.getY() == highlight.getY()) {
                                mHighlightMap.put(key, highlight);
                            }
                            valueMap.put(key, key + ": " + entry.getY() + "kWh");
                            MarkerInfo info = new MarkerInfo(key, entry.getY() + "kWh", colorMap.get(key), entry.getX(), entry.getY());
                            markerInfos.add(info);
                        }

                    }

                }
            } else {
                for (Entry entry : entries) {
                    if (entry.getX() == mEntry.getX()) {
                        valueMap.put(key, key + ": " + entry.getY() + "kWh");

                        MarkerInfo info = new MarkerInfo(key, entry.getY() + "kWh", colorMap.get(key), entry.getX(), entry.getY());
                        markerInfos.add(info);
                    }
                }
            }
            Log.e("TAG_LLLL", "Gson------------>3" + new Gson().toJson(mHighlightMap));
        }
        return markerInfos;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    class MarkerInfo {
        private String name = "";
        private String value = "0";
        private float mValueX = 0f;
        private float mValueY = 0f;
        private int color = 0;

        public MarkerInfo(String name, String value, int color, float mValueX, float mValueY) {
            this.name = name == null ? "" : name;
            this.value = value == null ? "" : value;
            this.color = color;
            this.mValueX = mValueX;
            this.mValueY = mValueY;
        }
    }

    public void getGroupSpace(float groupSpace) {
        this.groupSpace = groupSpace;
    }


}
