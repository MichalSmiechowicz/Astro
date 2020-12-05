package com.example.astroweather;

import android.view.View;
import android.widget.AdapterView;

public class SpinnerHandler implements AdapterView.OnItemSelectedListener {
    private final GlobalVar globalVar = GlobalVar.getGlobalVarInstance();

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        globalVar.setRefreshTime(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
