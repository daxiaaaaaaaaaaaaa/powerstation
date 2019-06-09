package com.jilian.powerstation.views;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

public class CustomerLinearLayoutManager extends LinearLayoutManager {
    private boolean canScrollVertically;

    public boolean isCanScrollVertically() {
        return canScrollVertically;
    }

    public void setCanScrollVertically(boolean canScrollVertically) {
        this.canScrollVertically = canScrollVertically;
    }

    public CustomerLinearLayoutManager(Context context) {
        super(context);
    }

    public CustomerLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CustomerLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean canScrollVertically() {
        return canScrollVertically;
    }
}
