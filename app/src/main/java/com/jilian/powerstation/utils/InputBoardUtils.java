package com.jilian.powerstation.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class InputBoardUtils {

    public static void hideInputKeyBoard(EditText editText) {
        @SuppressLint("WrongConstant")
        InputMethodManager imm = (InputMethodManager)editText.getContext().getSystemService("input_method");
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        editText.clearFocus();
    }

    public static void showInputKeyBoard(EditText editText) {
        editText.requestFocus();
        @SuppressLint("WrongConstant")
        InputMethodManager imm = (InputMethodManager)editText.getContext().getSystemService("input_method");
        imm.showSoftInput(editText, 0);
    }
    /**
     * 显示软键盘
     *
     * @param context
     * @param view
     */
    public static void showInputMethodForQuery(final Context context,final View view) {

        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }
    /**
     * 隐藏软键盘
     *
     * @param context
     * @param view
     */
    public static void hideInputMethod(final Context context, final View view) {

        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.SHOW_FORCED);
        }

    }

}
