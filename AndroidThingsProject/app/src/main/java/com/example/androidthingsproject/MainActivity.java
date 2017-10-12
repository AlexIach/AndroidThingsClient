package com.example.androidthingsproject;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.androidthingsproject.data.services.AlarmManagerReceiver;
import com.example.androidthingsproject.data.services.JobSchedulerService;
import com.example.androidthingsproject.drivers.bmp180.Bmp180;
import com.example.androidthingsproject.utils.Bmp180Data;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;

public class MainActivity extends AppCompatActivity /*implements MainActivityPresenterLight.LightCallBack*/ {

    public static final String TAG = MainActivity.class.getSimpleName();
    private static final int MY_JOB_ID = 1;
    private static final String WEB_SERVICE_URL = "web_service_url";
    private ComponentName mServiceComponent;

    private Gpio mLedGpio1;
    private Gpio mLedGpio2;
    private Gpio mLedGpio3;


    private String I2C_BUS = "I2C1";
    private String SENSOR_DATA_REFERENCE = "bmp180";
    private Bmp180 mBmp180;

    public MainActivityPresenterLight mainActivityPresenterLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        mServiceComponent = new ComponentName(this, JobSchedulerService.class);

        //mainActivityPresenterLight = new MainActivityPresenterLight();

        //mainActivityPresenterLight.setUpCallback(this);
        //mainActivityPresenterLight.updateLightStatuses();

        //mBmp180 = new Bmp180(I2C_BUS);
        startAlarm();
        //scheduleJob();

        //startSensorPolling();


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
    protected void onStart() {
        super.onStart();
        //Intent startServiceIntent = new Intent(this, JobSchedulerService.class);
        //startService(startServiceIntent);
    }

    /**
     * Executed when user clicks on SCHEDULE JOB.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void scheduleJob() {
        JobInfo.Builder builder = new JobInfo.Builder(MY_JOB_ID, mServiceComponent);
        builder.setPeriodic(3000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

        Log.d(TAG, "Scheduling job");

        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int result = tm.schedule(builder.build());


            if (result <= 0) {
                Log.d(TAG, "SOmething went wrong");
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
        //cancelAllJobs();
        try {
            closeSensor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Close the Gpio pin.
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
        }
    }

    /*@Override
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

    private void startSensorPolling() {
        try {
            Bmp180Data bmp180 = getSensorData();
            Log.d(TAG, "temp is " + bmp180.getTemperature() + " press is " + bmp180.getPressure() + " alt is " + bmp180.getAltitude());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    private Bmp180Data getSensorData() throws IOException {
        int temp = (int) mBmp180.readTemperature();
        int press = mBmp180.readPressure();
        int alt = (int) mBmp180.readAltitude();

        return new Bmp180Data(alt, press, temp);
    }

    private void closeSensor() throws IOException {
        try {
            mBmp180.close();
        } catch (IOException e) {
            Log.e(TAG, "closeSensor  error: ", e);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void cancelAllJobs() {
        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.cancelAll();
    }


    public void startAlarm() {
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent;
        PendingIntent pendingIntent;

        intent = new Intent(this, AlarmManagerReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 0, 60000, pendingIntent);
    }
}
