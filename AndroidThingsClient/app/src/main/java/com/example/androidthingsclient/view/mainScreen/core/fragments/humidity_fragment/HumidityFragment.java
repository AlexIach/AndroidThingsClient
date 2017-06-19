package com.example.androidthingsclient.view.mainScreen.core.fragments.humidity_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidthingsclient.R;

import butterknife.ButterKnife;

/**
 * Created by aiachimov on 6/19/17.
 */

public class HumidityFragment extends Fragment {

    public static HumidityFragment newInstance() {
        return new HumidityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_humidity, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
