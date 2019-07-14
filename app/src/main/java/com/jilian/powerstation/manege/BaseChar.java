package com.jilian.powerstation.manege;

import android.text.TextUtils;

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
}
