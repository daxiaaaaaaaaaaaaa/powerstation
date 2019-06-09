package com.jilian.powerstation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class CustomerScrollView extends ScrollView {
    public CustomerScrollView(Context context) {
        super(context);
    }

    public CustomerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
