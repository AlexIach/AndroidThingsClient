package com.example.androidthingsclient.view.mainScreen.core.fragments.pressure_fragment.core;

import com.example.androidthingsclient.models.PressureIndicators;
import com.example.androidthingsclient.models.SensorsData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by aiachimov on 6/26/17.
 */

public class PressurePresenter implements ValueEventListener {

    private PressureCallBack callback;

    @Inject
    public PressurePresenter() {
    }

    public void loadPressure() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.addValueEventListener(this);
    }

    public void setUpCallback(PressureCallBack callback) {
        this.callback = callback;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        SensorsData sensorsData = dataSnapshot.getValue(SensorsData.class);
        callback.onPressureLoaded(sensorsData.getPressureIndicators());
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        callback.onFailedGetPressure("Error Code  is " + databaseError.getCode());
    }


    public interface PressureCallBack {
        void onPressureLoaded(List<PressureIndicators> pressureList);

        void onFailedGetPressure(String error);
    }
}
