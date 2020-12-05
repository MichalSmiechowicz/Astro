package com.example.astroweather;


import java.util.HashMap;

public class GlobalVar   {
    private static String refreshTime ="10 sec";
    private static double longitude =0 ;
    private static double latitude = 0;
    private static GlobalVar instance =  new GlobalVar();
    private static HashMap<String,Integer> spinnerStringToTime = new HashMap<>();
    static {
        spinnerStringToTime.put("10 sec", 10);
        spinnerStringToTime.put("15 min", 900);
        spinnerStringToTime.put("30 min", 1800);
        spinnerStringToTime.put("45 min", 2700);
        spinnerStringToTime.put("1 h", 3600);
    }
    private GlobalVar() {

    }

    public HashMap<String, Integer> getSpinnerStringToTime() {
        return spinnerStringToTime;
    }

    public static GlobalVar getGlobalVarInstance(){
        return instance;
    }

    public  String getRefreshTime() {
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
