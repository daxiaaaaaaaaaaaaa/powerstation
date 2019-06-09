package com.jilian.powerstation.views;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

public class CustomerGridLayManager extends GridLayoutManager {
    private boolean canScrollVertically;

    public boolean isCanScrollVertically() {
        return canScrollVertically;
    }

    public void setCanScrollVertically(boolean canScrollVertically) {
        this.canScrollVertically = canScrollVertically;
    }

    public CustomerGridLayManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public CustomerGridLayManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public CustomerGridLayManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollVertically() {
        return canScrollVertically;
    }
}
