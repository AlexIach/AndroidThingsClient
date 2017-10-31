package com.example.androidthingsclient.view.mainScreen.core.fragments.temperature_fragment;

import android.app.Notification;
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
import com.example.androidthingsclient.models.TemperatureIndicators;
import com.example.androidthingsclient.util.DateFormatterProvider;
import com.example.androidthingsclient.util.ScreenNavigationManager;
import com.example.androidthingsclient.view.mainScreen.core.fragments.temperature_fragment.core.TemperaturePresenter;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aiachimov on 6/19/17.
 */

public class TemperatureFragment extends Fragment implements TemperaturePresenter.TemperatureCallBack {

    private static final int SLEEP_MILISEC = 1600;
    private final int CALL_REQUEST = 100;

    @Inject
    TemperaturePresenter temperaturePresenter;
    @Inject
    DateFormatterProvider dateFormatterProvider;

    @BindView(R.id.constraintLayoutTemperature)
    ConstraintLayout constraintLayoutTemperature;
    @BindView(R.id.textViewTemperatureValue)
    TextView textViewTemperatureValue;
    @BindView(R.id.textViewTemperatureType)
    ImageView textViewTemperatureType;
    @BindView(R.id.textViewTemperatureTime)
    TextView textViewTemperatureTime;

    @BindView(R.id.lottie_animation_view_loading)
    LottieAnimationView lottieAnimationViewTemperature;

    @Inject
    ScreenNavigationManager screenNavigationManager;

    public static TemperatureFragment newInstance() {
        return new TemperatureFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Injector.INSTANCE.getMainActivityComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);
        ButterKnife.bind(this, view);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/germania_one.ttf");
        textViewTemperatureValue.setTypeface(tf);
        textViewTemperatureTime.setTypeface(tf);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        temperaturePresenter.setUpCallback(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        new Task().execute();
    }

    @Override
    public void onTemperatureLoaded(List<TemperatureIndicators> temperatureList, boolean isSmokeExist) {
        Log.d("TAG", "TemperatureFragmentList size is " + temperatureList.size());
        TemperatureIndicators currentTemperature = temperatureList.get(temperatureList.size() - 1);
        textViewTemperatureValue.setText("Current temperature is " + currentTemperature.getValue());
        setTemperatyreTypeImage(currentTemperature.getType());
        DateTime currentDateTime = DateTime.now();

        DateTime lastSyncDateTime = new DateTime(Long.valueOf(currentTemperature.getTime()) * 1000);
        textViewTemperatureTime.setText("Last synced : " + dateFormatterProvider.periodFormatter().print(new Period(lastSyncDateTime, currentDateTime)) + " ago");


        if (isSmokeExist) {
            if (this.isVisible()) {
                sendNotification();
            }
        }
    }

    @Override
    public void onFailedGetTemperature(String error) {
        Log.d("TAG", "Error is " + error);
    }

    private void setTemperatyreTypeImage(String temperatureType) {
        if (temperatureType.equalsIgnoreCase("Cold")) {
            textViewTemperatureType.setImageResource(R.drawable.cold);
        } else if (temperatureType.equalsIgnoreCase("Hot")) {
            textViewTemperatureType.setImageResource(R.drawable.warm);
        } else {
            textViewTemperatureType.setImageResource(R.drawable.normal);
        }
    }

    public void sendNotification() {
        NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:901"));
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getContext())
                        .setSmallIcon(R.drawable.ic_aler)
                        .setContentTitle(getString(R.string.alert_message))
                        .setContentText(getString(R.string.smoke_detected_message))
                        .setContentIntent(pendingIntent);

        Notification notification = mBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;


        mNotificationManager.notify(0, notification);
    }

    class Task extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            textViewTemperatureValue.setVisibility(View.GONE);
            textViewTemperatureType.setVisibility(View.GONE);
            textViewTemperatureTime.setVisibility(View.GONE);
            lottieAnimationViewTemperature.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            lottieAnimationViewTemperature.setVisibility(View.GONE);
            textViewTemperatureValue.setVisibility(View.VISIBLE);
            textViewTemperatureType.setVisibility(View.VISIBLE);
            textViewTemperatureTime.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            temperaturePresenter.loadTemperature();
            try {
                Thread.sleep(SLEEP_MILISEC);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
