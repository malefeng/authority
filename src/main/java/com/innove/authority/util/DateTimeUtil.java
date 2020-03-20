package com.innove.authority.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author paradise
 * @ClassName: DateTimeUtil.java
 * @Description: 日期时间处理工具类
 * @date 2019年8月30日
 */
public class DateTimeUtil {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String HOUR_START_DATE_FOMAT = "yyyy/m 00:00:00";

    public static final String Y_m_ = "yyyy/M 23:59:59";

    public static final String Y_M = "yyyy/M";

//    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd 23:59:59";

//    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd 23:59:59";

    /**
     * @param date
     * @return
     * @Title: getStandardDateString
     * @Description: 获取标准格式(yyyy - MM - dd HH : mm : ss)的时间字符串
     */
    public static String getStandardDateString(Date date) {
        return getFormatDateString("yyyy-MM-dd HH:mm:ss", date);
    }

    /**
     * @param date
     * @return
     * @Title: getStandardDateFromString
     * @Description: 标准格式(yyyy - MM - dd HH : mm : ss) string转date
     */
    public static Date getStandardDateFromString(String date) {
        return getDateFromString("yyyy-MM-dd HH:mm:ss", date);
    }

    /**
     * @param format
     * @param date
     * @return
     * @Title: getFormatDateString
     * @Description: 按照格式获取日期字符串
     */
    public static String getFormatDateString(String format, Date date) {
        if (format == null || date == null) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param format
     * @param stringDate
     * @return
     * @Title: getDateFromString
     * @Description: 按格式，string转date
     */
    public static Date getDateFromString(String format, String stringDate) {
        if (format == null || stringDate == null) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(stringDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return
     * @Title: getThisMonthLastDay
     * @Description: 获取当月最后一天(yyyy - MM - dd)
     */
    public static String getThisMonthLastDay() {
        try {
            // 设置时间格式
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            // 获得实体类
            Calendar ca = Calendar.getInstance();
            // 设置最后一天
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
            // 最后一天格式化
            String lastDay = format.format(ca.getTime());
            return lastDay;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return
     * @Title: getLastMonthFirstDay
     * @Description: 获取上月第一天
     */
    public static String getLastMonthFirstDay() {
        String firstDay;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前月的第一天
        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        firstDay = format.format(cal_1.getTime());
        return firstDay;
    }

    /**
     * @return
     * @Title: getLastMonthLastDay
     * @Description: 获取上月最后一天
     */
    public static String getLastMonthLastDay() {
        String lastDay;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
        lastDay = format.format(cale.getTime());
        return lastDay;
    }

    /**
     * @return
     * @Title: getCurrentQuarterFirstDay
     * @Description: 获得当季第一天
     */
    public static String getCurrentQuarterFirstDay() {
        try {
            Calendar c = Calendar.getInstance();
            int currentMonth = c.get(Calendar.MONTH) + 1;
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 6);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return
     * @Title: getCurrentQuarterEndTime
     * @Description: 获取当月最后一天
     */
    public static String getCurrentQuarterLastDay() {
        try {
            Calendar c = Calendar.getInstance();
            int currentMonth = c.get(Calendar.MONTH) + 1;
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取某月的最后一天结束时间
     *
     * @param:@param date yyyy-MM
     * @return:String yyyy-MM-dd HH:mm:ss
     */
    public static String getLastDayOfMonth(String date,String format) {

        String[] yearAndMonth = date.split("-");

        int year = Integer.valueOf(yearAndMonth[0]);
        int month = Integer.valueOf(yearAndMonth[1]);

        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(cal.getTime());
    }

    /**
     * 获取某年某月的第一天开始时间
     *
     * @param:@param date yyyy-MM
     * @param:@return
     * @return:String yyyy-MM-dd HH:mm:ss
     */
    public static String getFirstDayOfMonth(String date,String format) {

        String[] yearAndMonth = date.split("-");

        int year = Integer.valueOf(yearAndMonth[0]);
        int month = Integer.valueOf(yearAndMonth[1]);

        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(cal.getTime());
    }

    /**
     * @param dateA(yyyy-MM-dd HH:mm:ss)
     * @param dateB(yyyy-MM-dd HH:mm:ss)
     * @return
     * @Title: timeDistance
     * @Description: 计算两个时间点的间隔，单位秒
     */
    public static int dateDistance(String dateA, String dateB) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date a = sdf.parse(dateA);
            Date b = sdf.parse(dateB);
            return (int) ((b.getTime() - a.getTime()) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param timeA(HH:mm:ss)
     * @param timeB(HH:mm:ss)
     * @return
     * @Title: timeDistance
     * @Description: 计算两个时间点的间隔，单位秒
     */
    public static int timeDistance(String timeA, String timeB) {
        try {
            String dateA = "2000-01-01 " + timeA;
            String dateB = "2000-01-01 " + timeB;
            return dateDistance(dateA, dateB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param sourceDay
     * @return
     * @Title: addOneDay
     * @Description: 日期加一天，参数(yyyy-MM-dd)
     */
    public static String addOneDay(String sourceDay) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date sDate = sdf.parse(sourceDay);
            Calendar c = Calendar.getInstance();
            c.setTime(sDate);
            c.add(Calendar.DAY_OF_MONTH, 1);
            sDate = c.getTime();
            return sdf.format(sDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
