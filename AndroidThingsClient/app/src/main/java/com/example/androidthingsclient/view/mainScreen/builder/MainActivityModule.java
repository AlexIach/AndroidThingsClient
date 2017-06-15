package com.example.androidthingsclient.view.mainScreen.builder;

import com.example.androidthingsclient.view.common.BaseModule;
import com.example.androidthingsclient.view.mainScreen.MainActivity;

import dagger.Module;

/**
 * Created by aiachimov on 6/15/17.
 */

@Module
public class MainActivityModule extends BaseModule<MainActivity> {

    private MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        super(mainActivity);
        this.mainActivity = mainActivity;
    }
}
