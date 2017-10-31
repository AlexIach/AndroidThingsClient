package com.example.androidthingsclient.view.mainScreen.core.fragments.humidity_fragment.core;

import com.example.androidthingsclient.models.HumidityIndicators;
import com.example.androidthingsclient.models.SensorsData;
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
 * Created by aiachimov on 6/26/17.
 */

public class HumidityPresenter implements ValueEventListener {

    private HumidityCallBack callback;

    @Inject
    public HumidityPresenter() {
    }

    public void setUpCallback(HumidityCallBack callback) {
        this.callback = callback;
    }

    public void loadHumidity() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.addValueEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        SensorsData sensorsData = dataSnapshot.getValue(SensorsData.class);
        Collection<HumidityIndicators> humidityIndicatorses = sensorsData.getHumidityIndicators().values();
        ArrayList<HumidityIndicators> humidityIndicatorsArrayList = new ArrayList<>(humidityIndicatorses);
        callback.onHumidityLoaded(humidityIndicatorsArrayList);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        callback.onFailedGetHumidity("Error Code  is " + databaseError.getCode());
    }

    public interface HumidityCallBack {
        void onHumidityLoaded(List<HumidityIndicators> humidityList);

        void onFailedGetHumidity(String error);
    }
}
