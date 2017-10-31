package com.example.androidthingsclient.view.mainScreen.core.fragments.lights_fragment.core;

import com.example.androidthingsclient.models.SensorsData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

/**
 * Created by aiachimov on 8/4/17.
 */

public class LightPresenter implements ValueEventListener {

    private static final String IS_KITCHEN_LIGHT_ENABLE_STATUS = "isKitchenLightEnable";
    private static final String IS_BATHROOM_LIGHT_ENABLE_STATUS = "isBathRoomLightEnable";
    private static final String IS_MAINROOM_LIGHT_ENABLE_STATUS = "isMainRoomLightEnable";
    private LightCallBack lightCallBack;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Inject
    public LightPresenter() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void setUpCallback(LightCallBack lightCallBack) {
        this.lightCallBack = lightCallBack;
    }

    public void onLoadLightStatuses() {
        databaseReference.addValueEventListener(this);
    }

    public void uploadKitchenLightStatus(boolean isKitchenLightEnable) {

        DatabaseReference databaseReferenceChildKithcen = databaseReference.child(IS_KITCHEN_LIGHT_ENABLE_STATUS);
        databaseReferenceChildKithcen.setValue(isKitchenLightEnable);
    }

    public void uploadBathroomLightStatus(boolean isBathroomLightEnable) {
        DatabaseReference databaseReferenceChildBathroom = databaseReference.child(IS_BATHROOM_LIGHT_ENABLE_STATUS);
        databaseReferenceChildBathroom.setValue(isBathroomLightEnable);
    }

    public void uploadMainRoomLightStatus(boolean isMainRoomLightEnable) {
        DatabaseReference databaseReferenceChildMainroom = databaseReference.child(IS_MAINROOM_LIGHT_ENABLE_STATUS);
        databaseReferenceChildMainroom.setValue(isMainRoomLightEnable);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        SensorsData sensorsData = dataSnapshot.getValue(SensorsData.class);
        lightCallBack.onLightStatusLoaded(sensorsData.getIsKitchenLightEnable(), sensorsData.getIsBathRoomLightEnable(), sensorsData.getIsMainRoomLightEnable());
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        lightCallBack.onLightStatusLoadFailed("Error Code  is " + databaseError.getCode());
    }

    public interface LightCallBack {
        void onLightStatusLoaded(boolean isLightKitchenEnable, boolean isLightBthroomEnable, boolean isLightMainRoomEnable);

        void onLightStatusLoadFailed(String error);
    }
}
