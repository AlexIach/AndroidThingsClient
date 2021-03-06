package com.example.androidthingsclient.view.splashScreen.builder;

import com.example.androidthingsclient.view.common.BaseModule;
import com.example.androidthingsclient.view.splashScreen.SplashActivity;

import dagger.Module;

/**
 * Created by aiachimov on 6/15/17.
 */

@Module
public class SplashActivityModule extends BaseModule<SplashActivity> {

    private SplashActivity splashActivity;

    public SplashActivityModule(SplashActivity splashActivity) {
        super(splashActivity);
        this.splashActivity = splashActivity;
    }

}
