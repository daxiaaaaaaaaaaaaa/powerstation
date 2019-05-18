package com.jilian.powerstation.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.jilian.powerstation.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerIndicator implements ViewPager.OnPageChangeListener {

    private int size;
        private int iv_selected = R.drawable.image_bananer_selected, iv_no_selected = R.drawable.image_bananer_normal;
    private List<ImageView> dotViewLists = new ArrayList<>();

    public ViewPagerIndicator(Context context, ViewPager viewPager, LinearLayout dotLayout, int size) {
        dotLayout.removeAllViews();
        if (size < 2) {
            return;
        }
        this.size = size;
        for (int i = 0; i < size; i++) {
            ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.layout_banner, null).findViewById(R.id.iv_indicator);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //为小圆点左右添加间距
            params.leftMargin = 10;
            params.rightMargin = 10;
            //手动给小圆点一个大小
//            params.height = 8;
//            params.width = 50;
            if (i == 0) {
                imageView.setBackgroundResource(iv_selected);
            } else {
                imageView.setBackgroundResource(iv_no_selected);
            }
            //为LinearLayout添加ImageView
            dotLayout.addView(imageView, params);
            dotViewLists.add(imageView);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < size; i++) {
            //选中的页面改变小圆点为选中状态，反之为未选中
            if ((position % size) == i) {
                (dotViewLists.get(i)).setBackgroundResource(iv_selected);
            } else {
                (dotViewLists.get(i)).setBackgroundResource(iv_no_selected);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}