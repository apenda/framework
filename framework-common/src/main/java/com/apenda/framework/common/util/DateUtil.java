package com.apenda.framework.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author rui.zhou
 * @date 2021/05/17 11:14
 **/
@Slf4j
public class DateUtil {

    /**
     * 时间戳转String日期字符串
     * @param time
     * @return
     */
    public static String timeToString(Long time){
        Assert.notNull(time, "time is null");
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }

    /**
     * 传入生日日期判断是否成年
     *
     * @param birthDay
     * @return
     */
    public static boolean isAdultByDate(Date birthDay) {
        if (birthDay == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        long calendarTime = calendar.getTime().getTime();
        long birthDayTime = birthDay.getTime();
        return calendarTime - birthDayTime >= 0;
    }

    /**
     * String类型转日期
     * @param date
     * @return
     */
    public static Date stringToDate(String date){
        if (date == null || date.isEmpty()) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            log.error("string conversion date error", e);
        }
        return null;
    }
}
