package com.example.androidthingsclient.view.mainScreen.core.fragments.humidity_fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.androidthingsclient.Injector;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.models.HumidityIndicators;
import com.example.androidthingsclient.view.mainScreen.core.fragments.humidity_fragment.core.HumidityPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aiachimov on 6/19/17.
 */

public class HumidityFragment extends Fragment implements HumidityPresenter.HumidityCallBack {


    private static final int SLEEP_MILISEC = 2000;
    @Inject
    HumidityPresenter humidityPresenter;

    @BindView(R.id.constraintLayoutHumidity)
    ConstraintLayout constraintLayoutHumidity;
    @BindView(R.id.textViewHumidityValue)
    TextView textViewHumidityValue;
    @BindView(R.id.textViewHumidityType)
    TextView textViewHumidityType;
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
        lottieAnimationViewHumidity.setVisibility(View.VISIBLE);
        new Task().execute();
    }

    @Override
    public void onHumidityLoaded(List<HumidityIndicators> humidityList) {
        Log.d("TAG", "HumidityFragmentList size is " + humidityList.size());
        HumidityIndicators currentHumidity = humidityList.get(humidityList.size() - 1);
        textViewHumidityValue.setText("Humidity  Value is " + currentHumidity.getValue());
        textViewHumidityType.setText("Humidity  Type is " + currentHumidity.getType());
        textViewHumidityTime.setText("Last synced at " + currentHumidity.getTime());
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
            lottieAnimationViewHumidity.playAnimation();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            textViewHumidityValue.setVisibility(View.VISIBLE);
            textViewHumidityType.setVisibility(View.VISIBLE);
            textViewHumidityTime.setVisibility(View.VISIBLE);
            lottieAnimationViewHumidity.setVisibility(View.GONE);
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
}
