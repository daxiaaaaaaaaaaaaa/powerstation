package com.jilian.powerstation.modul.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.modul.adapter.PageAdapter;

import java.util.ArrayList;

/**
 * 告警信息界面
 *
 */
public class WarningFragment extends BaseFragment {

    private RadioGroup radioGroup;
    private ViewPager viewPager;
    private InverterWarningFragment inverterWarningFragment;
    private BatteryWarningFragment batteryWarningFragment;
    private SmartWarningFragment smartWarningFragment;

    FragmentPagerAdapter adapter;
    ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void loadData() {

    }

    @Override
    protected void createViewModel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_warning;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.warning_viewpage);
        radioGroup = view.findViewById(R.id.rg_date);
        initViewpage();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_view1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_view2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_view3:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    public void initViewpage() {
        viewPager.setOffscreenPageLimit(3);
        inverterWarningFragment = new InverterWarningFragment();
        batteryWarningFragment = new BatteryWarningFragment();
        smartWarningFragment = new SmartWarningFragment();
        fragments.add(inverterWarningFragment);
        fragments.add(batteryWarningFragment);
        fragments.add(smartWarningFragment);
        adapter = new PageAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }
}
