package com.example.androidthingsclient.view.mainScreen.core.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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

    @Inject
    public MainPagerAdapter(FragmentManager fm, int numberOfFragments) {
        super(fm);
        this.numberOfFragments = numberOfFragments;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FIRST_FRAGMENT:
                return TemperatureFragment.newInstance();
            case SECOND_FRAGMENT:
                return HumidityFragment.newInstance();
            case THIRD_FRAGMENT:
                return PressureFragment.newInstance();
            case FOURTH_FRAGMENT:
                return GraphicsFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberOfFragments;
    }
}
