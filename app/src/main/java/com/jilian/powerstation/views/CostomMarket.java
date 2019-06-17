package com.jilian.powerstation.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;

import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.jilian.powerstation.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class CostomMarket implements IMarker {

    private HashMap<String, List<Entry>> entryMap;
    private Entry mEntry;
    private HashMap<String, Integer> colorMap;
    private HashMap<String, String> valueMap;
    private List<MarkerInfo> markerInfos;
    private float width, height;
    private Context context;
    Paint textPaint;
    Paint paint;
    RectF rect;

    int bgColor;
    int strokerColor;
    int textColor;

    public CostomMarket(Context context, float width, float height) {
        this.width = width;
        this.height = height;
        this.context = context.getApplicationContext();
        entryMap = new HashMap<>();
        colorMap = new HashMap<>();
        valueMap = new HashMap<>();
        markerInfos = new ArrayList<>();
        init();
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
        rect.left = Utils.convertDpToPixel(100);
        rect.top = Utils.convertDpToPixel(100);
        rect.right = width - Utils.convertDpToPixel(100);
        rect.bottom = height - Utils.convertDpToPixel(100);
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
        this.mEntry = e;
        markerInfos = getEntrys(mEntry);
    }

    @Override
    public void draw(Canvas canvas, float posX, float posY) {

        Set<String> keys = colorMap.keySet();
        int len = keys.size();
        float bottom = height - 75 * (len+1);
        float top = bottom - Utils.convertDpToPixel(20) * (len + 1);
        rect.bottom = bottom + Utils.convertDpToPixel(8);
        rect.top = top - Utils.convertDpToPixel(8);
        paint.setColor(bgColor);
        paint.setAlpha(120);
        canvas.drawRect(rect, paint);

        paint.reset();
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(strokerColor);
        canvas.drawRect(rect, paint);

        paint.reset();
        int position = 0;
        for (String key : keys) {
            int color = colorMap.get(key);
            setCircle(canvas, posX, color, len-position);
            setNumberCircle(canvas, color, len-position);
            setText(canvas, key, position);
            position++;
        }

    }

    /**
     * 圆点
     *
     * @param canvas
     * @param color
     * @param position
     */
    public void setNumberCircle(Canvas canvas, int color, int position) {
        paint.setColor(color);
        float x = rect.left + Utils.convertDpToPixel(16) + 12;
        float y = rect.top + Utils.convertDpToPixel(8) + Utils.convertDpToPixel(20) * position + Utils.convertDpToPixel(10);
        canvas.drawCircle(x, y, 12, paint);
    }

    /**
     * @param canvas
     * @param posX
     * @param color
     * @param position
     */
    public void setCircle(Canvas canvas, float posX, int color, int position) {
        paint.setColor(color);
        paint.setAlpha(95);
        canvas.drawCircle(posX, height - 75 * position, 25, paint);
        paint.reset();
        paint.setColor(color);
        canvas.drawCircle(posX, height - 75 * position, 10, paint);
    }

    /**
     * 值
     *
     * @param canvas
     * @param key
     * @param position
     */
    public void setText(Canvas canvas, String key, int position) {
        String value = valueMap.get(key);
        if (TextUtils.isEmpty(value)) {
            value = "0kWh";
        }
        float x = rect.left + Utils.convertDpToPixel(32);
        float y = rect.top + Utils.convertDpToPixel(8) + Utils.convertDpToPixel(20) * position + Utils.convertDpToPixel(14);
        canvas.drawText(value, x, y, textPaint);
    }

    public List<MarkerInfo> getEntrys(Entry mEntry) {
        List<MarkerInfo> markerInfos = new ArrayList<>();
        Set<String> kays = entryMap.keySet();
        for (String key : kays) {
            List<Entry> entries = entryMap.get(key);
            if (entries == null || entries.isEmpty()) {
                continue;
            }
            valueMap.put(key, key + ": " + 0 + "kWh");
            for (Entry entry : entries) {
                if (entry.getX() == mEntry.getX()) {
                    valueMap.put(key, key + ": " + entry.getY() + "kWh");
                    markerInfos.add(new MarkerInfo(key, entry.getY() + "kWh", colorMap.get(key)));
                }
            }
        }
        return markerInfos;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    class MarkerInfo {
        private String name = "";
        private String value = "0";
        private int color = 0;

        public MarkerInfo(String name, String value, int color) {
            this.name = name == null ? "" : name;
            this.value = value == null ? "" : value;
            this.color = color;
        }
    }
}
