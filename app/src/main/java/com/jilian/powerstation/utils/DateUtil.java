package com.jilian.powerstation.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间格式化工具类
 *
 * @author weishixiong
 * @Time 2018-03-19
 */
public class DateUtil {
    public static final String DEFAULT_DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_ = "yyyy-MM-dd";
    public static final String DATE_FORMAT_MONTH = "yyyy-MM";
    public static final String DATE_FORMAT_TIME = "HH:mm:ss";
    public static final String DATE_FORMAT_HOUR_MIN = "HH:mm";
    public static final String DEFAULT_DATE_FORMATTER_MIN = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_DATE_FORMATTER_HOUR = "yyyy-MM-dd HH";
    public static final String EPARK_DATE_FORMATER = "yyyy/MM/dd HH:mm";
    public static final String DATE_FORMAT = "yyyy/MM/dd";
    public static final String DATE_FORMAT_POINT = "yyyy.MM.dd";
    public static final String DATE_FORMAT_CHINAL = "yyyy年MM月dd日";

    private final static long MINUTE = 60 * 1000;// 1分钟
    private final static long HOUR = 60 * MINUTE;// 1小时
    private final static long DAY = 24 * HOUR;// 1天
    private final static long MONTH = 31 * DAY;// 月
    private final static long YEAR = 12 * MONTH;// 年

    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > YEAR) {
            r = (diff / YEAR);
            return r + "年前";
        }
        if (diff > MONTH) {
            r = (diff / MONTH);
            return r + "个月前";
        }
        if (diff > DAY) {
            r = (diff / DAY);
            return r + "天前";
        }
        if (diff > HOUR) {
            r = (diff / HOUR);
            return r + "小时前";
        }
        if (diff > MINUTE) {
            r = (diff / MINUTE);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 字符串转换成 date
     *
     * @param pattern
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String pattern, String strDate) throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(pattern);

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return date;
    }


    /**
     * date转换成字符串
     *
     * @param destFormat
     * @param date
     * @return
     */
    public static String dateToString(String destFormat, Date date) {
        String strDate = null;
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(destFormat);
            strDate = format.format(date);
        }
        return strDate;
    }

    /**
     * 毫秒 转换
     *
     * @param time
     * @return
     */
    public static String timeToHms(long time) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;
        long day = time / dd;
        long hour = (time - day * dd) / hh;
        long minute = (time - day * dd - hour * hh) / mi;
        long second = (time - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = time - day * dd - hour * hh - minute * mi - second * ss;
        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
        return strHour + ":" + strMinute + ":" + strSecond;
    }


    private static final int YESTERDY = -1;
    private static final int TODAY = 0;
    private static final int TOMORROWDAT = 1;
    private static final int OTHER_DAY = 10000;
    /**
     * 调用显示日期
     */
    public static String getTitleDay(Date date){
        try {

            switch (JudgmentDay(date)) {
                case YESTERDY : {
                    return "昨天";
                }
                case TODAY : {
                    return "今天";
                }
                case TOMORROWDAT : {
                    return "明天";
                }
                default:
                    String str = DateUtil.dateToString(DateUtil.DATE_FORMAT,date);
                    return str;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 判断日期(效率比较高)
     */
    public static int JudgmentDay(Date date) throws ParseException {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            switch (diffDay) {
                case YESTERDY : {
                    return YESTERDY;
                }
                case TODAY : {
                    return TODAY;
                }
                case TOMORROWDAT : {
                    return TOMORROWDAT;
                }
            }
        }
        return OTHER_DAY;
    }

}
