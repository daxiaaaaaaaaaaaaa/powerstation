package com.jilian.powerstation.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import java.lang.reflect.Method;

public  class AndroidWorkaround {

        public static void assistActivity(View content) {
            new AndroidWorkaround(content);
        }

        private View mChildOfContent;
        private int usableHeightPrevious;
        private ViewGroup.LayoutParams frameLayoutParams;

        private AndroidWorkaround(View content) {
            mChildOfContent = content;
            mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    possiblyResizeChildOfContent();
                }
            });
            frameLayoutParams = mChildOfContent.getLayoutParams();
        }

        private void possiblyResizeChildOfContent() {
            int usableHeightNow = computeUsableHeight();
            if (usableHeightNow != usableHeightPrevious) {

                frameLayoutParams.height = usableHeightNow;
                mChildOfContent.requestLayout();
                usableHeightPrevious = usableHeightNow;
            }
        }

        private int computeUsableHeight() {
            Rect r = new Rect();
            mChildOfContent.getWindowVisibleDisplayFrame(r);
            return (r.bottom);
        }

        /** 获取虚拟功能键高度 */

        public static int getVirtualBarHeigh(Context context) {

            int vh = 0;

            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            DisplayMetrics dm = new DisplayMetrics();

            try {

                @SuppressWarnings("rawtypes")
                Class c = Class.forName("android.view.Display");

                @SuppressWarnings("unchecked")
                Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);

                method.invoke(display, dm);
                vh = dm.heightPixels - windowManager.getDefaultDisplay().getHeight();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return vh;
        }

        public static boolean checkDeviceHasNavigationBar(Context context) {
            boolean hasNavigationBar = false;
            Resources rs = context.getResources();
            int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
            if (id > 0) {
                hasNavigationBar = rs.getBoolean(id);
            }
            try {
                Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
                Method m = systemPropertiesClass.getMethod("get", String.class);
                String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
                if ("1".equals(navBarOverride)) {
                    hasNavigationBar = false;
                } else if ("0".equals(navBarOverride)) {
                    hasNavigationBar = true;
                }
            } catch (Exception e) {

            }
            return hasNavigationBar;
        }
    }
