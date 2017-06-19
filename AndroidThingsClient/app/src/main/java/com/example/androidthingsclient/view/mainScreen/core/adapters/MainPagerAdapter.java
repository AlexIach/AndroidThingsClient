package com.example.androidthingsclient.view.mainScreen.core.adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.example.androidthingsclient.view.mainScreen.core.fragments.graphics_fragment.GraphicsFragment;
import com.example.androidthingsclient.view.mainScreen.core.fragments.humidity_fragment.HumidityFragment;
import com.example.androidthingsclient.view.mainScreen.core.fragments.pressure_fragment.PressureFragment;
import com.example.androidthingsclient.view.mainScreen.core.fragments.temperature_fragment.TemperatureFragment;

import javax.inject.Inject;

/**
 * Created by aiachimov on 6/19/17.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private static final int FIRST_FRAGMENT = 0;
    private static final int SECOND_FRAGMENT = 1;
    private static final int THIRD_FRAGMENT = 2;
    private static final int FOURTH_FRAGMENT = 3;

    private int numberOfFragments;
    private Activity activity;

    @Inject
    public MainPagerAdapter(FragmentManager fm, int numberOfFragments, Activity activity) {
        super(fm);
        this.numberOfFragments = numberOfFragments;
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FIRST_FRAGMENT:
                ((AppCompatActivity) activity).getSupportActionBar().setTitle("Temperature");
                return TemperatureFragment.newInstance();
            case SECOND_FRAGMENT:
                ((AppCompatActivity) activity).getSupportActionBar().setTitle("Humidity");
                return HumidityFragment.newInstance();
            case THIRD_FRAGMENT:
                ((AppCompatActivity) activity).getSupportActionBar().setTitle("Pressure");
                return PressureFragment.newInstance();
            case FOURTH_FRAGMENT:
                ((AppCompatActivity) activity).getSupportActionBar().setTitle("Graphics");
                return GraphicsFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberOfFragments;
    }
}
