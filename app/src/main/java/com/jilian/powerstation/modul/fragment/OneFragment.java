package com.jilian.powerstation.modul.fragment;


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
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.jilian.powerstation.Constant;
import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.AlarmInfoDto;
import com.jilian.powerstation.common.dto.PowerInfoDetailDto;
import com.jilian.powerstation.common.event.AlarmMsg;
import com.jilian.powerstation.common.event.MessageEvent;
import com.jilian.powerstation.dialog.nicedialog.BaseNiceDialog;
import com.jilian.powerstation.dialog.nicedialog.NiceDialog;
import com.jilian.powerstation.dialog.nicedialog.ViewConvertListener;
import com.jilian.powerstation.dialog.nicedialog.ViewHolder;
import com.jilian.powerstation.modul.activity.MainActivity;
import com.jilian.powerstation.modul.activity.WarningDetailActivity;
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

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.basic.Basic;
import interfaces.heweather.com.interfacesmodule.bean.search.Search;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.Forecast;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.ForecastBase;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.NowBase;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;


public class OneFragment extends BaseFragment  implements BDLocationListener {

    private UserViewModel userViewModel;
    private TextView tvNumber1;
    private TextView tvNumber2;
    private TextView tvNumber3;
    private TextView tvNumber4;
    private TextView tvNumber5;
    private TextView tvNumber6;
    private MainActivity activity;
    private SmartRefreshLayout srHasData;
    private ImageView ivWether;
    private TextView tvGoodAir;


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
        tvNumber1 = (TextView) view.findViewById(R.id.tv_number_1);
        tvNumber2 = (TextView) view.findViewById(R.id.tv_number_2);
        tvNumber3 = (TextView) view.findViewById(R.id.tv_number_3);
        tvNumber4 = (TextView) view.findViewById(R.id.tv_number_4);
        tvNumber5 = (TextView) view.findViewById(R.id.tv_number_5);
        tvNumber6 = (TextView) view.findViewById(R.id.tv_number_6);
        srHasData = (SmartRefreshLayout) view.findViewById(R.id.sr_has_data);
        srHasData.setEnableLoadMore(false);
        ivWether = (ImageView) view.findViewById(R.id.iv_wether);
        tvGoodAir = (TextView) view.findViewById(R.id.tv_good_air);
        setrightImageOne(R.drawable.image_right_one, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShareDialog();
            }
        });

        setNormalTitle(activity.getData().getProductName(), v -> getActivity().finish());
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
        if(EmptyUtils.isNotEmpty(Constant.nowTmp)){
           Integer inx =  Integer.parseInt(Constant.nowTmp);
           tvGoodAir.setText((inx-3)+"~"+(inx+3));
        }


    }
    /**
     * 选择设置配置类型对话框
     */
    private void showShareDialog() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_share_select)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        dialog.setOutCancel(false);



                        LinearLayout  llOne = (LinearLayout) holder.getView(R.id.ll_one);
                        LinearLayout  llTwo = (LinearLayout) holder.getView(R.id.ll_two);

                        llOne.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                share(1);
                            }
                        });
                        llTwo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                share(2);
                            }
                        });


                    }
                })
                .setShowBottom(true)
                .show(getActivity().getSupportFragmentManager());
    }

    private void share(int type) {
        UMImage image = new UMImage(getmActivity(), R.mipmap.ic_launcher);//分享图标
        final UMWeb web = new UMWeb("http://www.baidu.com/"); //切记切记 这里分享的链接必须是http开头
        web.setTitle("测试分享标题");//标题
        web.setThumb(image);  //缩略图
        web.setDescription("测下分享内容");//描述
        if(type==1){
            new ShareAction(activity).setPlatform(SHARE_MEDIA.FACEBOOK)
                    .withMedia(web)
                    .setCallback(umShareListener)
                    .share();
        }
        if(type==2){
            new ShareAction(activity).setPlatform(SHARE_MEDIA.FACEBOOK)
                    .withMedia(web)
                    .setCallback(umShareListener)
                    .share();
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
    @Override
    protected void initData() {
        lang = Lang.ENGLISH;
        unit = Unit.METRIC;
        getPowerInfo();
        startLocationCilent();

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
        tvNumber1.setText(data.getToday_pv_production());
        tvNumber2.setText(data.getToday_consumption());
        tvNumber3.setText(data.getToday_own_consumption_rate());
        tvNumber4.setText(data.getToday_own_consumption());
        tvNumber5.setText(data.getHistory_estimated_refund());
        tvNumber6.setText(data.getHistory_carbon_offset());
    }

private UMShareListener umShareListener;
    @Override
    protected void initListener() {
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                //分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
           Toast.makeText(getmActivity(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
           Toast.makeText(getmActivity(),platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getmActivity(),platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            }
        };


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
