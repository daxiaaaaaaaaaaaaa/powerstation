package com.jilian.powerstation.modul.fragment;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.jilian.powerstation.Constant;
import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.PowerInfoDetailDto;
import com.jilian.powerstation.common.event.AlarmMsg;
import com.jilian.powerstation.common.event.MessageEvent;
import com.jilian.powerstation.dialog.nicedialog.BaseNiceDialog;
import com.jilian.powerstation.dialog.nicedialog.NiceDialog;
import com.jilian.powerstation.dialog.nicedialog.ViewConvertListener;
import com.jilian.powerstation.dialog.nicedialog.ViewHolder;
import com.jilian.powerstation.modul.activity.MainActivity;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.utils.IconUtils;
import com.jilian.powerstation.utils.StatusBarUtil;
import com.jilian.powerstation.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.util.List;

import cn.sharesdk.facebook.Facebook;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.twitter.Twitter;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.basic.Basic;
import interfaces.heweather.com.interfacesmodule.bean.search.Search;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.Forecast;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.ForecastBase;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.NowBase;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;


public class OneFragment extends BaseFragment implements BDLocationListener {

    private UserViewModel userViewModel;

    private MainActivity activity;
    private SmartRefreshLayout srHasData;
    private ImageView ivWether;
    private TextView tvGoodAir;
    private TextView tvPowerValue1;
    private TextView tvPowerValue2;
    private TextView tvPowerValue3;
    private TextView tvPowerValue4;
    private TextView tvPowerValue5;
    private TextView tvPowerValue6;
    private ImageView imgPower1;
    private ImageView imgPower2;
    private ImageView imgPower3;
    private ImageView imgPower4;
    private ImageView imgPower5;
    private ImageView imgPower6;
    private ImageView imgPower7;
    private ImageView imgPower8;
    private ScrollView scrollView;

    private LinearLayout ll01Reverse;
    private TextView tvNumber1Reverse;
    private LinearLayout ll01Positive;
    private TextView tvNumber1;
    private LinearLayout ll02Reverse;
    private TextView tvNumber2Reverse;
    private LinearLayout ll02Positive;
    private TextView tvNumber2;
    private LinearLayout ll03Reverse;
    private TextView tvNumber3Reverse;
    private LinearLayout ll03Positive;
    private TextView tvNumber3;
    private LinearLayout ll04Reverse;
    private TextView tvNumber4Reverse;
    private LinearLayout ll04Positive;
    private TextView tvNumber4;
    private LinearLayout ll05Reverse;
    private TextView tvNumber5Reverse;
    private LinearLayout ll05Positive;
    private TextView tvNumber5;
    private LinearLayout ll06Reverse;
    private TextView tvNumber6Reverse;
    private LinearLayout ll06Positive;
    private TextView tvNumber6;
    private RelativeLayout rl01;
    private RelativeLayout rl02;
    private RelativeLayout rl03;
    private RelativeLayout rl04;
    private RelativeLayout rl05;
    private RelativeLayout rl06;




    @Override
    protected void loadData() {
        StatusBarUtil.setStatusBarMode(getmActivity(), true, R.color.white);
    }


    @Override
    protected void createViewModel() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        activity = (MainActivity) getmActivity();
        rl01 = (RelativeLayout) view.findViewById(R.id.rl_01);
        rl02 = (RelativeLayout) view.findViewById(R.id.rl_02);
        rl03 = (RelativeLayout)view. findViewById(R.id.rl_03);
        rl04 = (RelativeLayout) view.findViewById(R.id.rl_04);
        rl05 = (RelativeLayout) view.findViewById(R.id.rl_05);
        rl06 = (RelativeLayout) view.findViewById(R.id.rl_06);

        ll01Reverse = (LinearLayout) view.findViewById(R.id.ll_01_reverse);
        tvNumber1Reverse = (TextView) view.findViewById(R.id.tv_number_1_reverse);
        ll01Positive = (LinearLayout) view.findViewById(R.id.ll_01_positive);
        tvNumber1 = (TextView) view.findViewById(R.id.tv_number_1);
        ll02Reverse = (LinearLayout) view.findViewById(R.id.ll_02_reverse);
        tvNumber2Reverse = (TextView) view.findViewById(R.id.tv_number_2_reverse);
        ll02Positive = (LinearLayout) view.findViewById(R.id.ll_02_positive);
        tvNumber2 = (TextView) view.findViewById(R.id.tv_number_2);
        ll03Reverse = (LinearLayout) view.findViewById(R.id.ll_03_reverse);
        tvNumber3Reverse = (TextView) view.findViewById(R.id.tv_number_3_reverse);
        ll03Positive = (LinearLayout) view.findViewById(R.id.ll_03_positive);
        tvNumber3 = (TextView) view.findViewById(R.id.tv_number_3);
        ll04Reverse = (LinearLayout) view.findViewById(R.id.ll_04_reverse);
        tvNumber4Reverse = (TextView) view.findViewById(R.id.tv_number_4_reverse);
        ll04Positive = (LinearLayout) view.findViewById(R.id.ll_04_positive);
        tvNumber4 = (TextView) view.findViewById(R.id.tv_number_4);
        ll05Reverse = (LinearLayout) view.findViewById(R.id.ll_05_reverse);
        tvNumber5Reverse = (TextView)view. findViewById(R.id.tv_number_5_reverse);
        ll05Positive = (LinearLayout) view.findViewById(R.id.ll_05_positive);
        tvNumber5 = (TextView) view.findViewById(R.id.tv_number_5);
        ll06Reverse = (LinearLayout) view.findViewById(R.id.ll_06_reverse);
        tvNumber6Reverse = (TextView)view. findViewById(R.id.tv_number_6_reverse);
        ll06Positive = (LinearLayout) view.findViewById(R.id.ll_06_positive);
        tvNumber6 = (TextView) view.findViewById(R.id.tv_number_6);

        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        tvPowerValue1 = (TextView) view.findViewById(R.id.tv_power_value1);
        tvPowerValue2 = (TextView) view.findViewById(R.id.tv_power_value2);
        tvPowerValue3 = (TextView) view.findViewById(R.id.tv_power_value3);
        tvPowerValue4 = (TextView) view.findViewById(R.id.tv_power_value4);
        tvPowerValue5 = (TextView) view.findViewById(R.id.tv_power_value5);
        tvPowerValue6 = (TextView) view.findViewById(R.id.tv_power_value6);

        imgPower1 = view.findViewById(R.id.img_station_7);
        imgPower2 = view.findViewById(R.id.img_station_6);
        imgPower3 = view.findViewById(R.id.img_station_5);
        imgPower4 = view.findViewById(R.id.img_station_8);
        imgPower5 = view.findViewById(R.id.img_station_9);
        imgPower6 = view.findViewById(R.id.img_station_10);
        imgPower7 = view.findViewById(R.id.img_station_3);
        imgPower8 = view.findViewById(R.id.img_station_1);

        srHasData = (SmartRefreshLayout) view.findViewById(R.id.sr_has_data);
        srHasData.setEnableLoadMore(false);
        ivWether = (ImageView) view.findViewById(R.id.iv_wether);
        tvGoodAir = (TextView) view.findViewById(R.id.tv_good_air);
        setrightImageOne(R.drawable.image_right_one, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).showShareDialog(scrollView,null,null,null);
            }
        });

        seNoBackTitle(activity.getData().getProductName(), v -> getActivity().finish());
        srHasData.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getPowerInfo();
            }
        });

        if (EmptyUtils.isNotEmpty(Constant.daily_forecast)) {
            for (int i = 0; i < Constant.daily_forecast.size(); i++) {
                ForecastBase forecastBase = Constant.daily_forecast.get(i);
                String condCodeD = forecastBase.getCond_code_d();
                String condCodeN = forecastBase.getCond_code_n();
                String tmpMin = forecastBase.getTmp_min();
                String tmpMax = forecastBase.getTmp_max();
                if (i == 0) {
                    ivWether.setImageResource(IconUtils.getDayIconDark(condCodeD));
                    // ivTodayNight.setImageResource(IconUtils.getNightIconDark(condCodeN));
                }

            }
        }
        if (EmptyUtils.isNotEmpty(Constant.nowTmp)) {
            Integer inx = Integer.parseInt(Constant.nowTmp);
            tvGoodAir.setText((inx - 3) + "~" + (inx + 3));
        }


    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getmActivity()).onActivityResult(requestCode, resultCode, data);
    }

    private String nowTmp;
    private String location;
    private String language;
    private ImageView ivBack;
    private String condCode;

    private Lang lang;
    private Unit unit;
    private boolean b_01=true,b_02=true,b_03=true,b_04=true,b_05=true,b_06=true;
    @Override
    protected void initData() {
        lang = Lang.ENGLISH;
        unit = Unit.METRIC;
        getPowerInfo();
        startLocationCilent();

    }

    @Override
    protected void initListener() {
        setAnimators(); // 设置动画
      //  setCameraDistance(); // 设置镜头距离


        rl01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll01Reverse.setVisibility(View.VISIBLE);
                if(b_01){
                    flipCard(ll01Positive,ll01Reverse,false);
                }
                else{
                    flipCard(ll01Positive,ll01Reverse,true);
                }
                b_01 =!b_01;
            }
        });




//        rl02.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ll02Reverse.setVisibility(View.VISIBLE);
//                if(b_02){
//                    flipCard(ll02Positive,ll02Reverse,false);
//                }
//                else{
//                    flipCard(ll02Positive,ll02Reverse,true);
//                }
//                b_02 =!b_02;
//            }
//        });





        rl03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll03Reverse.setVisibility(View.VISIBLE);
                if(b_03){
                    flipCard(ll03Positive,ll03Reverse,false);
                }
                else{
                    flipCard(ll03Positive,ll03Reverse,true);
                }
                b_03 =!b_03;
            }
        });



        rl04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll04Reverse.setVisibility(View.VISIBLE);
                if(b_04){
                    flipCard(ll04Positive,ll04Reverse,false);
                }
                else{
                    flipCard(ll04Positive,ll04Reverse,true);
                }
                b_04 =!b_04;
            }
        });




//        rl05.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ll05Reverse.setVisibility(View.VISIBLE);
//                if(b_05){
//                    flipCard(ll05Positive,ll05Reverse,false);
//                }
//                else{
//                    flipCard(ll05Positive,ll05Reverse,true);
//                }
//                b_05 =!b_05;
//            }
//        });
//
//
//        rl06.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ll06Reverse.setVisibility(View.VISIBLE);
//                if(b_06){
//                    flipCard(ll06Positive,ll06Reverse,false);
//                }
//                else{
//                    flipCard(ll06Positive,ll06Reverse,true);
//                }
//                b_06 =!b_06;
//            }
//        });





    }
//    // 改变视角距离, 贴近屏幕
//    private void setCameraDistance() {
//        int distance = 16000;
//        float scale = getResources().getDisplayMetrics().density * distance;
//        ll01Positive.setCameraDistance(scale);
//        ll01Reverse.setCameraDistance(scale);
//    }

    private AnimatorSet mRightOutSet;
    private AnimatorSet mLeftInSet;

    // 设置动画
    private void setAnimators() {
        mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(getmActivity(), R.animator.anim_out);
        mLeftInSet = (AnimatorSet) AnimatorInflater.loadAnimator(getmActivity(), R.animator.anim_in);

        // 设置点击事件
        mRightOutSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                rl01.setClickable(false);
                rl02.setClickable(false);
                rl03.setClickable(false);
                rl04.setClickable(false);
                rl05.setClickable(false);
                rl06.setClickable(false);

            }
        });
        mLeftInSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                rl01.setClickable(true);
                rl02.setClickable(true);
                rl03.setClickable(true);
                rl04.setClickable(true);
                rl05.setClickable(true);
                rl06.setClickable(true);
            }
        });
    }


    // 翻转卡片
    public void flipCard(LinearLayout positive,LinearLayout reverse,boolean mIsShowBack) {

        // 正面朝上
        if (!mIsShowBack) {
            mRightOutSet.setTarget(positive);
            mLeftInSet.setTarget(reverse);

            mRightOutSet.start();
            mLeftInSet.start();

        } else { // 背面朝上
            mRightOutSet.setTarget(reverse);
            mLeftInSet.setTarget(positive);
            mRightOutSet.start();
            mLeftInSet.start();

        }

    }
    /**
     * 获取电站详情
     */
    private void getPowerInfo() {
        userViewModel.getPowerInfo(getActivity().getIntent().getStringExtra("sn"));
        userViewModel.getPowerliveData().observe(this, new Observer<BaseDto<PowerInfoDetailDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<PowerInfoDetailDto> detailDtoBaseDto) {
                srHasData.finishRefresh();
                if (detailDtoBaseDto.isSuccess()) {
                    if (EmptyUtils.isNotEmpty(detailDtoBaseDto)) {
                        initDetailView(detailDtoBaseDto.getData());
                    }
                } else {
                    ToastUitl.showImageToastTips(detailDtoBaseDto.getMsg());
                }
            }
        });

    }

    /**
     * 初始化電站詳情视图
     *
     * @param data
     */
    private void initDetailView(PowerInfoDetailDto data) {
        Log.e(TAG, "initDetailView: " + data.toString());
        tvNumber1.setText(data.getToday_own_consumption_rate());//当日自发自用率
        tvNumber2.setText("0");//设备运行数量(没字段，感觉)
        tvNumber3.setText(data.getToday_pv_production() );//当天发电量
        tvNumber4.setText(data.getToday_consumption());//当天用电
        tvNumber5.setText(data.getHistory_estimated_refund());//当天收益(没字段，感觉)
        tvNumber6.setText(data.getHistory_carbon_offset());//当天二氧化碳(没字段，感觉)

        tvNumber1Reverse.setText(data.getToday_own_consumption());//
        tvNumber3Reverse.setText(data.getHistory_pv_production());//总发电
        tvNumber4Reverse.setText(data.getHistory_consumption());//总用电

        setPowerView(data.getPv2GRID(), tvPowerValue1, imgPower1, R.drawable.image_station_7, R.drawable.image_station_7, R.drawable.image_station_7);
        setPowerView(data.getPv2BAT(), tvPowerValue2, imgPower2, R.drawable.image_station_6, R.drawable.image_station_6, R.drawable.image_station_6);
        setPowerView(data.getBat2GRID(), tvPowerValue3, imgPower3, R.drawable.image_station_7, R.drawable.image_station_7, R.drawable.image_station_5);
        setPowerView(data.getPv2LOAD(), tvPowerValue4, imgPower4, R.drawable.image_station_8, R.drawable.image_station_8, R.drawable.image_station_8);
        setPowerView(data.getBat2LOAD(), tvPowerValue5, imgPower5, R.drawable.image_station_9, R.drawable.image_station_9, R.drawable.image_station_9);
        setPowerView(data.getGrid2LOAD(), tvPowerValue6, imgPower6, R.drawable.image_station_10, R.drawable.image_station_10, R.drawable.image_station_10);
        if (data.getBatteryStatus()==0){
            imgPower7.setImageResource(R.drawable.image_station_3_0);
        }else if (data.getBatteryStatus()==1){
            imgPower7.setImageResource(R.drawable.image_station_3_1);
        }else {
            imgPower7.setImageResource(R.drawable.image_station_3_2);
        }
        if (data.getLoad_a_active_power()<0){
            imgPower8.setImageResource(R.drawable.image_station_1_0);
        }else {
            imgPower8.setImageResource(R.drawable.image_station_1);
        }
    }

    /**
     * @param textView
     * @param imageView
     * @param input     输入
     * @param output    输出
     * @param nopower   没有能量
     */
    public void setPowerView(int value, TextView textView, ImageView imageView, int input, int output, int nopower) {
        if (value == 0) {
            textView.setText("");
            imageView.setImageResource(nopower);
        } else if (value < 0) {
            textView.setText(value + "W");
            imageView.setImageResource(output);
        } else {
            textView.setText(value + "W");
            imageView.setImageResource(nopower);
        }

    }

    private int getIntValue(String value) {
        if (value == null) return 0;
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }

    }



    /**
     * 开启定位
     */
    private void startLocationCilent() {
        // 申请权限
        MyApplication.getInstance().mLocationClient.start();
        MyApplication.getInstance().mLocationClient.registerLocationListener(this);
    }


    private void getNow(String location, final boolean nowCity) {
        HeWeather.getSearch(getmActivity(), location, "en,overseas", 3, lang, new HeWeather.OnResultSearchBeansListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "onError: " + throwable);
            }

            @Override
            public void onSuccess(Search search) {
                Basic basic = search.getBasic().get(0);
                String cid = basic.getCid();
                String location = basic.getLocation();
                Log.e(TAG, "onSuccess: " + cid + ":" + location);
                HeWeather.getWeatherNow(getmActivity(), cid, new HeWeather.OnResultWeatherNowBeanListener() {
                    @Override
                    public void onError(Throwable throwable) {
                        Log.e(TAG, "onError: " + throwable);
                    }

                    @Override
                    public void onSuccess(List<Now> list) {
                        NowBase now = list.get(0).getNow();
                        String rain = now.getPcpn();
                        String hum = now.getHum();
                        String pres = now.getPres();
                        String vis = now.getVis();
                        String windDir = now.getWind_dir();
                        String windSc = now.getWind_sc();
                        String condTxt = now.getCond_txt();
                        Constant.condTxt = condTxt;

                        condCode = now.getCond_code();
                        nowTmp = now.getTmp();
                        Constant.nowTmp = nowTmp;




                        if(EmptyUtils.isNotEmpty(Constant.nowTmp)){
                            Integer inx =  Integer.parseInt(Constant.nowTmp);
                            tvGoodAir.setText((inx-3)+"~"+(inx+3)+"°C");
                        }


                        Log.e(TAG, "onSuccess: " + condTxt);

                        Log.e(TAG, "onSuccess: " + nowTmp + "°");
                        Log.e(TAG, "onSuccess: " + rain + "mm");
                        Log.e(TAG, "onSuccess: " + pres + "HPA");
                        Log.e(TAG, "onSuccess: " + hum + "%");
                        Log.e(TAG, "onSuccess: " + vis + "KM");

                        Log.e(TAG, "onSuccess: " + windDir);

                        Log.e(TAG, "onSuccess: " + windSc + "级");
                    }
                });
            }
        });
    }

    private double currentLat;//当前位置的维度
    private double currentLon;//当前位置的维度

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation != null) {
            currentLat = bdLocation.getLatitude();
            currentLon = bdLocation.getLongitude();

            getNow(currentLon + "," + currentLat, true);

            getWeatherForecast(currentLon + "," + currentLat);
            MyApplication.getInstance().mLocationClient.stop();

        }
    }


    public void getWeatherForecast(final String location) {
        HeWeather.getWeatherForecast(getmActivity(), location, lang, unit, new HeWeather.OnResultWeatherForecastBeanListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.i("sky", "getWeatherForecast onError: ");

            }

            @Override
            public void onSuccess(List<Forecast> list) {

                Forecast bean = list.get(0);


                if (bean != null && bean.getDaily_forecast() != null) {


                    List<ForecastBase> daily_forecast = bean.getDaily_forecast();
                    Constant.daily_forecast = daily_forecast;
                    if (EmptyUtils.isNotEmpty(Constant.daily_forecast)) {
                        for (int i = 0; i < Constant.daily_forecast.size(); i++) {
                            ForecastBase forecastBase = Constant.daily_forecast.get(i);
                            String condCodeD = forecastBase.getCond_code_d();
                            String condCodeN = forecastBase.getCond_code_n();
                            String tmpMin = forecastBase.getTmp_min();
                            String tmpMax = forecastBase.getTmp_max();
                            if (i == 0) {
                                ivWether.setImageResource(IconUtils.getDayIconDark(condCodeD));
                                // ivTodayNight.setImageResource(IconUtils.getNightIconDark(condCodeN));
                            }

                        }
                    }

                }


            }
        });
    }


}
