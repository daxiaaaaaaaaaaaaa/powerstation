package com.jilian.powerstation.modul.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.modul.adapter.PageAdapter;
import com.jilian.powerstation.modul.fragment.OneFragment;
import com.jilian.powerstation.modul.fragment.ThreeFragment;
import com.jilian.powerstation.modul.fragment.TwoFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class IntelligentDeviceActivity extends BaseActivity {

    TabLayout tablayout;
    ViewPager viewPager;
    List<Fragment> mlist;
    PageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.removeActivity(this);
    }
    @Override
    protected void createViewModel() {

    }

    @Override
    public int intiLayout() {
        return R.layout.activity_intelligent_device;
    }

    @Override
    public void initView() {
        tablayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpage);
        mlist = new ArrayList<>();
        mlist.add(new OneFragment());
        mlist.add(new TwoFragment());
        mlist.add(new ThreeFragment());
        adapter = new PageAdapter(getSupportFragmentManager(),mlist);
        //设置TabLayout的模式
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.addTab(tablayout.newTab().setText("00122"));
        tablayout.addTab(tablayout.newTab().setText("00122"));
        tablayout.addTab(tablayout.newTab().setText("00122"));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        //viewpager加载adapter
        viewPager.setAdapter(adapter);
        //TabLayout加载viewpager
        tablayout.setupWithViewPager(viewPager);
        setTabWidth(tablayout,10);
    }
    @Override
    public void initData() {

    }
    @Override
    public void initListener() {

    }

    public  void setTabWidth(final TabLayout tabLayout, final int padding){
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);



                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距 注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = padding;
                        params.rightMargin = padding;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
