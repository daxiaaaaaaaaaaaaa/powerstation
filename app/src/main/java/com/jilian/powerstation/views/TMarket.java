package com.jilian.powerstation.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;

import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

public class TMarket implements IMarker {

    private Entry mEntry;

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
    }

    @Override
    public void draw(Canvas canvas, float posX, float posY) {
        Log.e("TAG_INFO","Canvas--------->"+posX+"/"+ mEntry.getX());
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        // 绘制倒等腰三角线
        Path path = new Path();
        path.moveTo(posX, posY - Utils.convertDpToPixel(5));
        path.lineTo(posX - Utils.convertDpToPixel(8), posY - Utils.convertDpToPixel(18));
        path.lineTo(posX + Utils.convertDpToPixel(8), posY - Utils.convertDpToPixel(18));
        path.close();
        canvas.drawPath(path, paint);
        // 绘制矩形
        RectF rect = new RectF(
                posX - Utils.convertDpToPixel(34),
                posY - Utils.convertDpToPixel(51),
                posX + Utils.convertDpToPixel(14),
                posY - Utils.convertDpToPixel(17));
        canvas.drawRect(rect, paint);
        // 绘制文字，居于矩形框正中间
        if (mEntry != null) {
            String str = String.valueOf(mEntry.getX()+"W");
            Paint textPaint = new Paint();
            textPaint.setColor(Color.RED);
            textPaint.setTextSize(Utils.convertDpToPixel(18));
            textPaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText(str,
                    // x = 矩形左坐标 + （矩形宽度 - 文本宽度）/ 2
                    rect.left + (Utils.convertDpToPixel(48) - Utils.calcTextWidth(textPaint, str)) / 2,
                    // y = 矩形下坐标 + （矩形高度 - 文本高度）/ 2
                    rect.bottom - (Utils.convertDpToPixel(24) - Utils.calcTextHeight(textPaint, str)) / 2,
                    textPaint);
        }
    }
}
