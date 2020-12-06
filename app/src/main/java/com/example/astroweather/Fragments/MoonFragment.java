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
import com.example.astroweather.CalendarUtil;
import com.example.astroweather.GlobalVar;
import com.example.astroweather.R;

import java.util.Map;

public class MoonFragment extends Fragment {

    private View view;
    private final GlobalVar globalVar = GlobalVar.getGlobalVarInstance();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("TaskFragment", "onActivityCreated");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.moon_fragment, container, false);

        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onStart() {
        calculateAstronomicalValuesForMoonAndUpdate();
        super.onStart();


    }

    @Override
    public void onResume() {
        calculateAstronomicalValuesForMoonAndUpdate();
        super.onResume();
    }

    private void calculateAstronomicalValuesForMoonAndUpdate() {

        AstroCalculator calculator = new CalendarUtil().getAstroCalc();
        TextView moonRiseTime = view.findViewById(R.id.moonRiseTimeText);
        TextView moonSetTime = view.findViewById(R.id.moonSetTimeText);
        TextView newMoon = view.findViewById(R.id.newMoonTimeText);
        TextView fullMoon = view.findViewById(R.id.fullMoonText);
        TextView moonPhase = view.findViewById(R.id.moonPhaseText);
        TextView dayOfTheSynodicMonth = view.findViewById(R.id.dayOfTheSynodicMonth);
        String temp = "";
        if ( calculator.getMoonInfo().getMoonrise()!= null) {
            temp = "Czas: " + calculator.getMoonInfo().getMoonrise().toString().substring(11, 19);
        }
        moonRiseTime.setText(temp);
        if ( calculator.getMoonInfo().getMoonset()!= null){
            temp="Czas: " +  calculator.getMoonInfo().getMoonset().toString().substring(11,19);
        }
        moonSetTime.setText(temp);
        temp = "Nów: " + calculator.getMoonInfo().getNextNewMoon().toString().substring(0,19);
        newMoon.setText(temp);
        temp = "Pełnia: " + calculator.getMoonInfo().getNextFullMoon().toString().substring(0,19);
        fullMoon.setText(temp);
        temp = "Faza księżyca : " +  Math.round(calculator.getMoonInfo().getIllumination()*100) + "%";
        moonPhase.setText(temp);
        temp = "Dzień miesiąca synodycznego: " + calculator.getMoonInfo().getAge();
        dayOfTheSynodicMonth.setText(temp);


    }
}
