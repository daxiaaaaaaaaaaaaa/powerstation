package com.jilian.powerstation.modul.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.modul.adapter.CinemaTabAdapter;
import com.jilian.powerstation.modul.adapter.PageAdapter;

import java.util.ArrayList;
import java.util.List;


public class ThreeFragment extends BaseFragment {

    ViewPager viewPager;
    RadioGroup radioGroup;


    ConnectedFragment connectedFragment;
    SiteSettingFragment siteSettingFragment;
    SiteCardFragment siteCardFragment;

    FragmentPagerAdapter adapter;
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> mTitle = new ArrayList<>();

    @Override
    protected void loadData() {

    }


    @Override
    protected void createViewModel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_three;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.vp_three);
        radioGroup = view.findViewById(R.id.rg_three_title);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_three_view1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_three_view2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_three_view3:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        initViewpage();
    }

    @Override
    protected void initData() {
    }

    public void initViewpage() {
        connectedFragment = new ConnectedFragment();
        siteSettingFragment = new SiteSettingFragment();
        siteCardFragment = new SiteCardFragment();

        fragments.add(connectedFragment);
        fragments.add(siteSettingFragment);
        fragments.add(siteCardFragment);

        adapter = new PageAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }


}
