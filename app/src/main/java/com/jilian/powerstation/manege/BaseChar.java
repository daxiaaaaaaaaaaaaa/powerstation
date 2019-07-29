package com.jilian.powerstation.manege;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by cxz on 2019-07-14
 * <p>
 * Discrebe:
 */
public class BaseChar {

    public int getIntValue(String value) {
        int mValue = 0;
        if (TextUtils.isEmpty(value)) {
            mValue = 0;
        }
        try {
            mValue = Integer.valueOf(value);
        } catch (Exception e) {
            mValue = 0;
        }
        return mValue;
    }

    public float getFloatValue(String value) {
        float mValue = 0;
        if (TextUtils.isEmpty(value)) {
            mValue = 0;
        }
        try {
            mValue = Float.parseFloat(value);
        } catch (Exception e) {
            mValue = 0;
        }
        return mValue;
    }

    //30个横坐标时，缩放4f是正好的。
    public float scalePercent = 4f/30f;

    public float scaleNum(int xCount){
        Log.e("TAG", "--->initDataView: " + xCount);
        return xCount * scalePercent;
    }


}
