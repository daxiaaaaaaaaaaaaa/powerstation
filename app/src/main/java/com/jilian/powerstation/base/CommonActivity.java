package com.jilian.powerstation.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jilian.powerstation.Constant;
import com.jilian.powerstation.R;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.dialog.LoadingDialog;
import com.jilian.powerstation.utils.AndroidWorkaround;
import com.jilian.powerstation.utils.SPUtil;
import com.jilian.powerstation.utils.StatusBarUtil;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

/**
 * Activity 基类3
 *
 * @author weishixiong
 * @Time 2018-03-16
 */

public abstract class CommonActivity extends AppCompatActivity {
    /***封装toast对象**/
    private static Toast toast;
    /***获取TAG的activity名称**/
    protected final String TAG = this.getClass().getSimpleName();
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //禁止横屏 竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // StatusBarUtil.setColor(this, getResources().getColor(R.color.color_black), 0);
        initStatus();
        initLoginInterceptor();
        //创建viewModel
        createViewModel();
        //设置布局
        setContentView(intiLayout());
        //初始化控件
        initView();
        //设置数据
        initData();
        //监听器
        initListener();
        // 执行业务逻辑
        doBusiness();
        //解决  华为手机虚拟按键
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));
        }
        //根据状态栏颜色来决定状态栏文字用黑色还是白色
        StatusBarUtil.setStatusBarMode(this, true, R.color.white);

    }
    public LoginDto getLoginDto(){
        return SPUtil.getData(Constant.SP_VALUE.SP,Constant.SP_VALUE.LOGIN_DTO,LoginDto.class,null);
    }
    //地址选择对象
    private CityPickerView mPicker;

    private CityPickerView cityPicker;

    /**
     * 初始化地址选择对象
     *
     * @return
     */
    public CityPickerView getPickerInstance() {
        cityPicker = new CityPickerView();
        /**
         * 预先加载仿iOS滚轮实现的全部数据
         */
        cityPicker.init(this);
        //添加默认的配置，不需要自己定义
        // CityConfig cityConfig = new CityConfig.Builder().build();
        CityConfig cityConfig = new CityConfig.Builder()

                .title("选择服务区域")//标题
                .titleTextSize(16)//标题文字大小
                .titleTextColor("#333333")//标题文字颜  色
                .titleBackgroundColor("#F2F2F2")//标题栏背景色
                .confirTextColor("#65C1C9")//确认按钮文字颜色
                .confirmText("确定")//确认按钮文字
                .confirmTextSize(16)//确认按钮文字大小
                .cancelTextColor("#a5a5a5")//取消按钮文字颜色
                .cancelText("取消")//取消按钮文字
                .cancelTextSize(16)//取消按钮文字大小
                .province("广东省")//默认显示的省份
                .city("深圳市")//默认显示省份下面的城市
                .district("南山区")//默认显示省市下面的区县数据
                .build();

        //设置自定义的属性配置
        cityPicker.setConfig(cityConfig);
        return cityPicker;

    }


    /**
     * 初始化地址选择对象
     *
     * @return
     */
    public CityPickerView getCityPickerInstance() {
        mPicker = new CityPickerView();
        /**
         * 预先加载仿iOS滚轮实现的全部数据
         */
        mPicker.init(this);
        //添加默认的配置，不需要自己定义
        // CityConfig cityConfig = new CityConfig.Builder().build();
        CityConfig cityConfig = new CityConfig.Builder()
                .title("选择城市")//标题
                .titleTextSize(16)//标题文字大小
                .titleTextColor("#222222")//标题文字颜  色
                .titleBackgroundColor("#F2F2F2")//标题栏背景色
                .confirTextColor("#c71233")//确认按钮文字颜色
                .confirmText("确定")//确认按钮文字
                .confirmTextSize(16)//确认按钮文字大小
                .cancelTextColor("#a5a5a5")//取消按钮文字颜色
                .cancelText("取消")//取消按钮文字
                .cancelTextSize(16)//取消按钮文字大小
                .province("广东省")//默认显示的省份
                .city("深圳市")//默认显示省份下面的城市
                .setCityWheelType(CityConfig.WheelType.PRO_CITY)
                .build();

        //设置自定义的属性配置
        mPicker.setConfig(cityConfig);
        return mPicker;

    }
    private void initStatus() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTranslucentStatus | flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }


    }


    /**
     * 登录拦截
     */
    public void initLoginInterceptor() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    /**
     * 返回建
     **/
    public void back(View view) {
        finish();
    }

    /**
     * 创建presenter
     */
    protected abstract void createViewModel();

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int intiLayout();

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();

    /**
     * 初始化监听事件
     */
    public abstract void initListener();

    /**
     * 执行业务逻辑
     */
    public void doBusiness() {

    }

    /**
     * 初始化 ViewModel
     */
    public <T extends ViewModel> T getViewModel(Class<T> clazz) {
        return ViewModelProviders.of(this).get(clazz);
    }

    public void setNormalTitle(String title, View.OnClickListener backListener) {
        setCenterTitle(title, "#FFFFFF");
        setleftImage(R.drawable.image_back, true, backListener);
        setTitleBg(R.color.color_main_select);
    }

    public void setNormalTitle(String title, View.OnClickListener backListener, String rightText, View.OnClickListener rightListener) {
        setCenterTitle(title, "#FFFFFF");
        setleftImage(R.drawable.image_back, true, backListener);
        setTitleBg(R.color.color_main_select);
        setrightTitle(rightText, "#FFFFFF", rightListener);
    }

    public void setNormalTitle(String title, View.OnClickListener backListener, int rightResource, View.OnClickListener rightListener) {
        setCenterTitle(title, "#FFFFFF");
        setleftImage(R.drawable.image_back, true, backListener);
        setTitleBg(R.color.color_main_select);
        setrightImageOne(rightResource, rightListener);
    }

    /**
     * 设置中间标题
     **/
    public void setCenterTitle(String text, String color) {
        TextView textView = (TextView) findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(color)) {
            textView.setTextColor(Color.parseColor(color));
        }
        textView.setText(text);
        textView.setVisibility(View.VISIBLE);


    }

    /**
     * 设置左边标题
     **/
    public void setleftTitle(String text, String color, boolean isfinish, View.OnClickListener listener) {
        TextView textView = (TextView) findViewById(R.id.tv_left_text);
        if (!TextUtils.isEmpty(color)) {
            textView.setTextColor(Color.parseColor(color));
        }
        textView.setText(text);
        textView.setVisibility(View.VISIBLE);
        if (isfinish) {
            findViewById(R.id.v_back).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.v_back).setVisibility(View.GONE);
            textView.setOnClickListener(listener);
        }

    }

    /**
     * 设置右边标题
     **/
    public void setrightTitle(String text, String color, View.OnClickListener listener) {
        TextView textView = (TextView) findViewById(R.id.tv_right_text);
        if (!TextUtils.isEmpty(color)) {
            textView.setTextColor(Color.parseColor(color));
        }
        textView.setText(text);
        textView.setOnClickListener(listener);
        textView.setVisibility(View.VISIBLE);

    }

    /**
     * 设置左边图片
     **/
    public void setleftImage(int resource, boolean isfinish, View.OnClickListener listener) {
        ImageView imageView = (ImageView) findViewById(R.id.iv_left_text);
        imageView.setImageResource(resource);
        imageView.setVisibility(View.VISIBLE);
        if (isfinish) {
            findViewById(R.id.v_back).setVisibility(View.VISIBLE);
            imageView.setOnClickListener(listener);
        } else {
            findViewById(R.id.v_back).setVisibility(View.GONE);

        }

    }

    /**
     * 设置右边图片
     **/
    public void setrightImageOne(int resource, View.OnClickListener listener) {
        ImageView imageView = (ImageView) findViewById(R.id.iv_right_one);
        imageView.setImageResource(resource);
        imageView.setOnClickListener(listener);
        imageView.setVisibility(View.VISIBLE);

    }
    /**
     * 设置右边图片
     **/
    public void setrightImageTwo(int resource, View.OnClickListener listener) {
        ImageView imageView = (ImageView) findViewById(R.id.iv_right_two);
        imageView.setImageResource(resource);
        imageView.setOnClickListener(listener);
        imageView.setVisibility(View.VISIBLE);

    }
    /**
     * 设置背景图片
     **/
    public void setTitleBg(int color) {
        LinearLayout lLtitle = (LinearLayout) findViewById(R.id.ll_title);
        lLtitle.setBackgroundResource(color);

    }

    /**
     * 显示长toast
     *
     * @param msg
     */
    public void toastLong(String msg) {
        if (null == toast) {
            toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setText(msg);
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }
    }

    /**
     * 显示短toast
     *
     * @param msg
     */
    public void toastShort(String msg) {
        if (null == toast) {
            toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText(msg);
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }
    }


    private LoadingDialog loadingDialog;//加载提示框


    public LoadingDialog getLoadingDialog() {
        if (null == loadingDialog) {
            loadingDialog = new LoadingDialog(this);
            //点击空白处Dialog不消失
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        return loadingDialog;
    }

    public void showLoadingDialog() {
        getLoadingDialog().showDialog();
    }

    public void hideLoadingDialog()  {
        getLoadingDialog().dismiss();
    }

}
