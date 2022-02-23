package com.webApiDeveloper.rewards.util;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DateUtil {

    public String getMonthName(String longDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(Long.parseLong(longDate)));
        return theMonth(cal.get(Calendar.MONTH));
    }

    public static String theMonth(int month){
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }

}
