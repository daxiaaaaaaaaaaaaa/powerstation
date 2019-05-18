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
    public static void logout(int code,String msg) {
        out( code, msg);
    }

    /**
     * 锁住方法，防止多个接口返回登录失效
     * @param code
     * @param msg
     */
    private static  synchronized  void out(int code, String msg) {
        //判断 session是否存在
        String cookieStr = SPUtil.getData(Constant.SP_VALUE.SP, Constant.SP_VALUE.SESSION_ID, String.class, null);
        //如果session已经被清空。则不往下走。
        if (TextUtils.isEmpty(cookieStr)) {
            return;
        }
        ToastUitl.showImageToastTips(msg);
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