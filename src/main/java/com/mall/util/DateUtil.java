package com.mall.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author :y.hao
 * time :2019/4/9
 * function:
 */
public class DateUtil {
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * Date类型转为指定格式的String类型
     *
     * @param source
     * @param pattern
     * @return
     */
    public static String DateToString(Date source, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(source);
    }

    public static Date getCurrentDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return stringToDate(simpleDateFormat.format(new Date()), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 字符串转换为对应日期
     *
     * @param source
     * @param pattern
     * @return
     */
    public static Date stringToDate(String source, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(source);
        } catch (Exception e) {
        }
        return date;
    }

    public static void main(String[] args) {
        String t = DateToString(new Date(1556795831000L),DATE_PATTERN);
    }
}
