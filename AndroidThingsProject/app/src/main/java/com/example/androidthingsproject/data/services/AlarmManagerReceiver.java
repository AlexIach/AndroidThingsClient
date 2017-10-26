package com.example.androidthingsproject.data.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.androidthingsproject.MainActivity;
import com.example.androidthingsproject.data.DataLoaderPresenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

/**
 * Created by aiachimov on 10/11/17.
 */

public class AlarmManagerReceiver extends BroadcastReceiver {

    private DataLoaderPresenter dataLoaderPresenter;
    private DatabaseReference databaseReferenceHumidity = FirebaseDatabase.getInstance().getReference("humidityIndicators");
    private DatabaseReference databaseReferencePressure = FirebaseDatabase.getInstance().getReference("pressureIndicators");
    private DatabaseReference databaseReferenceTemperature = FirebaseDatabase.getInstance().getReference("temperatureIndicators");

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(MainActivity.TAG, "Alaram!!");

        if(dataLoaderPresenter == null) {
            dataLoaderPresenter = new DataLoaderPresenter(context);
        }

        Long timeStamp = System.currentTimeMillis()/1000;

        databaseReferenceHumidity.child(timeStamp.toString()).setValue(dataLoaderPresenter.getHumidityIndicators());
        databaseReferencePressure.child(timeStamp.toString()).setValue(dataLoaderPresenter.getPressureIndicators());
        databaseReferenceTemperature.child(timeStamp.toString()).setValue(dataLoaderPresenter.getTemperatureIndicators());

    }
}
