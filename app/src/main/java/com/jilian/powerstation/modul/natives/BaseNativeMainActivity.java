package com.jilian.powerstation.modul.natives;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.CommonViewPagerAdapter;
import com.jilian.powerstation.views.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseNativeMainActivity extends BaseActivity {
    private List<Fragment> mFragmentList;
    private OneNativeFragment oneFragment;
    private TwoNativeFragment twoFragment;


    private CommonViewPagerAdapter mainTapPagerAdapter;
    public NoScrollViewPager viewPager;
    private RelativeLayout rlOne;
    private ImageView ivOne;
    private TextView tvOne;
    private RelativeLayout rlTwo;
    private ImageView ivTwo;
    private TextView tvTwo;

    public LinearLayout llBottom;

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
        return R.layout.activity_native_main;
    }

    @Override
    public void initView() {
        viewPager = (NoScrollViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);
        rlOne = (RelativeLayout) findViewById(R.id.rl_one);
        ivOne = (ImageView) findViewById(R.id.iv_one);
        tvOne = (TextView) findViewById(R.id.tv_one);
        rlTwo = (RelativeLayout) findViewById(R.id.rl_two);
        ivTwo = (ImageView) findViewById(R.id.iv_two);
        tvTwo = (TextView) findViewById(R.id.tv_two);
        llBottom = (LinearLayout) findViewById(R.id.ll_bottom);
    }

    @Override
    public void initData() {

        mFragmentList = new ArrayList<>();
        oneFragment = new OneNativeFragment();
        twoFragment = new TwoNativeFragment();

        mFragmentList.add(oneFragment);
        mFragmentList.add(twoFragment);


        mainTapPagerAdapter = new CommonViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        viewPager.setAdapter(mainTapPagerAdapter);
        init();

    }

    protected abstract void init();


    @Override
    public void initListener() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        ivOne.setImageResource(R.drawable.image_native_one_select);
                        tvOne.setTextColor(Color.parseColor("#D24F60"));

                        ivTwo.setImageResource(R.drawable.image_two);
                        tvTwo.setTextColor(Color.parseColor("#222222"));


                        break;
                    case 1:

                        ivOne.setImageResource(R.drawable.image_native_one);
                        tvOne.setTextColor(Color.parseColor("#222222"));
                        ivTwo.setImageResource(R.drawable.image_native_two_select);
                        tvTwo.setTextColor(Color.parseColor("#D24F60"));


                        break;


                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rlOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
        rlTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });

    }



}
