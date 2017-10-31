package com.example.androidthingsclient.application;

import android.app.Application;

import com.example.androidthingsclient.Injector;

/**
 * Created by aiachimov on 6/15/17.
 */

public class AndroidThingsClientApp extends Application {

    private static AndroidThingsClientApp androidThingsClientApp;

    public static AndroidThingsClientApp getInstance() {
        return androidThingsClientApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        androidThingsClientApp = this;
        initDependencies();
    }

    public void initDependencies() {
        Injector.INSTANCE.initAndroidThingsClientComponent(this);
    }
}
