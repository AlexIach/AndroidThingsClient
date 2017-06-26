package com.example.androidthingsclient.view.mainScreen.core.fragments.pressure_fragment;

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
import com.example.androidthingsclient.models.PressureIndicators;
import com.example.androidthingsclient.view.mainScreen.core.fragments.pressure_fragment.core.PressurePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aiachimov on 6/19/17.
 */

public class PressureFragment extends Fragment implements PressurePresenter.PressureCallBack {

    private static final int SLEEP_MILISEC = 2000;
    @Inject
    PressurePresenter pressurePresenter;

    @BindView(R.id.constraintLayoutPressure)
    ConstraintLayout constraintLayoutPressure;
    @BindView(R.id.textViewPressureValue)
    TextView textViewPressureValue;
    @BindView(R.id.textViewPressureType)
    TextView textViewPressureType;
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
        PressureIndicators currentPressure = pressureList.get(pressureList.size() - 1);
        textViewPressureValue.setText("Pressure  Value is " + currentPressure.getValue());
        textViewPressureType.setText("Pressure  Type is " + currentPressure.getType());
        textViewPressureTime.setText("Last synced at " + currentPressure.getTime());
    }

    @Override
    public void onFailedGetPressure(String error) {
        Log.d("TAG", "Error is " + error);
    }

    class Task extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            textViewPressureValue.setVisibility(View.GONE);
            textViewPressureType.setVisibility(View.GONE);
            textViewPressureTime.setVisibility(View.GONE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            textViewPressureValue.setVisibility(View.VISIBLE);
            textViewPressureType.setVisibility(View.VISIBLE);
            textViewPressureTime.setVisibility(View.VISIBLE);
            lottieAnimationViewPressure.setVisibility(View.GONE);
            super.onPostExecute(result);
        }

        @Override
        protected Boolean doInBackground(String... params) {

            try {
                pressurePresenter.loadPressure();
                Thread.sleep(SLEEP_MILISEC);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
