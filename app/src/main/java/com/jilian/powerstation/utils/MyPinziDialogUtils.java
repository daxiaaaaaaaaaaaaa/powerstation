package com.jilian.powerstation.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;



/**
 * 对话框工具类
 *
 * @author weishixiong
 * @Time 2018-07-4
 */
public class MyPinziDialogUtils extends Dialog {

    public MyPinziDialogUtils(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    public static MyPinziDialogUtils getDialog(Context content, @LayoutRes int resource) {
        final MyPinziDialogUtils dialog = new MyPinziDialogUtils(content);
        View view = LayoutInflater.from(content).inflate(resource, new LinearLayout(content));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        return dialog;
    }

    public static MyPinziDialogUtils getDialogNotTouchOutside(Context content, @LayoutRes int resource) {
        final MyPinziDialogUtils dialog = new MyPinziDialogUtils(content);
        View view = LayoutInflater.from(content).inflate(resource, new LinearLayout(content));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        return dialog;
    }


}
