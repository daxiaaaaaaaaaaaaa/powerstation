package com.jilian.powerstation.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * 常规校验
 */
public class Utils {

    public static boolean doubleyn(String str,int dousize){
        try {
            double num=Double.valueOf(str);//把字符串强制转换为数字
            if(str.trim().indexOf(".") == -1){
                return false;
            }
            int fourplace = str.trim().length() - str.trim().indexOf(".") - 1;
            if(fourplace<dousize){
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            return false;//如果抛出异常，返回False
        }

}
    /**
     * 包含大小写字母及数字且在6-12位
     * 是否包含
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]{6,16}$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }

    public static  boolean checkIndentity(String str){
        if(TextUtils.isEmpty(str)){
            return false;
        }
        String regex = "isIDCard2=/^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$/";
        boolean isRight =str.matches(regex);
        return isRight;

    }
    /**
     * 手机号校验
     */
    public static boolean checkPhoneNo(String phoneNum) {

        // 定义手机号的规则
        String phoneNumPattern = "^1[34578][0-9]{9}";
        // 比对phoneNum是否符合定义的规则
        boolean result = Pattern.matches(phoneNumPattern, phoneNum);
        return result;

    }



    /*
    校验过程：
    1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
    2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，将个位十位数字相加，即将其减去9），再求和。
    3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
    */
        /**
         * 校验银行卡卡号
         */
        public static boolean checkBankCard(String bankCard) {
            if(bankCard.length() < 15 || bankCard.length() > 19) {
                return false;
            }
            char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
            if(bit == 'N'){
                return false;
            }
            return bankCard.charAt(bankCard.length() - 1) == bit;
        }

        /**
         * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
         * @param nonCheckCodeBankCard
         * @return
         */
        public static char getBankCardCheckCode(String nonCheckCodeBankCard){
            if(nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
                    || !nonCheckCodeBankCard.matches("\\d+")) {
                //如果传的不是数据返回N
                return 'N';
            }
            char[] chs = nonCheckCodeBankCard.trim().toCharArray();
            int luhmSum = 0;
            for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
                int k = chs[i] - '0';
                if(j % 2 == 0) {
                    k *= 2;
                    k = k / 10 + k % 10;
                }
                luhmSum += k;
            }
            return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
        }
}
