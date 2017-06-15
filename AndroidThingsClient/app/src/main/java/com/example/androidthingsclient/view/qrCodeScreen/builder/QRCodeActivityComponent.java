package com.example.androidthingsclient.view.qrCodeScreen.builder;

import com.example.androidthingsclient.application.builder.AndroidThingsClientComponent;
import com.example.androidthingsclient.view.qrCodeScreen.QRCodeActivity;

import dagger.Component;

/**
 * Created by aiachimov on 6/15/17.
 */

@QRCodeActivityScope
@Component(dependencies = AndroidThingsClientComponent.class, modules = {QRCodeActivityModule.class})
public interface QRCodeActivityComponent {

    void inject(QRCodeActivity qrCodeActivity);
}
