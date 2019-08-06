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
    public static final String EPARK_DATE_FORMATER_DATE = "dd/MM/yyyy";
    public static final String EPARK_DATE_FORMATER_MONTH = "MM/yyyy";
    public static final String EPARK_DATE_FORMATER_YEAR = "yyyy";

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
    public static String getTitleDay(Date date) {
        try {

            switch (JudgmentDay(date)) {
                case YESTERDY: {
                    return "昨天";
                }
                case TODAY: {
                    return "今天";
                }
                case TOMORROWDAT: {
                    return "明天";
                }
                default:
                    String str = DateUtil.dateToString(DateUtil.DATE_FORMAT, date);
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
                case YESTERDY: {
                    return YESTERDY;
                }
                case TODAY: {
                    return TODAY;
                }
                case TOMORROWDAT: {
                    return TOMORROWDAT;
                }
            }
        }
        return OTHER_DAY;
    }


    /**
     * 获取某一天的开始时间
     *
     * @param time
     * @return
     */
    public static String getDayBegin(long time) {
        SimpleDateFormat aDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return aDateFormat.format(c.getTime());
    }

    /**
     * 获取某一天的结束时间
     *
     * @param time
     * @return
     */
    public static String getDayEnd(long time) {
        SimpleDateFormat aDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return aDateFormat.format(c.getTime());
    }

    /**
     * 获取指定日期所在月份开始的时间
     * 时间格式yyyyMMdd
     *
     * @param date 指定日期
     * @return
     */
    public static String getMonthBegin(long date) {
        SimpleDateFormat aDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间
        return aDateFormat.format(c.getTime());
    }

    /**
     * 获取指定日期所在月份结束的时间
     * 时间格式yyyyMMdd
     *
     * @param date 指定日期
     * @return
     */
    public static String getMonthEnd(long date) {
        SimpleDateFormat aDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND, 59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间
        return aDateFormat.format(c.getTime());
    }


    /**
     * 获取当前时间的前一天时间
     *
     * @return
     */
    public static Date getBeforeDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //使用roll方法进行向前回滚
        //cl.roll(Calendar.DATE, -1);
        //使用set方法直接进行设置
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 获取当前时间的后一天时间
     *
     * @return
     */
    public static Date getAfterDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //使用roll方法进行回滚到后一天的时间
        //cl.roll(Calendar.DATE, 1);
        //使用set方法直接设置时间值
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }


    /**
     * 获取当前时间的前一月时间
     *
     * @return
     */
    public static Date getBeforeMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //使用roll方法进行向前回滚
        //cl.roll(Calendar.DATE, -1);
        //使用set方法直接进行设置
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取当前时间的后一月时间
     *
     * @return
     */
    public static Date getAfterMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //使用roll方法进行回滚到后一天的时间
        //cl.roll(Calendar.DATE, 1);
        //使用set方法直接设置时间值
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取当前时间的前一月时间
     *
     * @return
     */
    public static Date getBeforeYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //使用roll方法进行向前回滚
        //cl.roll(Calendar.DATE, -1);
        //使用set方法直接进行设置
        cal.add(Calendar.YEAR, -1);
        return cal.getTime();
    }

    /**
     * 获取当前时间的后一月时间
     *
     * @return
     */
    public static Date getAfterYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //使用roll方法进行回滚到后一天的时间
        //cl.roll(Calendar.DATE, 1);
        //使用set方法直接设置时间值
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }

    /**
     * 是否是过去时间
     * @param date
     * @param type 1 年  2 月 3 天
     * @return
     */
    public static boolean isBeforCurrentDate(Date date, int type) {
        Calendar currCalendar = Calendar.getInstance();
        currCalendar.setTime(date);
        int currYear = currCalendar.get(Calendar.YEAR);
        int currMonth = currCalendar.get(Calendar.MONTH);
        int currDay = currCalendar.get(Calendar.DAY_OF_MONTH);

        Calendar SystemCalendar = Calendar.getInstance();
        SystemCalendar.setTime(new Date(System.currentTimeMillis()));
        int systemYear = SystemCalendar.get(Calendar.YEAR);
        int systemMonth = SystemCalendar.get(Calendar.MONTH);
        int systemDay = SystemCalendar.get(Calendar.DAY_OF_MONTH);

        switch (type) {
            case 1:
                return currYear < systemYear;
            case 2:
                if (currYear < systemYear){
                    return true;
                }else  if (currYear == systemYear){
                    return currMonth < systemMonth;
                }else {
                    return false;
                }
           default:
               if (currYear < systemYear){
                   return true;
               }else  if (currYear == systemYear){
                   if (currMonth < systemMonth){
                       return true;
                   }else if(currMonth == systemMonth){
                       return currDay < systemDay;
                   }else {
                       return false;
                   }
               }else {
                   return false;
               }
        }
    }

    public static boolean isCurrentDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date currDate = new Date(System.currentTimeMillis());
        String currTime = format.format(currDate);
        String mTime = format.format(date);
        return currTime.equals(mTime);
    }

    public static boolean isCurrentMonth(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date currDate = new Date(System.currentTimeMillis());
        String currTime = format.format(currDate);
        String mTime = format.format(date);
        return currTime.equals(mTime);
    }

    public static boolean isCurrentYear(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Date currDate = new Date(System.currentTimeMillis());
        String currTime = format.format(currDate);
        String mTime = format.format(date);
        return currTime.equals(mTime);
    }

}
