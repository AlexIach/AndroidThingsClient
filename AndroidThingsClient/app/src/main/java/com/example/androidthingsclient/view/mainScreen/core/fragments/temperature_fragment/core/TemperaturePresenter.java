package com.example.androidthingsclient.view.mainScreen.core.fragments.temperature_fragment.core;

import com.example.androidthingsclient.models.SensorsData;
import com.example.androidthingsclient.models.TemperatureIndicators;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        callback.onTemperatureLoaded(sensorsData.getTemperatureIndicators());
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        callback.onFailedQRCodeSave("Error Code  is " + databaseError.getCode());
    }


    public interface TemperatureCallBack {
        void onTemperatureLoaded(List<TemperatureIndicators> temperatureList);

        void onFailedQRCodeSave(String error);
    }
}
