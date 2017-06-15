package com.example.androidthingsclient.application.builder;

import android.content.Context;

import com.example.androidthingsclient.application.AndroidThingsClientApp;
import com.example.androidthingsclient.util.SharedPrefUtil;
import com.example.androidthingsclient.util.StringUtil;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aiachimov on 6/15/17.
 */

@Module
public class AndroidThingsClientModule {

    AndroidThingsClientApp androidThingsClientApp;

    public AndroidThingsClientModule(AndroidThingsClientApp androidThingsClientApp) {
        this.androidThingsClientApp = androidThingsClientApp;
    }

    @Provides
    public Context provideApplicationContext() {
        return androidThingsClientApp.getApplicationContext();
    }

    @Provides
    public StringUtil provideStringUtil() {
        return new StringUtil();
    }

    @Provides
    public SharedPrefUtil provideSharedPrefUtil() {
        return new SharedPrefUtil();
    }
}
