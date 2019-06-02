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


public class TwoFragment extends BaseFragment  {



    private ViewPager viewPager;
    private RadioGroup radioGroup;

    SiteOneFargement oneFargement;
    SiteTwoFargement twoFargement;
    SiteThreeFargement threeFargement;
    SiteFourFargement fourFargement;

    ArrayList<String> mTitle = new ArrayList<>();

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
        return R.layout.fragment_two;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.vp_two);
        radioGroup = view.findViewById(R.id.rg_date);
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
                    case R.id.rb_view4:
                        viewPager.setCurrentItem(3);
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
        oneFargement = new SiteOneFargement();
        twoFargement = new SiteTwoFargement();
        threeFargement = new SiteThreeFargement();
        fourFargement = new SiteFourFargement();
        fragments.add(oneFargement);
        fragments.add(twoFargement);
        fragments.add(threeFargement);
        fragments.add(fourFargement);
        adapter = new PageAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }



}
