package com.example.astroweather.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;
import com.example.astroweather.CalendarUtil;
import com.example.astroweather.GlobalVar;
import com.example.astroweather.R;

import java.util.Calendar;

public class SunFragment extends Fragment {
    View view;
    private  TextView sunRiseTime;


    private final GlobalVar globalVar = GlobalVar.getGlobalVarInstance();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("TaskFragment", "onActivityCreated");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sun_fragment, container, false);
        calculateAstronomicalValuesForSunAndUpdate();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setRetainInstance(true);
    }

    @Override
    public void onStart() {
        calculateAstronomicalValuesForSunAndUpdate();
        super.onStart();


    }

    @Override
    public void onResume() {
        calculateAstronomicalValuesForSunAndUpdate();
        super.onResume();
    }

    public void calculateAstronomicalValuesForSunAndUpdate() {
        AstroCalculator calculator = new CalendarUtil().getAstroCalc();

        TextView sunRiseTime = view.findViewById(R.id.sunRiseTimeText);
        TextView sunRiseAzimuth = view.findViewById(R.id.sunRiseAzimuthText);
        TextView sunSetTime = view.findViewById(R.id.sunSetTimeText);
        TextView sunSetAzimuth = view.findViewById(R.id.sunSetAzimuthText);
        TextView timeOfCivilianTwilight = view.findViewById(R.id.twilightTimeText);
        TextView timeOfCivilianDawn = view.findViewById(R.id.dawnTimeText);

        String temp = "Czas: " + calculator.getSunInfo().getSunrise().toString().substring(11,19);
        sunRiseTime.setText(temp);
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
}
