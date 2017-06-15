package com.example.androidthingsclient;

import com.example.androidthingsclient.application.AndroidThingsClientApp;
import com.example.androidthingsclient.application.builder.AndroidThingsClientComponent;
import com.example.androidthingsclient.application.builder.AndroidThingsClientModule;
import com.example.androidthingsclient.application.builder.DaggerAndroidThingsClientComponent;
import com.example.androidthingsclient.view.qrCodeScreen.QRCodeActivity;
import com.example.androidthingsclient.view.qrCodeScreen.builder.DaggerQRCodeActivityComponent;
import com.example.androidthingsclient.view.qrCodeScreen.builder.QRCodeActivityComponent;
import com.example.androidthingsclient.view.qrCodeScreen.builder.QRCodeActivityModule;
import com.example.androidthingsclient.view.splashScreen.SplashActivity;
import com.example.androidthingsclient.view.splashScreen.builder.DaggerSplashActivityComponent;
import com.example.androidthingsclient.view.splashScreen.builder.SplashActivityComponent;
import com.example.androidthingsclient.view.splashScreen.builder.SplashActivityModule;

/**
 * Created by aiachimov on 6/15/17.
 */

public enum Injector {

    INSTANCE;

    AndroidThingsClientComponent androidThingsClientComponent;
    SplashActivityComponent splashActivityComponent;
    QRCodeActivityComponent qrCodeActivityComponent;

    public void initAndroidThingsClientComponent(AndroidThingsClientApp androidThingsClientApp) {
        if (androidThingsClientComponent == null) {
            androidThingsClientComponent = DaggerAndroidThingsClientComponent.builder()
                    .androidThingsClientModule(new AndroidThingsClientModule(androidThingsClientApp))
                    .build();
        }
    }

    public void initSplashComponent(SplashActivity splashActivity) {
        if (splashActivityComponent == null) {
            splashActivityComponent = DaggerSplashActivityComponent.builder()
                    .splashActivityModule(new SplashActivityModule(splashActivity))
                    .androidThingsClientComponent(androidThingsClientComponent)
                    .build();
        }
    }

    public void initQRCodeComponent(QRCodeActivity qrCodeActivity) {
        if (qrCodeActivityComponent == null) {
            qrCodeActivityComponent = DaggerQRCodeActivityComponent.builder()
                    .qRCodeActivityModule(new QRCodeActivityModule(qrCodeActivity))
                    .androidThingsClientComponent(androidThingsClientComponent)
                    .build();
        }
    }


    public AndroidThingsClientComponent getAndroidThingsClientComponent() {
        return androidThingsClientComponent;
    }

    public SplashActivityComponent getSplashActivityComponent() {
        return splashActivityComponent;
    }

    public QRCodeActivityComponent getQrCodeActivityComponent() {
        return qrCodeActivityComponent;
    }
}
