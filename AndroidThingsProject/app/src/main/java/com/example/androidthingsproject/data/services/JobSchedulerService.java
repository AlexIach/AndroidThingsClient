package com.example.androidthingsproject.data.services;

import com.example.androidthingsproject.MainActivity;
import com.example.androidthingsproject.data.DataLoaderPresenter;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;


/**
 * Created by aiachimov on 10/8/17.
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerService extends JobService {

  private DataLoaderPresenter dataLoaderPresenter;

  @Override
  public void onCreate() {
    super.onCreate();
    Log.d(MainActivity.TAG, "Service created");
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.d(MainActivity.TAG, "Service destroyed");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    return START_NOT_STICKY;
  }

    /*Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //TODO Work must be here , i.e get data from sensors using presenter and upload it to Firebase
            Log.d(MainActivity.TAG, "Temperature info Time " + dataLoaderPresenter.getTemperatureIndicators().getTime());
            Log.d(MainActivity.TAG, "Temperature info Value " + dataLoaderPresenter.getTemperatureIndicators().getValue());
            Log.d(MainActivity.TAG, "Temperature info Type " + dataLoaderPresenter.getTemperatureIndicators().getType());
            Log.d(MainActivity.TAG, "Temperature info QRCode " + dataLoaderPresenter.getTemperatureIndicators().getQrCodeId());


            Log.d(MainActivity.TAG, "Humidity info Time " + dataLoaderPresenter.getHumidityIndicators().getTime());
            Log.d(MainActivity.TAG, "Humidity info Value " + dataLoaderPresenter.getHumidityIndicators().getValue());
            Log.d(MainActivity.TAG, "Humidity info Type " + dataLoaderPresenter.getHumidityIndicators().getType());
            Log.d(MainActivity.TAG, "Humidity info QRCode " + dataLoaderPresenter.getHumidityIndicators().getQrCodeId());


            Log.d(MainActivity.TAG, "Pressure info Time " + dataLoaderPresenter.getPressureIndicators().getTime());
            Log.d(MainActivity.TAG, "Pressure info Value " + dataLoaderPresenter.getPressureIndicators().getValue());
            Log.d(MainActivity.TAG, "Pressure info Type " + dataLoaderPresenter.getPressureIndicators().getType());
            Log.d(MainActivity.TAG, "Pressure info QRCode " + dataLoaderPresenter.getPressureIndicators().getQrCodeId());


            Log.d(MainActivity.TAG, "Count ");
            jobFinished(jobParameters, false);
        }
    };*/

  @Override
  public boolean onStopJob(JobParameters params) {
    Log.d(MainActivity.TAG, "on stop job: " + params.getJobId());
    // Return false to drop the job.
    return false;
  }

  @Override
  public boolean onStartJob(final JobParameters params) {
    this.dataLoaderPresenter = new DataLoaderPresenter(getApplicationContext());

    // Uses a handler to delay the execution of jobFinished().
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        //TODO Main work here
        Log.d(MainActivity.TAG, "Inside run()");
        jobFinished(params, false);
      }
    }, 2000);//2000 time to evaluate work.
    Log.d(MainActivity.TAG, "on start job: " + params.getJobId());

    // All works is completed - > return false*/
    return true; // if work is not done yet return true
  }
}
