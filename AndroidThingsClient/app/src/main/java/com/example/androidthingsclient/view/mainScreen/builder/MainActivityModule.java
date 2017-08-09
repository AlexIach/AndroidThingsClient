package com.example.androidthingsclient.view.mainScreen.builder;

import android.content.Context;

import com.example.androidthingsclient.util.DateFormatterProvider;
import com.example.androidthingsclient.view.common.BaseModule;
import com.example.androidthingsclient.view.mainScreen.MainActivity;
import com.example.androidthingsclient.view.mainScreen.core.adapters.MainPagerAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by aiachimov on 6/15/17.
 */

@Module
public class MainActivityModule extends BaseModule<MainActivity> {

    public static final int NUMBER_OF_TABS = 4;

    private MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        super(mainActivity);
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public MainPagerAdapter provideMainPagerAdapter() {
        return new MainPagerAdapter(mainActivity.getSupportFragmentManager(), NUMBER_OF_TABS);
    }

    @Provides
    @MainActivityScope
    public DateFormatterProvider provideDateFormatterProvider (Context context) {
        return new DateFormatterProvider(context);
    }
}
