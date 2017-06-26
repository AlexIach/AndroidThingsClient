package com.example.androidthingsclient.view.mainScreen.core.fragments.temperature_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidthingsclient.Injector;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.models.SensorsData;
import com.example.androidthingsclient.models.TemperatureIndicators;
import com.example.androidthingsclient.view.mainScreen.core.fragments.temperature_fragment.core.TemperaturePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by aiachimov on 6/19/17.
 */

public class TemperatureFragment extends Fragment implements TemperaturePresenter.TemperatureCallBack {

    @Inject
    TemperaturePresenter temperaturePresenter;

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
        temperaturePresenter.loadTemperature();
    }

    @Override
    public void onTemperatureLoaded(List<TemperatureIndicators> temperatureList) {
        Log.d("TAG", "TemperatureFragmentList size is " + temperatureList.size());
    }

    @Override
    public void onFailedQRCodeSave(String error) {
        Log.d("TAG", "Error is " + error);
    }
}
