package com.jilian.powerstation.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.jilian.powerstation.R;


/**
 * 对话框工具类
 *
 * @author weishixiong
 * @Time 2018-07-4
 */
public class PinziDialogUtils extends Dialog {

    public PinziDialogUtils(Context context) {
        super(context, R.style.my_dialog_style);
    }

    public static PinziDialogUtils getDialog(Context content, @LayoutRes int resource) {
        final PinziDialogUtils dialog = new PinziDialogUtils(content);
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

    public static PinziDialogUtils getDialogNotTouchOutside(Context content, @LayoutRes int resource) {
        final PinziDialogUtils dialog = new PinziDialogUtils(content);
        View view = LayoutInflater.from(content).inflate(resource, new LinearLayout(content));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        return dialog;
    }


}
