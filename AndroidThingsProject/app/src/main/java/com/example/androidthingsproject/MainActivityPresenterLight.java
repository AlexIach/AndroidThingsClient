package com.example.androidthingsproject;

import android.util.Log;

import com.example.androidthingsproject.models.SensorsData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by aiachimov on 10/1/17.
 */

public class MainActivityPresenterLight implements ValueEventListener {

    private LightCallBack lightCallBack;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    public MainActivityPresenterLight() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void setUpCallback(LightCallBack lightCallBack) {
        this.lightCallBack = lightCallBack;
    }


    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        SensorsData sensorsData = dataSnapshot.getValue(SensorsData.class);
        lightCallBack.onLightStatusLoaded(sensorsData.getIsKitchenLightEnable(), sensorsData.getIsBathRoomLightEnable(), sensorsData.getIsMainRoomLightEnable());
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.d(MainActivity.TAG, "Error");
    }

    public void updateLightStatuses () {
        databaseReference.addValueEventListener(this);
    }

    public interface LightCallBack {
        void onLightStatusLoaded(boolean isLightKitchenEnable, boolean isLightBthroomEnable, boolean isLightMainRoomEnable);

        void onLightStatusLoadFailed(String error);
    }
}
