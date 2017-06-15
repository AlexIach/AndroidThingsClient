package com.example.androidthingsclient.view.splashScreen.builder;

import com.example.androidthingsclient.application.builder.AndroidThingsClientComponent;
import com.example.androidthingsclient.view.splashScreen.SplashActivity;

import dagger.Component;

/**
 * Created by aiachimov on 6/15/17.
 */

@SplashActivityScope
@Component(dependencies = AndroidThingsClientComponent.class, modules = {SplashActivityModule.class})
public interface SplashActivityComponent {

    void inject(SplashActivity homeActivity);
}
