package com.example.androidthingsclient.view.common;

import android.support.v7.app.AppCompatActivity;

import com.example.androidthingsclient.util.ScreenNavigationManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aiachimov on 6/15/17.
 */

@Module
public abstract class BaseModule<T extends AppCompatActivity> {

    private T appCompatActivity;

    public BaseModule(T appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    public ScreenNavigationManager provideScreenNavigationManager() {
        return new ScreenNavigationManager(appCompatActivity.getSupportFragmentManager());
    }
}