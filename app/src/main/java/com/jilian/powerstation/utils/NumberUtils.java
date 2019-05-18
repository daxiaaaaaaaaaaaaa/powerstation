package com.jilian.powerstation.utils;



public class NumberUtils {



    /**
     * 格式化数字
     *
     * @param number
     * @return
     */
    public static String forMatNumber(double number) {

        if (number % 1 == 0) {
            return String.valueOf((int) (number)) + ".00";
        } else {
            return String.format("%.2f", number);
        }
    }

    /**
     * 格式化数字
     *
     * @param number
     * @return
     */
    public static String forMatOneNumber(double number) {

        if (number % 1 == 0) {
            return String.valueOf((int) (number)) + ".0";
        } else {
            return String.format("%.1f", number);
        }
    }

    /**
     * 如果要小数点 就保留 没有就整数
     *
     * @param number
     * @return
     */
    public static String forNormalMatNumber(double number) {

        if (number % 1 == 0) {
            return String.valueOf((int) (number));
        } else {
            return String.format("%.2f", number);
        }
    }

    /**
     * 格式化数字
     *
     * @param number
     * @return
     */
    public static String forMatZeroNumber(double number) {
        return (int) number + "";
    }
}
