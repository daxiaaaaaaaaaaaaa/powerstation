package com.jilian.powerstation.modul.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.modul.adapter.CinemaTabAdapter;
import com.jilian.powerstation.modul.fragment.OneFragment;
import com.jilian.powerstation.modul.fragment.ThreeFragment;
import com.jilian.powerstation.modul.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 智能设备详情
 */
public class IntelligentDeviceActivity extends BaseActivity {

    TabLayout tablayout;
    ViewPager viewPager;
    List<Fragment> mlist;
    FragmentPagerAdapter adapter;
    ArrayList<String> mTitle = new ArrayList<>();

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
        tablayout = findViewById(R.id.device_tablayout);
        viewPager = findViewById(R.id.device_viewpage);
        initViewpage();
        initTab();
    }

    public void initViewpage() {
        mlist.add(new OneFragment());
        mlist.add(new TwoFragment());
        mlist.add(new ThreeFragment());

        adapter = new CinemaTabAdapter(getSupportFragmentManager(), mlist, mTitle);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        viewPager.setAdapter(adapter);
    }

    public void initTab() {
        mTitle.add("1111");
        mTitle.add("2222");
        mTitle.add("3333");
        mTitle.add("4444");

        tablayout.addTab(tablayout.newTab().setText(mTitle.get(0)));
        tablayout.addTab(tablayout.newTab().setText(mTitle.get(1)));
        tablayout.addTab(tablayout.newTab().setText(mTitle.get(2)));
        tablayout.addTab(tablayout.newTab().setText(mTitle.get(3)));

        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

}
