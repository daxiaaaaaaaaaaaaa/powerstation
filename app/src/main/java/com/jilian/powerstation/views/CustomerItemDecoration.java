package com.jilian.powerstation.views;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 自定义 recyclerView 间距
 */
public class CustomerItemDecoration extends RecyclerView.ItemDecoration {
    private int height;

    public CustomerItemDecoration(int height) {
        this.height = height;
    }

    /**
     * @param outRect 边界
     * @param view    recyclerView ItemView
     * @param parent  recyclerView
     * @param state   recycler 内部数据管理
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //设定底部边距为1px
        outRect.set(0, 0, 0, height);
    }
}

