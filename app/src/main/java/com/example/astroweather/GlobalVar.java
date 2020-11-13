package com.example.astroweather;

import android.app.Application;

public class GlobalVar   {
    private static String refreshTime ="";
    private static double longitude =0 ;
    private static double latitude = 0;
    private static GlobalVar instance =  new GlobalVar();


    private GlobalVar() {
    }

    public static GlobalVar getGlobalVarInstance(){
        return instance;
    }

    public static String getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(String refreshTime) {
        GlobalVar.refreshTime = refreshTime;
    }

    public  double getLongitude() {
        return longitude;
    }

    public  void setLongitude(double longitude) {
        GlobalVar.longitude = longitude;
    }

    public  double getLatitude() {
        return latitude;
    }

    public  void setLatitude(double latitude) {
        GlobalVar.latitude = latitude;
    }
}
