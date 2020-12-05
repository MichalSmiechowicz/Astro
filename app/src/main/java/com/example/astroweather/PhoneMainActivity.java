package com.example.astroweather;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;
import com.example.astroweather.Fragments.MoonFragment;
import com.example.astroweather.Fragments.SunFragment;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PhoneMainActivity extends FragmentActivity  {

    private static final int PAGE_NUMBER = 2;
    private ViewPager2 viewPager;
    private final GlobalVar globalVar = GlobalVar.getGlobalVarInstance();
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_main);
        viewPager = findViewById(R.id.pager);
        if(viewPager != null){
            FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(this);
            viewPager.setAdapter(pagerAdapter);
        }
//        SunFragment sunFragment = new SunFragment();
//        MoonFragment moonFragment = new MoonFragment();

        EditText timeField = findViewById(R.id.timeField);
        timeField.setFocusable(false);

        Button confirmButton = findViewById(R.id.confirmData);
        EditText latitudeText = findViewById(R.id.latitudeText);
        EditText longitudeText = findViewById(R.id.longitudeText);
        latitudeText.setText("0");
        longitudeText.setText("0");

        Spinner refreshTimeSpinner = findViewById(R.id.refreshTimeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.time_periods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        refreshTimeSpinner.setAdapter(adapter);

        refreshTimeSpinner.setOnItemSelectedListener(new SpinnerHandler());
        confirmButton.setOnClickListener(OnClickListener -> {
            if (longitudeText.getText().toString().isEmpty() || latitudeText.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Wprowadź liczbę",Toast.LENGTH_SHORT).show();
            }else if(Math.abs(Double.parseDouble(longitudeText.getText().toString())) >180){
                Toast.makeText(getApplicationContext(),"Długość gograficzna musi być max +-180",Toast.LENGTH_SHORT).show();
            }else if(Math.abs(Double.parseDouble(latitudeText.getText().toString())) >90){
                Toast.makeText(getApplicationContext(),"Szerokość gograficzna musi być max +-90",Toast.LENGTH_SHORT).show();
            } else{
                globalVar.setLongitude(Double.parseDouble(longitudeText.getText().toString()));
                globalVar.setLatitude(Double.parseDouble(latitudeText.getText().toString()));

                if(viewPager == null){
                    calculateAstronomicalValuesForSunAndUpdate();
                    calculateAstronomicalValuesForMoonAndUpdate();
                }else if (viewPager.getCurrentItem() == 0){
                    calculateAstronomicalValuesForSunAndUpdate();
                }else {
                    calculateAstronomicalValuesForMoonAndUpdate();
                }
            }

        });

        Handler h = new Handler();
        h.post(new Runnable() {
            int counter = 0;
            Map<String, Integer> spinnerMap = globalVar.getSpinnerStringToTime();
            @Override
            public void run() {
                if (counter >= spinnerMap.get(globalVar.getRefreshTime()) ){
                    if(!longitudeText.getText().toString().isEmpty() && !latitudeText.getText().toString().isEmpty()){
                        if(viewPager == null){
                            calculateAstronomicalValuesForSunAndUpdate();
                            calculateAstronomicalValuesForMoonAndUpdate();
                        }else if (viewPager.getCurrentItem() == 0){
                            calculateAstronomicalValuesForSunAndUpdate();
                        }else {
                            calculateAstronomicalValuesForMoonAndUpdate();
                        }
                    }
                    counter=0;
                }
                timeField.setText(CalendarUtil.getStringifyTime());
                h.postDelayed(this, 1000);
                counter++;
            }
        });

//        Handler h2 = new Handler();
//        h2.post(new Runnable() {
//            @Override
//            public void run() {
//                calculateAstronomicalValuesForSunAndUpdate();
//                h2.postDelayed(this, 10000);
//            }
//        });

    }


    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private static class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                return new SunFragment();
            } else {
                return new MoonFragment();
            }
        }

        @Override
        public int getItemCount() {
            return PAGE_NUMBER;
        }
    }

    public void calculateAstronomicalValuesForSunAndUpdate() {

//        SunFragment sun =new SunFragment();
//        sun.calculateAstronomicalValuesForSunAndUpdate();
        AstroCalculator calculator = new CalendarUtil().getAstroCalc();

        TextView sunRiseTime = findViewById(R.id.sunRiseTimeText);
        TextView sunRiseAzimuth = findViewById(R.id.sunRiseAzimuthText);
        TextView sunSetTime = findViewById(R.id.sunSetTimeText);
        TextView sunSetAzimuth = findViewById(R.id.sunSetAzimuthText);
        TextView timeOfCivilianTwilight = findViewById(R.id.twilightTimeText);
        TextView timeOfCivilianDawn = findViewById(R.id.dawnTimeText);

        String temp = "Czas: " + calculator.getSunInfo().getSunrise().toString().substring(11,19);
        i++;
        sunRiseTime.setText(""+i);
        temp="Azymut: " + calculator.getSunInfo().getAzimuthRise();
        sunRiseAzimuth.setText(temp);
        temp = "Czas: " + calculator.getSunInfo().getSunset().toString().substring(11,19);
        sunSetTime.setText(temp);
        temp = "Azymut: " + calculator.getSunInfo().getAzimuthSet();
        sunSetAzimuth.setText(temp);
        temp = "Czas Zmierzcu Cywilnego: " + calculator.getSunInfo().getTwilightEvening().toString().substring(11,19);
        timeOfCivilianTwilight.setText(temp);
        temp = "Czas świtu Cywilnego: " + calculator.getSunInfo().getTwilightMorning().toString().substring(11,19);
        timeOfCivilianDawn.setText(temp);
    }

    private void calculateAstronomicalValuesForMoonAndUpdate() {

        AstroCalculator calculator = new CalendarUtil().getAstroCalc();
        TextView moonRiseTime = findViewById(R.id.moonRiseTimeText);
        TextView moonSetTime = findViewById(R.id.moonSetTimeText);
        TextView newMoon = findViewById(R.id.newMoonTimeText);
        TextView fullMoon = findViewById(R.id.fullMoonText);
        TextView moonPhase = findViewById(R.id.moonPhaseText);
        TextView dayOfTheSynodicMonth = findViewById(R.id.dayOfTheSynodicMonth);
        String temp = "";
        if ( calculator.getMoonInfo().getMoonrise()!= null) {
            temp = "Czas: " + calculator.getMoonInfo().getMoonrise().toString().substring(11, 19);
        }
        moonRiseTime.setText(temp);
        if (calculator.getMoonInfo().getMoonset() != null){
            temp="Czas: " + calculator.getMoonInfo().getMoonset().toString().substring(11,19);
        }
        moonSetTime.setText(temp);
        temp = "Nów: " + calculator.getMoonInfo().getNextNewMoon();
        newMoon.setText(temp);
        temp = "Pełnia: " + calculator.getMoonInfo().getNextFullMoon();
        fullMoon.setText(temp);
        temp = "Faza księżyca (w procentach): " +  Math.round(calculator.getMoonInfo().getIllumination()*100);
        moonPhase.setText(temp);
        temp = "Dzień miesiąca synodycznego: " + calculator.getMoonInfo().getAge();
        dayOfTheSynodicMonth.setText(temp);


    }

}