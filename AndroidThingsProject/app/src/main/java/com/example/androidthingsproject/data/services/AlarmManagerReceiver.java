package com.example.androidthingsproject.data.services;

import com.example.androidthingsproject.MainActivity;
import com.example.androidthingsproject.data.DataLoaderPresenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by aiachimov on 10/11/17.
 */

public class AlarmManagerReceiver extends BroadcastReceiver {

  private static final String SMOKE_STATUS = "isSmokeExist";

  private DataLoaderPresenter dataLoaderPresenter;
  private DatabaseReference databaseReferenceHumidity = FirebaseDatabase.getInstance()
    .getReference("humidityIndicators");
  private DatabaseReference databaseReferencePressure = FirebaseDatabase.getInstance()
    .getReference("pressureIndicators");
  private DatabaseReference databaseReferenceTemperature = FirebaseDatabase.getInstance()
    .getReference("temperatureIndicators");

  private FirebaseDatabase firebaseDatabase;
  private DatabaseReference databaseReference;

  @Override
  public void onReceive(Context context, Intent intent) {
    Log.d(MainActivity.TAG, "Alaram!!");
    initDatabase();

    if (dataLoaderPresenter == null) {
      dataLoaderPresenter = new DataLoaderPresenter(context);
    }

    Long timeStamp = System.currentTimeMillis() / 1000;

    databaseReferenceHumidity.child(timeStamp.toString())
      .setValue(dataLoaderPresenter.getHumidityIndicators());
    databaseReferencePressure.child(timeStamp.toString())
      .setValue(dataLoaderPresenter.getPressureIndicators());
    databaseReferenceTemperature.child(timeStamp.toString())
      .setValue(dataLoaderPresenter.getTemperatureIndicators());

    DatabaseReference databaseReferenceChildKithcen = databaseReference.child(SMOKE_STATUS);
    databaseReferenceChildKithcen.setValue(dataLoaderPresenter.getSmokeStatus());
  }

  private void initDatabase() {
    firebaseDatabase = FirebaseDatabase.getInstance();
    databaseReference = firebaseDatabase.getReference();
  }
}
