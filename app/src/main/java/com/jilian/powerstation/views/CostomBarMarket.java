package com.jilian.powerstation.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.google.gson.Gson;
import com.jilian.powerstation.R;
import com.jilian.powerstation.manege.BaseMarket;
import com.jilian.powerstation.manege.CharDateManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CostomBarMarket extends BaseMarket {
    final String TAG = "CostomBarMarket";
    private BarChart barChart;
    private BarEntry mEntry;
    private Highlight mHighlight;
    private HashMap<String, List<BarEntry>> entryMap;
    private List<String> mTitleList;
    private HashMap<String, Integer> colorMap;
    private Context context;
    private Paint paint;
    private Paint bgPaint;
    private int strokerColor;
    private Paint mTextPaint;
    private RectF rect;
    private RectF rect1;
    private float groupSpace = 0.2f; //柱状图组之间的间距
    private float barSpace = 0f;
    private int bgColor;
    private int textColor;
    private String title = "title";
    private String mUnit = "";

    public void setmUnit(String mUnit) {
        this.mUnit = mUnit;
    }

    private void init() {
        bgColor = context.getResources().getColor(R.color.color_5A8CC6);
        textColor = context.getResources().getColor(R.color.color_222222);
        strokerColor = context.getResources().getColor(R.color.color_C78A92);

        rect = new RectF();
        rect1 = new RectF();
        mTextPaint = new Paint();

        mTextPaint = new Paint();
        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(Utils.convertDpToPixel(12));
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);

        paint = new Paint();
        paint.setColor(bgColor);
        paint.setAlpha(120);

        bgPaint = new Paint();
        bgPaint.setColor(bgColor);
        bgPaint.setAlpha(120);
    }

    public CostomBarMarket(Context context, BarChart barChart, OnMarketBackCall onMarketBackCall) {
        this.context = context.getApplicationContext();
        this.barChart = barChart;
        this.onMarketBackCall = onMarketBackCall;
        entryMap = new HashMap<>();
        colorMap = new HashMap<>();
        mTitleList = new ArrayList<>();
        init();
    }
    public CostomBarMarket(Context context, BarChart barChart) {
        this.context = context.getApplicationContext();
        this.barChart = barChart;
        entryMap = new HashMap<>();
        colorMap = new HashMap<>();
        mTitleList = new ArrayList<>();
        init();
    }

    public void putValue(String key, List<BarEntry> value, int color) {
        entryMap.put(key, value);
        colorMap.put(key, color);
        mTitleList.add(key);
    }

    @Override
    public MPPointF getOffset() {
        return null;
    }

    @Override
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        return null;
    }

    boolean isClear = false;

    public void refreshContent() {
        isClear = true;
        refreshContent(mEntry, mHighlight);
    }

    public void setIsClear() {
        isClear = false;
    }

    public void refreshContent(Entry e, Highlight highlight) {
        this.mEntry = (BarEntry) e;
        this.mHighlight = highlight;

        if (barChart != null && !isClear) {
            if (onMarketBackCall!=null){
                title = (String) onMarketBackCall.onBackCall((int) mEntry.getX());
            }
            if (barChart.getData().getDataSets().size()>1){
                barSpace = 0.05f;
            }
            rect = getBarRect(mEntry, highlight);
        }
        Log.e(TAG, new Gson().toJson(mTitleList));
        Log.e(TAG, new Gson().toJson(entryMap));

    }


    @Override
    public void draw(Canvas canvas, float posX, float posY) {
        if (!isClear) {
            canvas.drawRect(rect, paint);
            int len = mTitleList.size();
            int maxWidth = (int) (getTextWidth() + Utils.convertDpToPixel(40));
            int maxHeight = getTextHeight();
            rect1.left = rect.right;
            rect1.right = rect.right + maxWidth;
            rect1.bottom = posY;
            rect1.top = posY - maxHeight;
            if (rect.bottom < rect1.bottom) {
                rect1.bottom = rect.bottom;
                rect1.top = rect1.bottom - maxHeight;
            } else if (rect.top > rect1.top) {
                rect1.top = rect.top;
                rect1.bottom = rect1.top + maxHeight;
            }
            int width = barChart.getWidth();

            if (rect1.right > width) {
                rect1.right = rect.left;
                rect1.left = rect.left - maxWidth;
            }
            bgPaint.reset();
            bgPaint.setColor(Color.WHITE);
            bgPaint.setAlpha(120);
            canvas.drawRect(rect1, bgPaint);

            bgPaint.reset();
            bgPaint.setStrokeWidth(5);
            bgPaint.setStyle(Paint.Style.STROKE);
            bgPaint.setColor(strokerColor);
            canvas.drawRect(rect1, bgPaint);


            mTextPaint.setColor(textColor);
            mTextPaint.setTextSize(Utils.convertDpToPixel(12));
            mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
            setText(canvas, "", 0, title);
            for (int item = 0; item < mTitleList.size(); item++) {
                String key = mTitleList.get(item);
                int color = colorMap.get(key);

                int index = (int) mEntry.getX();
                List<BarEntry> list = entryMap.get(key);
                if (index < 0) {
                    index = 0;
                }
                if (index >= list.size()) {
                    continue;
                }
                String value = key + ":" + list.get(index).getY() + mUnit;
                setNumberCircle(canvas, color, item + 1);
                setText(canvas, key, item + 1, value);
            }
        }
    }

    /**
     * 圆点
     *
     * @param canvas
     * @param color
     * @param position
     */
    private void setNumberCircle(Canvas canvas, int color, int position) {
        bgPaint.reset();
        bgPaint.setColor(color);
        float x = rect1.left + Utils.convertDpToPixel(16);
        float y = rect1.top + Utils.convertDpToPixel(8) + Utils.convertDpToPixel(20) * position + Utils.convertDpToPixel(10);
        canvas.drawCircle(x, y, Utils.convertDpToPixel(6), bgPaint);
    }

    /**
     * 值
     *
     * @param canvas
     * @param key
     * @param position
     */
    private void setText(Canvas canvas, String key, int position, String value) {
        float x = rect1.left + Utils.convertDpToPixel(32);
        float y = rect1.top + Utils.convertDpToPixel(8) + Utils.convertDpToPixel(20) * position + Utils.convertDpToPixel(14);
        if (position == 0 && !TextUtils.isEmpty(title)) {
            value = title;
            x = rect1.left + Utils.convertDpToPixel(10);
        } else if (TextUtils.isEmpty(value)) {
            value = "0" + mUnit;
        }
        canvas.drawText(value, x, y, mTextPaint);
    }


    public int getTextWidth() {
        int maxWidth = 0;
        for (String title : mTitleList) {
            int index = (int) mEntry.getX();
            List<BarEntry> list = entryMap.get(title);
            if (index < 0) {
                index = 0;
            }
            if (index >= list.size()) {
                continue;
            }
            String value = title + ":" + list.get(index).getY() + mUnit;
            int textWidth = Utils.calcTextWidth(mTextPaint, value);
            maxWidth = maxWidth > textWidth ? maxWidth : textWidth;
        }
        return (int) maxWidth;
    }

    public int getTextHeight() {
        int len = mTitleList.size();
        if (TextUtils.isEmpty(title)) {
            return (int) (Utils.convertDpToPixel(8) * 2 + Utils.convertDpToPixel(20) * len);
        } else {
            return (int) (Utils.convertDpToPixel(8) * 2 + Utils.convertDpToPixel(20) * (len + 1));
        }
    }


    public RectF getBarRect(BarEntry barEntry, Highlight highlight) {
        RectF rect = new RectF();
        if (barChart != null) {
            RectF rectF = barChart.getBarBounds(barEntry);
            int mCount = mTitleList.size();
            int mCurrCount = highlight.getDataSetIndex();
            float groupSpaceHalt = getGroupSpace(barEntry) / 2;
            float barSpace = getBarSpace(barEntry);
            float barWidth = rectF.right - rectF.left;
            float leftSpace = barWidth / 2 + groupSpaceHalt + barSpace * mCurrCount + barWidth * mCurrCount;
            float rightSpace = barWidth / 2 + groupSpaceHalt + barSpace * (mCount - 1 - mCurrCount) + barWidth * (mCount - 1 - mCurrCount);
            float left = highlight.getXPx() - leftSpace;
            float right = highlight.getXPx() + rightSpace;
            float top = 5;
            float bottom = rectF.bottom;
            rect.set(left, top, right, bottom);
        } else {
            rect.set(0, 0, 0, 0);
        }
        return rect;
    }

    public float getBarWidth(BarEntry barEntry) {
        float barWidthHalf = 0;
        if (barChart != null) {
            RectF rectF = barChart.getBarBounds(barEntry);
            barWidthHalf = rectF.right - rectF.left;
        }
        return barWidthHalf;
    }

    public float getGroupWidth(BarEntry barEntry) {
        if (barChart != null) {
            float count = mTitleList.size() * 1F;
            float barPersent = (1f - groupSpace - barSpace * count) / count;
            float barWidth = getBarWidth(barEntry);
            float groupWidth = barWidth / barPersent;
            return groupWidth;
        }
        return 0;
    }

    public float getGroupSpace(BarEntry barEntry) {
        if (barChart != null) {
            return (getGroupWidth(barEntry) * groupSpace) / 2;
        }
        return 0;
    }

    public float getBarSpace(BarEntry barEntry) {
        if (barChart != null) {
            return (getGroupWidth(barEntry) * barSpace);
        }
        return 0;
    }


    public void clear() {
        entryMap.clear();
        colorMap.clear();
        mTitleList.clear();
    }
}
