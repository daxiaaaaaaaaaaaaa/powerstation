package com.jilian.powerstation.utils;

import android.os.Build;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 判断对象是否为空工具类
 *
 * @author weishixiong
 * @Time 2018-04-10
 */
public class EmptyUtils {
    /**
     * 判断对象是否为空
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj)
    {
            if (obj == null)
            {
                return false;
            }
            if (obj instanceof String && obj.toString().length() == 0)
            {
                return false;
            }
            if (obj.getClass().isArray() && Array.getLength(obj) == 0)
            {
                return false;
            }
            if (obj instanceof Collection && ((Collection) obj).isEmpty())
            {
                return false;
            }
            if (obj instanceof Map && ((Map) obj).isEmpty())
            {
                return false;
            }
            if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0)
            {
                return false;
            }
            if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0)
            {
                return false;
            }
            if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0)
            {
                return false;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0)
                {
                    return false;
                }
            }
           return true;
    }
}
