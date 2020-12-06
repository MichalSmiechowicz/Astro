package com.example.astroweather;

import android.text.format.DateFormat;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
    private final GlobalVar globalVar = GlobalVar.getGlobalVarInstance();


    public AstroCalculator getAstroCalc(){
        Calendar cal = Calendar.getInstance();
        AstroDateTime dateTime = new AstroDateTime(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) +1,
                cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.HOUR),
                cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND),
                cal.get(Calendar.ZONE_OFFSET),
                false);

        System.out.println(cal.get(Calendar.YEAR) +"  " + (cal.get(Calendar.MONTH )+1)  +"  " + cal.get(Calendar.DAY_OF_MONTH) + " " + cal.get(Calendar.ZONE_OFFSET));
        System.out.println("Lat:" + globalVar.getLatitude());
        System.out.println("Long:" + globalVar.getLongitude());
        AstroCalculator.Location location = new AstroCalculator.Location(globalVar.getLatitude(), globalVar.getLongitude());
        return new AstroCalculator(dateTime, location);
    }
    public static String getStringifyTime(){
        Calendar cal = Calendar.getInstance();
        int hours = cal.get(Calendar.HOUR);
        int minutes = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);
        Date d = new Date();
        CharSequence s  = DateFormat.format("EEEE, MMMM d, yyyy hh:mm:ss", d.getTime());

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
