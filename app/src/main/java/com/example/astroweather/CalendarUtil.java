package com.example.astroweather;

import android.text.format.DateFormat;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
    private final GlobalVar globalVar = GlobalVar.getGlobalVarInstance();

    public static String getStringifyTime(){
        Calendar cal = Calendar.getInstance();
        int hours = cal.get(Calendar.HOUR);
        int minutes = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);
        Date d = new Date();
        CharSequence s  = DateFormat.format("EEEE, MMMM d, yyyy hh:mm:ss", d.getTime());
        System.out.println(s);
        System.out.println(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
