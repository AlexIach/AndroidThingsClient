package com.example.androidthingsclient.view.mainScreen.builder;

import com.example.androidthingsclient.application.builder.AndroidThingsClientComponent;
import com.example.androidthingsclient.view.mainScreen.MainActivity;
import com.example.androidthingsclient.view.mainScreen.core.fragments.charts_fragment.ChartsFragment;
import com.example.androidthingsclient.view.mainScreen.core.fragments.humidity_fragment.HumidityFragment;
import com.example.androidthingsclient.view.mainScreen.core.fragments.pressure_fragment.PressureFragment;
import com.example.androidthingsclient.view.mainScreen.core.fragments.temperature_fragment.TemperatureFragment;

import dagger.Component;

/**
 * Created by aiachimov on 6/15/17.
 */

@MainActivityScope
@Component(dependencies = AndroidThingsClientComponent.class, modules = {MainActivityModule.class})
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(PressureFragment pressureFragment);

    void inject(TemperatureFragment temperatureFragment);

    void inject(HumidityFragment humidityFragment);

    void inject(ChartsFragment chartsFragment);

}
