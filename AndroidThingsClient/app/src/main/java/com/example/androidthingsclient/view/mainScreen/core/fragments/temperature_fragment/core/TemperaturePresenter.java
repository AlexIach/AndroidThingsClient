package com.example.androidthingsclient.view.mainScreen.core.fragments.temperature_fragment.core;

import com.example.androidthingsclient.models.SensorsData;
import com.example.androidthingsclient.models.TemperatureIndicators;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by aiachimov on 6/20/17.
 */

public class TemperaturePresenter implements ValueEventListener {

    private TemperatureCallBack callback;

    @Inject
    public TemperaturePresenter() {
    }

    public void loadTemperature() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.addValueEventListener(this);
    }

    public void setUpCallback(TemperatureCallBack callback) {
        this.callback = callback;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        SensorsData sensorsData = dataSnapshot.getValue(SensorsData.class);
        Collection<TemperatureIndicators> temperatureIndicatorses = sensorsData.getTemperatureIndicators().values();
        ArrayList<TemperatureIndicators> temperatureIndicatorsArrayList = new ArrayList<>(temperatureIndicatorses);
        callback.onTemperatureLoaded(temperatureIndicatorsArrayList);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        callback.onFailedGetTemperature("Error Code  is " + databaseError.getCode());
    }


    public interface TemperatureCallBack {
        void onTemperatureLoaded(List<TemperatureIndicators> temperatureList);

        void onFailedGetTemperature(String error);
    }
}
