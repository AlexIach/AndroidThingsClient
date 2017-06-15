package com.example.androidthingsclient.view.mainScreen.builder;

import com.example.androidthingsclient.application.builder.AndroidThingsClientComponent;
import com.example.androidthingsclient.view.mainScreen.MainActivity;
import com.example.androidthingsclient.view.qrCodeScreen.builder.QRCodeActivityScope;

import dagger.Component;

/**
 * Created by aiachimov on 6/15/17.
 */

@QRCodeActivityScope
@Component(dependencies = AndroidThingsClientComponent.class, modules = {MainActivityModule.class})
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
