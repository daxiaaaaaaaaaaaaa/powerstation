package com.jilian.powerstation.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.jilian.powerstation.R;

public class RoundViewPager extends ViewPager {
    int radius;
    float width,height;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public RoundViewPager(@NonNull Context context) {
        super(context);
    }

    public RoundViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        radius=array.getInt(R.styleable.RoundImageView_radius,0);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (width > radius && height > radius) {
            Path path = new Path();
            path.moveTo(radius, 0);
            path.lineTo(width - radius, 0);
            path.quadTo(width, 0, width, radius);
            path.lineTo(width, height - radius);
            path.quadTo(width, height, width - radius, height);
            path.lineTo(radius, height);
            path.quadTo(0, height, 0, height - radius);
            path.lineTo(0, radius);
            path.quadTo(0, 0, radius, 0);
            canvas.clipPath(path);
        }

        super.onDraw(canvas);
    }

}
