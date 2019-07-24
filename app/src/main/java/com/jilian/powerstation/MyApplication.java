package com.jilian.powerstation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;

import com.jilian.powerstation.common.dto.PowerDto;
import com.jilian.powerstation.modul.activity.LoginActivity;
import com.jilian.powerstation.ssl.SslContextFactory;
import com.jilian.powerstation.utils.SPUtil;
import com.jilian.powerstation.utils.ToastUitl;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLSocketFactory;

import cn.jpush.android.api.JPushInterface;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


/**
 * 全局application
 *
 * @author weishixiong
 * @Time 2018-03-16
 */
public class MyApplication extends MultiDexApplication {
    private static final String TAG = "MyApplication";
    private static MyApplication instance;


    private PowerDto powerDto;

    public PowerDto getPowerDto() {
        return powerDto;
    }

    public void setPowerDto(PowerDto powerDto) {
        this.powerDto = powerDto;
    }



    /**
     *
     */
    public LocationClient mLocationClient = null;
    /**
     * 存放所有的activity
     */
    public static List<Activity> runActivities = new ArrayList<>();
    /**
     * 上下文
     */
    private static Context context;

    public static Context getContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        //初始化 bugly 崩溃收集  13129554264
        CrashReport.initCrashReport(getApplicationContext(), "9f7c6550aa", false);

        //
        Logger.addLogAdapter(new AndroidLogAdapter());
        regToWx();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        HeConfig.init("HE1907221513361565", "4636ab5d580b4ef3bac3423ae1e39bbf");
        HeConfig.switchToFreeServerNode();
        initYOUMENG();
        initBaidu();

    }
    private void initYOUMENG() {
        /**
         * 初始化common库
         * 参数1:上下文，必须的参数，不能为空
         * 参数2:友盟 app key，非必须参数，如果Manifest文件中已配置app key，该参数可以传空，则使用Manifest中配置的app key，否则该参数必须传入
         * 参数3:友盟 channel，非必须参数，如果Manifest文件中已配置channel，该参数可以传空，则使用Manifest中配置的channel，否则该参数必须传入，channel命名请详见channel渠道命名规范
         * 参数4:设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机
         * 参数5:Push推送业务的secret，需要集成Push功能时必须传入Push的secret，否则传空
         */
//        UMConfigure.init(this,"5d36b6f4570df33b4d000377","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
//        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
//        //豆瓣RENREN平台目前只能在服务器端配置
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
//        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
//        PlatformConfig.setAlipay("2015111700822536");
//        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
//        PlatformConfig.setPinterest("1439206");
//        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
//        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
//        PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");
//        PlatformConfig.setDropbox("oz8v5apet3arcdy","h7p2pjbzkkxt02a");




    }


    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static final String APP_ID = "wx543f82c7e0c9f306";

    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);

//        //建议动态监听微信启动广播进行注册到微信
//        registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//                // 将该app注册到微信
//                api.registerApp(Constants.APP_ID);
//            }
//        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));

    }

    /**
     * 初始化百度地图
     */
    private void initBaidu() {
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        mLocationClient = new LocationClient(this);     //声明LocationClient类
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(true);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }
    //如果应用屏幕固定了某个方向不旋转的话(比如qq和微信),下面可不写.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    /**
     * 获取https证书
     *
     * @return
     */
    public static SSLSocketFactory getSslSocket() {
        return SslContextFactory.getSSLSocketFactory(getInstance());
    }

    /**
     * 添加一个正在运行的Activity进入容器
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        runActivities.add(activity);
    }

    /**
     * 移除被销毁的Activiiy
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        runActivities.remove(activity);

    }

    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 释放所有正在运行的Activity
     */
    public static void clearAllActivitys() {
        for (int i = 0; i < runActivities.size(); i++) {
            runActivities.get(i).finish();
        }
        runActivities.clear();
    }
    /**
     *除了MainActivity都关闭
     */
    public static void clearAllNoMainActivitys() {
        for (int i = 0; i < runActivities.size(); i++) {
            if(!runActivities.get(i).getClass().getSimpleName().equals("MainActivity")){
                runActivities.get(i).finish();
            }

        }
        removeNoMainActivity();

    }

    /**
     * 从集合中移除
     * 递归遍历
     */
    private static void removeNoMainActivity() {
        for (int i = 0; i <runActivities.size() ; i++) {
            if(!runActivities.get(i).getClass().getSimpleName().equals("MainActivity")){
                runActivities.remove(i);
                removeNoMainActivity();
            }
        }
    }

    /**
     * 释放指定的activity
     *
     * @param exclude 要finish的Activity
     */
    public static void clearSpecifyActivities(Class<Activity> exclude[]) {
        for (int i = 0; i < runActivities.size(); i++) {
            Activity a = runActivities.get(i);
            if (a != null && Arrays.asList(exclude).contains(a.getClass())) {
                a.finish();
                runActivities.remove(a);
            }
        }
    }

    /**
     * 登陆失效 退出登录
     * @param code
     * @param msg
     */
    public  void logout(int code,String msg) {
        out( code, msg);
    }

    /**
     * 锁住方法，防止多个接口返回登录失效
     * @param code
     * @param msg
     */
    private   synchronized  void out(int code, String msg) {
        //判断 session是否存在
        String cookieStr = SPUtil.getData(Constant.SP_VALUE.SP, Constant.SP_VALUE.SESSION_ID, String.class, null);
        //如果session已经被清空。则不往下走。
        if (TextUtils.isEmpty(cookieStr)) {
            return;
        }
        ToastUitl.showImageToastTips(msg);
        setPowerDto(null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //清除session
                SPUtil.clearData(Constant.SP_VALUE.SP);
                //关闭所有的activiyt
                clearAllActivitys();
                //跳到登录界面
                Intent intent = new Intent(getInstance(), LoginActivity.class);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                getInstance().startActivity(intent);
            }
        }, 1500);

    }

}