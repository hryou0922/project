package com.hry.project.dictation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 存储公共的方法处理类
 */
@SuppressWarnings("unused")
public class CommonDateUtils {
    private static Logger logger = LoggerFactory.getLogger(CommonDateUtils.class);

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal<SimpleDateFormat> dateTimeFormat = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATE_TIME_PATTERN);
        }
    };


    public static String dateTime2String(Date datetime) {
        if (datetime == null) {
            return "";
        }
        return dateTimeFormat.get().format(datetime);
    }


    public static String timeMillis2String(long timeMillis) {
        Date date = new Date(timeMillis);
        return dateTimeFormat.get().format(date);
    }

}
