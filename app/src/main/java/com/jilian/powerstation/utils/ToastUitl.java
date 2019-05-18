package com.jilian.powerstation.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jilian.powerstation.MyApplication;
import com.jilian.powerstation.R;


/**
 * @author ningpan
 * @since 2018/10/18 19:07 <br>
 * description: Toast 统一管理类
 */
public class ToastUitl {

    /**
     * 普通toase
     */
    private static Toast toast;
    /**
     * 带图片toast
     */
    private static Toast imageToast;

    /**
     * 初始化toats
     *
     * @param message  提示内容
     * @param duration 时长
     * @return toast
     */
    private static Toast initToast(CharSequence message, int duration) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(), message, duration);
        } else {
            toast.setText(message);
            toast.setDuration(duration);
        }
        return toast;
    }

    /**
     * 短时间显示Toast
     *
     * @param message 提示内容
     */
    public static void showShort(CharSequence message) {
        initToast(message, Toast.LENGTH_SHORT).show();
    }


    /**
     * 短时间显示Toast
     *
     * @param strResId strResId
     */
    public static void showShort(int strResId) {
        initToast(MyApplication.getContext().getResources().getText(strResId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message 提示内容
     */
    public static void showLong(CharSequence message) {
        initToast(message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param strResId strResId
     */
    public static void showLong(int strResId) {
        initToast(MyApplication.getContext().getResources().getText(strResId), Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message  提示内容
     * @param duration 时长
     */
    public static void show(CharSequence message, int duration) {
        initToast(message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context  上下文
     * @param strResId strResId
     * @param duration 时长
     */
    public static void show(Context context, int strResId, int duration) {
        initToast(context.getResources().getText(strResId), duration).show();
    }

    /**
     * 显示有image的toast
     *
     * @param tvStr         显示文字
     * @param imageResource 图片资源id
     * @return toast2
     */
    public static Toast showToastWithImg(final String tvStr, final int imageResource) {
        if (imageToast == null) {
            imageToast = new Toast(MyApplication.getContext());
        }
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.layout_toast_succes, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_custom_tv);
        tv.setText(TextUtils.isEmpty(tvStr) ? "" : tvStr);
        ImageView iv = (ImageView) view.findViewById(R.id.toast_custom_iv);
        if (imageResource > 0) {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(imageResource);
        } else {
            iv.setVisibility(View.GONE);
        }
        imageToast.setView(view);
        imageToast.setGravity(Gravity.CENTER, 0, 0);
        imageToast.show();
        return imageToast;

    }

    /**
     * 带图片吐司 成功
     *
     * @param tvStr 消息
     * @return imageToast
     */
    public static Toast showImageToastSuccess(String tvStr) {
        if (imageToast == null) {
            imageToast = new Toast(MyApplication.getContext());
        }
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.layout_toast_succes, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_custom_tv);
        tv.setText(TextUtils.isEmpty(tvStr) ? "" : tvStr);
        imageToast.setView(view);
        imageToast.setGravity(Gravity.CENTER, 0, 0);
        imageToast.show();
        return imageToast;
    }


    /**
     * 带图片吐司 成功
     *
     * @param tvStr 消息
     * @return imageToast
     */
    public static Toast showImageToastFailuer(String tvStr) {
        if (imageToast == null) {
            imageToast = new Toast(MyApplication.getContext());
        }
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.layout_toast_failuer, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_custom_tv);
        tv.setText(TextUtils.isEmpty(tvStr) ? "" : tvStr);
        imageToast.setView(view);
        imageToast.setGravity(Gravity.CENTER, 0, 0);
        imageToast.show();
        return imageToast;
    }

    /**
     * 带图片吐司 提示
     *
     * @param tvStr 消息
     * @return imageToast
     */
    public static Toast showImageToastTips(String tvStr) {
        if (imageToast == null) {
            imageToast = new Toast(MyApplication.getContext());
        }
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.layout_toast_tips, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_custom_tv);
        tv.setText(tvStr);
        imageToast.setView(view);
        imageToast.setGravity(Gravity.CENTER, 0, 0);
        imageToast.setDuration(Toast.LENGTH_SHORT);
        imageToast.show();
        return imageToast;
    }
}
