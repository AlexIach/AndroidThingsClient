package com.example.androidthingsclient.view.mainScreen.core.fragments.temperature_fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.androidthingsclient.Injector;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.models.TemperatureIndicators;
import com.example.androidthingsclient.view.mainScreen.core.fragments.temperature_fragment.core.TemperaturePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aiachimov on 6/19/17.
 */

public class TemperatureFragment extends Fragment implements TemperaturePresenter.TemperatureCallBack {


    private static final int SLEEP_MILISEC = 2000;
    @Inject
    TemperaturePresenter temperaturePresenter;

    @BindView(R.id.constraintLayoutTemperature)
    ConstraintLayout constraintLayoutTemperature;
    @BindView(R.id.textViewTemperatureValue)
    TextView textViewTemperatureValue;
    @BindView(R.id.textViewTemperatureType)
    TextView textViewTemperatureType;
    @BindView(R.id.textViewTemperatureTime)
    TextView textViewTemperatureTime;

    @BindView(R.id.lottie_animation_view_loading)
    LottieAnimationView lottieAnimationView;

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
    public void onTemperatureLoaded(List<TemperatureIndicators> temperatureList) {
        Log.d("TAG", "TemperatureFragmentList size is " + temperatureList.size());
        TemperatureIndicators currentTemperature = temperatureList.get(temperatureList.size() - 1);
        textViewTemperatureValue.setText("Temperature  Value is " + currentTemperature.getValue());
        textViewTemperatureType.setText("Temperature  Type is " + currentTemperature.getType());
        textViewTemperatureTime.setText("Last synced at " + currentTemperature.getTime());

    }

    @Override
    public void onFailedQRCodeSave(String error) {
        Log.d("TAG", "Error is " + error);
    }

    class Task extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            textViewTemperatureValue.setVisibility(View.GONE);
            textViewTemperatureType.setVisibility(View.GONE);
            textViewTemperatureTime.setVisibility(View.GONE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            textViewTemperatureValue.setVisibility(View.VISIBLE);
            textViewTemperatureType.setVisibility(View.VISIBLE);
            textViewTemperatureTime.setVisibility(View.VISIBLE);
            lottieAnimationView.setVisibility(View.GONE);
            super.onPostExecute(result);
        }

        @Override
        protected Boolean doInBackground(String... params) {

            try {
                temperaturePresenter.loadTemperature();
                Thread.sleep(SLEEP_MILISEC);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
