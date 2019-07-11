package com.jilian.powerstation.modul.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.event.MessageEvent;
import com.jilian.powerstation.modul.adapter.CinemaTabAdapter;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.utils.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class FourFragment extends BaseFragment {

    TabLayout tablayout;
    ViewPager viewPager;

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
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
        setNormalTitle("Site Name", v -> getActivity().finish());
        setrightImageOne(R.drawable.image_right_one, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        setrightImageTwo(R.drawable.image_right_two, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    public void initViewpage() {
        viewPager.setOffscreenPageLimit(4);
        mlist = new ArrayList<>();
        mlist.add(new InverterFragment());
        mlist.add(new BatteryFragment());
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
