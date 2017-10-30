package com.example.androidthingsclient.view.mainScreen.core.fragments.humidity_fragment;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.androidthingsclient.Injector;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.models.HumidityIndicators;
import com.example.androidthingsclient.util.DateFormatterProvider;
import com.example.androidthingsclient.view.mainScreen.core.fragments.humidity_fragment.core.HumidityPresenter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aiachimov on 6/19/17.
 */

public class HumidityFragment extends Fragment implements HumidityPresenter.HumidityCallBack {

    private static final int SLEEP_MILISEC = 1600;
    @Inject
    HumidityPresenter humidityPresenter;
    @Inject
    DateFormatterProvider dateFormatterProvider;

    @BindView(R.id.constraintLayoutHumidity)
    ConstraintLayout constraintLayoutHumidity;
    @BindView(R.id.textViewHumidityValue)
    TextView textViewHumidityValue;
    @BindView(R.id.textViewHumidityType)
    ImageView textViewHumidityType;
    @BindView(R.id.textViewHumidityTime)
    TextView textViewHumidityTime;

    @BindView(R.id.lottie_animation_view_loading_humidity)
    LottieAnimationView lottieAnimationViewHumidity;

    public static HumidityFragment newInstance() {
        return new HumidityFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Injector.INSTANCE.getMainActivityComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_humidity, container, false);
        ButterKnife.bind(this, view);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/germania_one.ttf");
        textViewHumidityValue.setTypeface(tf);
        textViewHumidityTime.setTypeface(tf);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        humidityPresenter.setUpCallback(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        new Task().execute();
    }

    @Override
    public void onHumidityLoaded(List<HumidityIndicators> humidityList) {
        Log.d("TAG", "HumidityFragmentList size is " + humidityList.size());
        HumidityIndicators currentHumidity = humidityList.get(humidityList.size() - 1);
        textViewHumidityValue.setText("Humidity  Value is " + currentHumidity.getValue() + "%");
        setHumidityTypeImage(currentHumidity.getType());
        DateTime currentDateTime = DateTime.now();
        DateTime lastSyncDateTime = new DateTime(Long.valueOf(currentHumidity.getTime()) * 1000);
        textViewHumidityTime.setText("Last synced : " + dateFormatterProvider.periodFormatter().print(new Period(lastSyncDateTime, currentDateTime)) + " ago");
/*
        if (isSmokeExist) {
            Log.d("Test", "isSmoke Exist = " + isSmokeExist);
            if (this.isVisible()) {
                sendNotification();
            }
        }*/
    }

    @Override
    public void onFailedGetHumidity(String error) {
        Log.d("TAG", "Error is " + error);
    }

    class Task extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            textViewHumidityValue.setVisibility(View.GONE);
            textViewHumidityType.setVisibility(View.GONE);
            textViewHumidityTime.setVisibility(View.GONE);
            lottieAnimationViewHumidity.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            lottieAnimationViewHumidity.setVisibility(View.GONE);
            textViewHumidityValue.setVisibility(View.VISIBLE);
            textViewHumidityType.setVisibility(View.VISIBLE);
            textViewHumidityTime.setVisibility(View.VISIBLE);
            super.onPostExecute(result);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            humidityPresenter.loadHumidity();
            try {
                Thread.sleep(SLEEP_MILISEC);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void setHumidityTypeImage(String temperatureType) {
        if (temperatureType.equalsIgnoreCase("Low")) {
            textViewHumidityType.setImageResource(R.drawable.humidity_low);
        } else if (temperatureType.equalsIgnoreCase("High")) {
            textViewHumidityType.setImageResource(R.drawable.humidity_high);
        } else {
            textViewHumidityType.setImageResource(R.drawable.humidity_normal);
        }
    }

    public void sendNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getContext())
                        .setSmallIcon(R.drawable.ic_aler)
                        .setContentTitle("Alert")
                        .setContentText("Smoke is detected");

        NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(Intent.ACTION_CALL);
        notificationIntent.setData(Uri.parse("tel:901"));
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
            /* | Intent.FLAG_ACTIVITY_SINGLE_TOP*/);

        PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 1,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(001, mBuilder.build());
    }
}
