package com.jilian.powerstation.modul.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.jilian.powerstation.Constant;
import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseFragment;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.common.dto.PowerCardDto;
import com.jilian.powerstation.common.dto.PowerDto;
import com.jilian.powerstation.modul.viewmodel.PowerViewModel;
import com.jilian.powerstation.modul.viewmodel.UserViewModel;
import com.jilian.powerstation.utils.DateUtil;
import com.jilian.powerstation.utils.EmptyUtils;
import com.jilian.powerstation.utils.IconUtils;
import com.jilian.powerstation.utils.ToastUitl;

import java.util.Date;
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

/**
 * Created by cxz on 2019/6/2
 * <p>
 * Discrebe: 电站名片
 */
public class SiteCardFragment extends BaseFragment  implements BDLocationListener {
    private TextView tvSiteName;
    private TextView tvDeviceName;
    private TextView tvOne;
    private TextView tvPowerTotal;
    private TextView tvPowerVersion;
    private TextView tvOnlineTime;
    private TextView tvPowerIntroduction;
    private ImageView ivWether;
    private TextView tvGoodAir;
    private TextView tvAdress;






    private PowerDto powerDto;
    private PowerViewModel viewModel;

    @Override
    protected void loadData() {

    }

    @Override
    protected void createViewModel() {
        viewModel = ViewModelProviders.of(this).get(PowerViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_site_card;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tvSiteName = (TextView) view.findViewById(R.id.tv_site_name);
        tvDeviceName = (TextView) view.findViewById(R.id.tv_device_name);
        tvOne = (TextView) view.findViewById(R.id.tv_one);
        tvPowerTotal = (TextView) view.findViewById(R.id.tv_power_total);
        tvPowerVersion = (TextView) view.findViewById(R.id.tv_power_version);
        tvOnlineTime = (TextView) view.findViewById(R.id.tv_online_time);
        tvPowerIntroduction = (TextView) view.findViewById(R.id.tv_power_introduction);
        ivWether = (ImageView)view. findViewById(R.id.iv_wether);
        tvGoodAir = (TextView) view.findViewById(R.id.tv_good_air);
        tvAdress = (TextView)view. findViewById(R.id.tv_adress);
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
        powerDto = (PowerDto) getActivity().getIntent().getSerializableExtra("data");
        if (powerDto != null) {
            tvSiteName.setText(powerDto.getProductName());
            tvDeviceName.setText(powerDto.getProductName());
            tvPowerTotal.setText(powerDto.getHistoryPVproduction());
            getPowerCard();
        }
        startLocationCilent();


    }

    /**
     * 获取电站名片数据
     */
    private void getPowerCard() {
        showLoadingDialog();
        viewModel.getPowerCard(powerDto.getSn());
        viewModel.getPowerCardliveData().observe(this, new Observer<BaseDto<PowerCardDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<PowerCardDto> dtoBaseDto) {
                hideLoadingDialog();
                if (dtoBaseDto.isSuccess()) {
                    if (EmptyUtils.isNotEmpty(dtoBaseDto.getData())) {
                        initDataView(dtoBaseDto.getData());
                    }
                } else {
                    ToastUitl.showImageToastTips(dtoBaseDto.getMsg());
                }
            }
        });

    }

    /**
     * 展示卡片数据
     *
     * @param data
     */
    private void initDataView(PowerCardDto data) {
        tvOne.setText(data.getSpecification());//电站规格
        tvPowerVersion.setText(data.getVersion());//版本
        tvOnlineTime.setText(DateUtil.dateToString("yyyy/MM/dd HH:mm:ss", new Date(data.getTime())));
        tvPowerIntroduction.setText(data.getRemark());//电站简介
        tvAdress.setText(data.getAddress());
    }


    @Override
    protected void initListener() {
        tvPowerVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePowerFirmwareInfo();
            }
        });

    }

    /**
     * 更新固件
     */
    private void updatePowerFirmwareInfo() {
        showLoadingDialog();
        viewModel.updatePowerFirmwareInfo(powerDto.getSn());
        viewModel.getUpdateliveData().observe(this, new Observer<BaseDto<BaseResultDto>>() {
            @Override
            public void onChanged(@Nullable BaseDto<BaseResultDto> resultDtoBaseDto) {
                hideLoadingDialog();
                if (resultDtoBaseDto.isSuccess()) {
                    if ("1".equals(resultDtoBaseDto.getData().getResult())) {
                        getPowerCard();
                        ToastUitl.showImageToastTips("update success");
                    } else {
                        ToastUitl.showImageToastTips("update failuer");
                    }
                } else {
                    ToastUitl.showImageToastTips(resultDtoBaseDto.getMsg());
                }
            }
        });
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

//            getNow(-106.689372 + "," + 52.181005, true);
//
//            getWeatherForecast(-106.689372 + "," + 52.181005);

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
