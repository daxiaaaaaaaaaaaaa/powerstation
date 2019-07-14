package com.jilian.powerstation.manege;

import android.util.Log;

import com.jilian.powerstation.common.dto.ReportDto;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by cxz on 2019/6/20
 * Details：
 */
public class CharDateManager {
    /**
     * @param time
     * @return
     */
    public static String getDates(List<ReportDto> list, float time, String type) {
        int currTime = (int) time;
        String dateStr = currTime + "";
        if (currTime < 1 || currTime > list.size()) {
            dateStr = "";
        } else {
            if ("MM".endsWith(type)) {
                dateStr = getMonthSimple(list.get(currTime - 1).getTime());
            }else {
                SimpleDateFormat sf = new SimpleDateFormat(type);
                dateStr = sf.format(new Date(list.get(currTime - 1).getTime()));
            }
        }
        return dateStr;
    }

    /**
     * @param type 日期类型 0 小时 1 天 2 月 3 年
     * @param time
     * @return
     */
    public static String getDate(int type, float time) {
        int currTime = (int) time;
        String dateStr = currTime + "";
        if (time < 9) {
            dateStr = "0" + dateStr;
        }
        switch (type) {
            case 0:
                dateStr = currTime + ":00";
                break;
            case 1:
            case 2:
            case 3:
        }
        return dateStr;
    }

    /**
     * @param type 日期类型 0 小时 1 天 2 月 3 年
     * @param time
     * @return
     */
    public static String getDateForm(int type, float time) {
        int currTime = (int) time;
        String dateStr = currTime + "";
        if (time < 9) {
            dateStr = "0" + dateStr;
        }
        switch (type) {
            case 0:
                dateStr += ":00";
                break;
            case 2:
                dateStr = getMonth(currTime);
                break;
            case 1:
            case 3:
        }
        return dateStr;
    }


    public static String getMonth(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "";
        }
    }

    public static String getMonthSimple(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        int month = c.get(Calendar.MONTH) + 1;
        switch (month) {
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
            default:
                return "";
        }

    }
}
