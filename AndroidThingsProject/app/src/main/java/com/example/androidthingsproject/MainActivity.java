package com.example.androidthingsproject;

import java.io.IOException;

import com.example.androidthingsproject.data.services.AlarmManagerReceiver;
import com.example.androidthingsproject.data.services.JobSchedulerService;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements MainActivityPresenterLight.LightCallBack {

  public static final String TAG = MainActivity.class.getSimpleName();
  public MainActivityPresenterLight mainActivityPresenterLight;
  private Gpio mLedGpio1;
  private Gpio mLedGpio2;
  private Gpio mLedGpio3;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mainActivityPresenterLight = new MainActivityPresenterLight();

    mainActivityPresenterLight.setUpCallback(this);
    mainActivityPresenterLight.updateLightStatuses();

    startAlarm();

    Log.d(TAG, "Starting MainACtivity");

    PeripheralManagerService service = new PeripheralManagerService();
    Log.d(TAG, service.getGpioList().toString());
    try {
      mLedGpio1 = service.openGpio("BCM6");
      mLedGpio1.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);

      mLedGpio2 = service.openGpio("BCM13");
      mLedGpio2.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);

      mLedGpio3 = service.openGpio("BCM5");
      mLedGpio3.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);


      Log.d(TAG, "Start blinking LED GPIO pin");
    } catch (IOException e) {
      Log.d(TAG, "Error on PeripheralIO API", e);
    }

  }

  @Override
  protected void onStop() {
    stopService(new Intent(this, JobSchedulerService.class));
    super.onStop();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "Closing LED GPIO pin");
    try {
      mLedGpio1.close();
      mLedGpio2.close();
      mLedGpio3.close();
    } catch (IOException e) {
      Log.d(TAG, "Error on PeripheralIO API", e);
    } finally {
      mLedGpio1 = null;
      mLedGpio2 = null;
      mLedGpio3 = null;
    }
  }

  @Override
  public void onLightStatusLoaded(boolean isLightKitchenEnable, boolean isLightBthroomEnable, boolean isLightMainRoomEnable) {
    Log.d(TAG, " isLightKitchenEnable = " + isLightKitchenEnable +
      " isLightBthroomEnable = " + isLightBthroomEnable +
      " isLightMainRoomEnable = " + isLightMainRoomEnable);
    try {
      mLedGpio1.setValue(isLightKitchenEnable);
      mLedGpio2.setValue(isLightBthroomEnable);
      mLedGpio3.setValue(isLightMainRoomEnable);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onLightStatusLoadFailed(String error) {
    Log.d(TAG, "Failed error");
  }


  public void startAlarm() {
    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    Intent intent;
    PendingIntent pendingIntent;

    intent = new Intent(this, AlarmManagerReceiver.class);
    pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 0, 60000, pendingIntent);
  }
}
