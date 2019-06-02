package com.jilian.powerstation.base;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.CommonViewPagerAdapter;
import com.jilian.powerstation.modul.fragment.FiveFragment;
import com.jilian.powerstation.modul.fragment.FourFragment;
import com.jilian.powerstation.modul.fragment.OneFragment;
import com.jilian.powerstation.modul.fragment.ThreeFragment;
import com.jilian.powerstation.modul.fragment.TwoFragment;
import com.jilian.powerstation.utils.ToastUitl;
import com.jilian.powerstation.views.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

    }


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
                        ivOne.setImageResource(R.drawable.image_home_selected);
                        tvOne.setTextColor(Color.parseColor("#c71233"));

                        ivTwo.setImageResource(R.drawable.image_type_unselect);
                        tvTwo.setTextColor(Color.parseColor("#999999"));

                        ivThree.setImageResource(R.drawable.image_circle_unselect);
                        tvThree.setTextColor(Color.parseColor("#999999"));

                        ivFour.setImageResource(R.drawable.image_shopping_unselect);
                        tvFour.setTextColor(Color.parseColor("#999999"));

                        ivFive.setImageResource(R.drawable.image_my_unselect);
                        tvFive.setTextColor(Color.parseColor("#999999"));
                        break;
                    case 1:

                        ivOne.setImageResource(R.drawable.image_home_unselecte);
                        tvOne.setTextColor(Color.parseColor("#999999"));

                        ivTwo.setImageResource(R.drawable.image_type_select);
                        tvTwo.setTextColor(Color.parseColor("#c71233"));

                        ivThree.setImageResource(R.drawable.image_circle_unselect);
                        tvThree.setTextColor(Color.parseColor("#999999"));

                        ivFour.setImageResource(R.drawable.image_shopping_unselect);
                        tvFour.setTextColor(Color.parseColor("#999999"));

                        ivFive.setImageResource(R.drawable.image_my_unselect);
                        tvFive.setTextColor(Color.parseColor("#999999"));

                        break;
                    case 2:

                        ivOne.setImageResource(R.drawable.image_home_unselecte);
                        tvOne.setTextColor(Color.parseColor("#999999"));

                        ivTwo.setImageResource(R.drawable.image_type_unselect);
                        tvTwo.setTextColor(Color.parseColor("#999999"));

                        ivThree.setImageResource(R.drawable.image_circle_select);
                        tvThree.setTextColor(Color.parseColor("#c71233"));

                        ivFour.setImageResource(R.drawable.image_shopping_unselect);
                        tvFour.setTextColor(Color.parseColor("#999999"));

                        ivFive.setImageResource(R.drawable.image_my_unselect);
                        tvFive.setTextColor(Color.parseColor("#999999"));

                        break;
                    case 3:
                        ivOne.setImageResource(R.drawable.image_home_unselecte);
                        tvOne.setTextColor(Color.parseColor("#999999"));

                        ivTwo.setImageResource(R.drawable.image_type_unselect);
                        tvTwo.setTextColor(Color.parseColor("#999999"));

                        ivThree.setImageResource(R.drawable.image_circle_unselect);
                        tvThree.setTextColor(Color.parseColor("#999999"));

                        ivFour.setImageResource(R.drawable.image_shopping_select);
                        tvFour.setTextColor(Color.parseColor("#c71233"));

                        ivFive.setImageResource(R.drawable.image_my_unselect);
                        tvFive.setTextColor(Color.parseColor("#999999"));
                        break;
                    case 4:
                        ivOne.setImageResource(R.drawable.image_home_unselecte);
                        tvOne.setTextColor(Color.parseColor("#999999"));

                        ivTwo.setImageResource(R.drawable.image_type_unselect);
                        tvTwo.setTextColor(Color.parseColor("#999999"));

                        ivThree.setImageResource(R.drawable.image_circle_unselect);
                        tvThree.setTextColor(Color.parseColor("#999999"));

                        ivFour.setImageResource(R.drawable.image_shopping_unselect);
                        tvFour.setTextColor(Color.parseColor("#999999"));

                        ivFive.setImageResource(R.drawable.image_my_select);
                        tvFive.setTextColor(Color.parseColor("#c71233"));
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

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
            return false;
        }
        return true;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    /**
     * 退出应用程序
     */
    private void exitBy2Click() {
        Timer tExit = null;
        if (!isExit) {
            isExit = true; // 准备退出
            ToastUitl.showImageToastTips("再按一次退出程序");
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            System.exit(0);
        }
    }

}
