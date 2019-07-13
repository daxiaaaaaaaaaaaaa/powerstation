package com.jilian.powerstation.base;

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
import com.jilian.powerstation.common.event.MessageEvent;
import com.jilian.powerstation.modul.fragment.FiveFragment;
import com.jilian.powerstation.modul.fragment.FourFragment;
import com.jilian.powerstation.modul.fragment.OneFragment;
import com.jilian.powerstation.modul.fragment.ThreeFragment;
import com.jilian.powerstation.modul.fragment.TwoFragment;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.views.NoScrollViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMainActivity extends BaseActivity {
    private List<Fragment> mFragmentList;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;
    private FiveFragment fiveFragment;
    private CommonViewPagerAdapter mainTapPagerAdapter;
    public NoScrollViewPager viewPager;
    private RelativeLayout rlOne;
    private ImageView ivOne;
    private TextView tvOne;
    private RelativeLayout rlTwo;
    private ImageView ivTwo;
    private TextView tvTwo;
    private RelativeLayout rlThree;
    private ImageView ivThree;
    private TextView tvThree;
    private RelativeLayout rlFour;
    private ImageView ivFour;
    private TextView tvFour;
    private RelativeLayout rlFive;
    private ImageView ivFive;
    private TextView tvFive;
    public LinearLayout llBottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        MyApplication.addActivity(this);
    }

    /**
     * //监听外来是否要去成功的界面
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        /* Do something */
        if (EmptyUtils.isNotEmpty(event)
                && EmptyUtils.isNotEmpty(event.getAlarmMsg())
                && event.getAlarmMsg().getCode() == 200
        ) {
            viewPager.setCurrentItem(3);
            fourFragment.getViewPager().setCurrentItem(3);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        MyApplication.removeActivity(this);
    }

    @Override
    protected void createViewModel() {

    }

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
//        MyApplication.clearSpecifyActivities(new Class[]{WelcomeActivity.class});
        viewPager = (NoScrollViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(5);
//        viewPager.setScanScroll(false);
        rlOne = (RelativeLayout) findViewById(R.id.rl_one);
        ivOne = (ImageView) findViewById(R.id.iv_one);
        tvOne = (TextView) findViewById(R.id.tv_one);
        rlTwo = (RelativeLayout) findViewById(R.id.rl_two);
        ivTwo = (ImageView) findViewById(R.id.iv_two);
        tvTwo = (TextView) findViewById(R.id.tv_two);
        rlThree = (RelativeLayout) findViewById(R.id.rl_three);
        ivThree = (ImageView) findViewById(R.id.iv_three);
        tvThree = (TextView) findViewById(R.id.tv_three);
        rlFour = (RelativeLayout) findViewById(R.id.rl_four);
        ivFour = (ImageView) findViewById(R.id.iv_four);
        tvFour = (TextView) findViewById(R.id.tv_four);
        rlFive = (RelativeLayout) findViewById(R.id.rl_five);
        ivFive = (ImageView) findViewById(R.id.iv_five);
        tvFive = (TextView) findViewById(R.id.tv_five);
        llBottom = (LinearLayout) findViewById(R.id.ll_bottom);
    }

    @Override
    public void initData() {

        mFragmentList = new ArrayList<>();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fourFragment = new FourFragment();
        fiveFragment = new FiveFragment();
        mFragmentList.add(oneFragment);
        mFragmentList.add(twoFragment);
        mFragmentList.add(threeFragment);
        mFragmentList.add(fourFragment);
        mFragmentList.add(fiveFragment);

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
                        ivOne.setImageResource(R.drawable.image_tab_one);
                        tvOne.setTextColor(Color.parseColor("#D24F60"));

                        ivTwo.setImageResource(R.drawable.image_tab_two_un);
                        tvTwo.setTextColor(Color.parseColor("#222222"));

                        ivThree.setImageResource(R.drawable.image_tab_three_un);
                        tvThree.setTextColor(Color.parseColor("#222222"));

                        ivFour.setImageResource(R.drawable.image_tab_four_un);
                        tvFour.setTextColor(Color.parseColor("#222222"));

                        ivFive.setImageResource(R.drawable.image_tab_five_un);
                        tvFive.setTextColor(Color.parseColor("#222222"));
                        break;
                    case 1:


                        ivOne.setImageResource(R.drawable.image_tab_one_un);
                        tvOne.setTextColor(Color.parseColor("#222222"));

                        ivTwo.setImageResource(R.drawable.image_tab_two);
                        tvTwo.setTextColor(Color.parseColor("#D24F60"));

                        ivThree.setImageResource(R.drawable.image_tab_three_un);
                        tvThree.setTextColor(Color.parseColor("#222222"));

                        ivFour.setImageResource(R.drawable.image_tab_four_un);
                        tvFour.setTextColor(Color.parseColor("#222222"));

                        ivFive.setImageResource(R.drawable.image_tab_five_un);
                        tvFive.setTextColor(Color.parseColor("#222222"));


                        break;
                    case 2:

                        ivOne.setImageResource(R.drawable.image_tab_one_un);
                        tvOne.setTextColor(Color.parseColor("#222222"));

                        ivTwo.setImageResource(R.drawable.image_tab_two_un);
                        tvTwo.setTextColor(Color.parseColor("#222222"));

                        ivThree.setImageResource(R.drawable.image_tab_three);
                        tvThree.setTextColor(Color.parseColor("#D24F60"));

                        ivFour.setImageResource(R.drawable.image_tab_four_un);
                        tvFour.setTextColor(Color.parseColor("#222222"));

                        ivFive.setImageResource(R.drawable.image_tab_five_un);
                        tvFive.setTextColor(Color.parseColor("#222222"));

                        break;
                    case 3:
                        ivOne.setImageResource(R.drawable.image_tab_one_un);
                        tvOne.setTextColor(Color.parseColor("#222222"));

                        ivTwo.setImageResource(R.drawable.image_tab_two_un);
                        tvTwo.setTextColor(Color.parseColor("#222222"));

                        ivThree.setImageResource(R.drawable.image_tab_three);
                        tvThree.setTextColor(Color.parseColor("#222222"));

                        ivFour.setImageResource(R.drawable.image_tab_four);
                        tvFour.setTextColor(Color.parseColor("#D24F60"));

                        ivFive.setImageResource(R.drawable.image_tab_five_un);
                        tvFive.setTextColor(Color.parseColor("#222222"));
                        break;
                    case 4:
                        ivOne.setImageResource(R.drawable.image_tab_one_un);
                        tvOne.setTextColor(Color.parseColor("#222222"));

                        ivTwo.setImageResource(R.drawable.image_tab_two_un);
                        tvTwo.setTextColor(Color.parseColor("#222222"));

                        ivThree.setImageResource(R.drawable.image_tab_three);
                        tvThree.setTextColor(Color.parseColor("#222222"));

                        ivFour.setImageResource(R.drawable.image_tab_four);
                        tvFour.setTextColor(Color.parseColor("#222222"));

                        ivFive.setImageResource(R.drawable.image_tab_five);
                        tvFive.setTextColor(Color.parseColor("#D24F60"));
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
        rlThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });
        rlFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem(3);
            }
        });
        rlFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem(4);
            }
        });
    }



}
