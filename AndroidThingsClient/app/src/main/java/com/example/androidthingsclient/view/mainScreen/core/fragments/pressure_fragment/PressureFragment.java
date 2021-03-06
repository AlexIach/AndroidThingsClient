package com.example.androidthingsclient.view.mainScreen.core.fragments.pressure_fragment;

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
import com.example.androidthingsclient.models.PressureIndicators;
import com.example.androidthingsclient.util.DateFormatterProvider;
import com.example.androidthingsclient.view.mainScreen.core.fragments.pressure_fragment.core.PressurePresenter;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aiachimov on 6/19/17.
 */

public class PressureFragment extends Fragment implements PressurePresenter.PressureCallBack {

    private static final int SLEEP_MILISEC = 1600;
    @Inject
    PressurePresenter pressurePresenter;
    @Inject
    DateFormatterProvider dateFormatterProvider;

    @BindView(R.id.constraintLayoutPressure)
    ConstraintLayout constraintLayoutPressure;
    @BindView(R.id.textViewPressureValue)
    TextView textViewPressureValue;
    @BindView(R.id.textViewPressureType)
    ImageView textViewPressureType;
    @BindView(R.id.textViewPressureTime)
    TextView textViewPressureTime;

    @BindView(R.id.lottie_animation_view_loading_pressure)
    LottieAnimationView lottieAnimationViewPressure;

    public static PressureFragment newInstance() {
        return new PressureFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Injector.INSTANCE.getMainActivityComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pressure, container, false);
        ButterKnife.bind(this, view);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/germania_one.ttf");
        textViewPressureValue.setTypeface(tf);
        textViewPressureTime.setTypeface(tf);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pressurePresenter.setUpCallback(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        new Task().execute();
    }

    @Override
    public void onPressureLoaded(List<PressureIndicators> pressureList) {
        Log.d("TAG", "PressureFragmentList size is " + pressureList.size());

        Collections.sort(pressureList, new Comparator<PressureIndicators>() {
            @Override
            public int compare(PressureIndicators o1, PressureIndicators o2) {
                return Integer.parseInt(o1.getTime()) < Integer.parseInt(o2.getTime()) ? -1
                        : Integer.parseInt(o1.getTime()) > Integer.parseInt(o2.getTime()) ? 1
                        : 0;
            }
        });

        PressureIndicators currentPressure = pressureList.get(pressureList.size() - 1);
        textViewPressureValue.setText("Pressure  Value is " + currentPressure.getValue());
        setPressureTypeImage(currentPressure.getType());
        DateTime currentDateTime = DateTime.now();
        DateTime lastSyncDateTime = new DateTime(Long.valueOf(currentPressure.getTime()) * 1000);
        textViewPressureTime.setText("Last synced : " + dateFormatterProvider.periodFormatter().print(new Period(lastSyncDateTime, currentDateTime)) + " ago");

     /*   if (isSmokeExist) {
            Log.d("Test", "isSmoke Exist = " + isSmokeExist);
            if (this.isVisible()) {
                sendNotification();
            }
        }*/
    }

    @Override
    public void onFailedGetPressure(String error) {
        Log.d("TAG", "Error is " + error);
    }

    private void setPressureTypeImage(String temperatureType) {
        if (temperatureType.equalsIgnoreCase("Low")) {
            textViewPressureType.setImageResource(R.drawable.pressure_low);
        } else if (temperatureType.equalsIgnoreCase("High")) {
            textViewPressureType.setImageResource(R.drawable.pressure_high);
        } else {
            textViewPressureType.setImageResource(R.drawable.pressure_normal);
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
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(001, mBuilder.build());
    }

    class Task extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            textViewPressureValue.setVisibility(View.GONE);
            textViewPressureType.setVisibility(View.GONE);
            textViewPressureTime.setVisibility(View.GONE);
            lottieAnimationViewPressure.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            lottieAnimationViewPressure.setVisibility(View.GONE);
            textViewPressureValue.setVisibility(View.VISIBLE);
            textViewPressureType.setVisibility(View.VISIBLE);
            textViewPressureTime.setVisibility(View.VISIBLE);
            super.onPostExecute(result);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            pressurePresenter.loadPressure();
            try {
                Thread.sleep(SLEEP_MILISEC);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
