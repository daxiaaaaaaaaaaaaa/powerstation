package com.jilian.powerstation.modul.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.modul.activity.MainActivity;
import com.jilian.powerstation.modul.adapter.CinemaTabAdapter;
import com.jilian.powerstation.utils.StatusBarUtil;
import com.jilian.powerstation.views.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;


public class FourFragment extends BaseFragment {

    TabLayout tablayout;
    NoScrollViewPager viewPager;

    public NoScrollViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(NoScrollViewPager viewPager) {
        this.viewPager = viewPager;
    }
    List<Fragment> mlist;
    FragmentPagerAdapter adapter;
    ArrayList<String> mTitle = new ArrayList<>();

    @Override
    protected void loadData() {
        StatusBarUtil.setStatusBarMode(getmActivity(), true, R.color.white);
    }


    @Override
    protected void createViewModel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_four;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        tablayout = view.findViewById(R.id.four_tablayout);
        viewPager = view.findViewById(R.id.four_viewPager);
        initTab();
        initViewpage();
        seNoBackTitle(getActivity().getIntent().getStringExtra("name"), v -> getActivity().finish());
        setrightImageOne(R.drawable.image_right_one, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem()==0){
                    ((MainActivity) getmActivity()).showShareDialog(null, null,inverterFragment.getRvList(),null );
                }
                if(viewPager.getCurrentItem()==1){
                    ((MainActivity) getmActivity()).showShareDialog(null, null,batteryFragment.getRvList() ,null);
                }

                if(viewPager.getCurrentItem()==2){
                    ((MainActivity) getmActivity()).showShareDialog(null, getmActivity(),null ,null);
                }
                if(viewPager.getCurrentItem()==3){
                    ((MainActivity) getmActivity()).showShareDialog(null, getmActivity(),null ,null);
                }
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    protected void initData() {
        StatusBarUtil.setStatusBarMode(getmActivity(), true, R.color.white);
    }

    @Override
    protected void initListener() {

    }
    private InverterFragment inverterFragment;
    private BatteryFragment batteryFragment;
    public void initViewpage() {
        inverterFragment = new InverterFragment();
        batteryFragment = new BatteryFragment();
        viewPager.setOffscreenPageLimit(4);
        mlist = new ArrayList<>();
        mlist.add(inverterFragment);
        mlist.add(batteryFragment);
        mlist.add(new SmartFragment());
        mlist.add(new WarningFragment());

        adapter = new CinemaTabAdapter(getFragmentManager(), mlist, mTitle);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        viewPager.setAdapter(adapter);

    }

    public void initTab() {
        mTitle = new ArrayList<>();
        mTitle.add("Inverter information");
        mTitle.add("Battery information");
        mTitle.add("Smart device information");
        mTitle.add("Warning information");

        tablayout.addTab(tablayout.newTab().setText(mTitle.get(0)));
        tablayout.addTab(tablayout.newTab().setText(mTitle.get(1)));
        tablayout.addTab(tablayout.newTab().setText(mTitle.get(2)));
        tablayout.addTab(tablayout.newTab().setText(mTitle.get(3)));

        tablayout.setTabMode(TabLayout.GRAVITY_FILL);
        tablayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
    }

}
