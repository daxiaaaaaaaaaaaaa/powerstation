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

import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.jilian.powerstation.R;
import com.jilian.powerstation.manege.CharDateManager;

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
    private String title = "";
    private Paint textPaint;
    private Paint paint;
    private RectF rect;
    private RectF rect1;

    private int type = -1; //X轴的数据类型
    private int bgColor;
    private int strokerColor;
    private  int textColor;
    private float groupSpace = 0;


    public CostomMarket(Context context, float width, float height) {
        this.width = width;
        this.height = height;
        this.type = -1;
        this.context = context.getApplicationContext();
        entryMap = new HashMap<>();
        colorMap = new HashMap<>();
        valueMap = new HashMap<>();
        markerInfos = new ArrayList<>();
        init();
    }

    public CostomMarket(Context context, float width, float height, int type) {
        this.width = width;
        this.height = height;
        this.type = type;
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
        rect1 = new RectF();
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
        title = CharDateManager.getDateForm(type, mEntry.getX());
    }


    @Override
    public void draw(Canvas canvas, float posX, float posY) {
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

        Log.e("TAG_LLLL","------------>"+groupSpace);
        paint.reset();
        paint.setColor(Color.RED);
        rect1.left = 0;
        rect1.top = 0;
        rect1.right = groupSpace;
        rect1.bottom = height;
        canvas.drawRect(rect1, paint);

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
            setCircle(canvas, posX, color, len - position);
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
            value = "0kWh";
        }
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

    public void getGroupSpace(float groupSpace){
        this.groupSpace = groupSpace;
    }


}
