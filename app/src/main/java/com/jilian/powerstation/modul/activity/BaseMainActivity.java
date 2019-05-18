package com.jilian.powerstation.modul.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseActivity;
import com.jilian.powerstation.base.CommonActivity;
import com.jilian.powerstation.utils.ToastUitl;
import com.jilian.powerstation.views.NoScrollViewPager;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BaseMainActivity extends BaseActivity {
    private List<Fragment> mFragmentList;
    //    private OneFragment oneFragment;
//    private TwoFragment twoFragment;
//    private ThreeFragment threeFragment;
//    private FourFragment fourFragment;
//    private CommonViewPagerAdapter mainTapPagerAdapter;
    private NoScrollViewPager viewPager;
    private RelativeLayout rlOne;
    private ImageView ivOne;
    private RelativeLayout rlTwo;
    private ImageView ivTwo;
    private RelativeLayout rlThree;
    private ImageView ivThree;
    private RelativeLayout rlFour;
    private ImageView ivFour;

    @Override
    protected void createViewModel() {

    }

    @Override
    public int intiLayout() {
        return R.layout.activity_base_main;
    }

    @Override
    public void initView() {
//        viewPager = (NoScrollViewPager) findViewById(R.id.viewPager);
//        viewPager.setOffscreenPageLimit(4);
//        rlOne = (RelativeLayout) findViewById(R.id.rl_one);
//        ivOne = (ImageView) findViewById(R.id.iv_one);
//        rlTwo = (RelativeLayout) findViewById(R.id.rl_two);
//        ivTwo = (ImageView) findViewById(R.id.iv_two);
//        rlThree = (RelativeLayout) findViewById(R.id.rl_three);
//        ivThree = (ImageView) findViewById(R.id.iv_three);
//        rlFour = (RelativeLayout) findViewById(R.id.rl_four);
//        ivFour = (ImageView) findViewById(R.id.iv_four);

    }

    @Override
    public void initData() {
//        MyApplication.clearSpecifyActivities(new Class[]{WelcomeActivity.class,LoginActivity.class});
//        MyApplication.clearAllActivitys();
//        mFragmentList = new ArrayList<>();
//        oneFragment = new OneFragment();
//        twoFragment = new TwoFragment();
//        threeFragment = new ThreeFragment();
//        fourFragment = new FourFragment();
//        mFragmentList.add(oneFragment);
//        mFragmentList.add(twoFragment);
//        mFragmentList.add(threeFragment);
//        mFragmentList.add(fourFragment);
//        mainTapPagerAdapter = new CommonViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
//        viewPager.setAdapter(mainTapPagerAdapter);


    }



    @Override
    public void initListener() {
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                switch (position) {
//                    case 0:
//                        ivOne.setImageResource(R.drawable.image_one_selected);
//                        ivTwo.setImageResource(R.drawable.image_two_normal);
//                        ivThree.setImageResource(R.drawable.image_three_normal);
//                        ivFour.setImageResource(R.drawable.image_four_normal);
//                        break;
//                    case 1:
//                        ivOne.setImageResource(R.drawable.image_one_normal);
//                        ivTwo.setImageResource(R.drawable.image_two_selected);
//                        ivThree.setImageResource(R.drawable.image_three_normal);
//                        ivFour.setImageResource(R.drawable.image_four_normal);
//                        break;
//                    case 2:
//                        ivOne.setImageResource(R.drawable.image_one_normal);
//                        ivTwo.setImageResource(R.drawable.image_two_normal);
//                        ivThree.setImageResource(R.drawable.image_three_selected);
//                        ivFour.setImageResource(R.drawable.image_four_normal);
//                        break;
//                    case 3:
//                        ivOne.setImageResource(R.drawable.image_one_normal);
//                        ivTwo.setImageResource(R.drawable.image_two_normal);
//                        ivThree.setImageResource(R.drawable.image_three_normal);
//                        ivFour.setImageResource(R.drawable.image_four_selected);
//                        break;
//
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        rlOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewPager.setCurrentItem(0);
//            }
//        });
//        rlTwo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewPager.setCurrentItem(1);
//            }
//        });
//        rlThree.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewPager.setCurrentItem(2);
//            }
//        });
//        rlFour.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                viewPager.setCurrentItem(3);
//            }
//        });

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
