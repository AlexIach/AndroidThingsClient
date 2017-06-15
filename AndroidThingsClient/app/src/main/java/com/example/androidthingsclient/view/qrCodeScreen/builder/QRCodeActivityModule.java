package com.example.androidthingsclient.view.qrCodeScreen.builder;

import com.example.androidthingsclient.view.common.BaseModule;
import com.example.androidthingsclient.view.qrCodeScreen.QRCodeActivity;

import dagger.Module;

/**
 * Created by aiachimov on 6/15/17.
 */

@Module
public class QRCodeActivityModule extends BaseModule<QRCodeActivity> {

    private QRCodeActivity qrCodeActivity;

    public QRCodeActivityModule(QRCodeActivity qrCodeActivity) {
        super(qrCodeActivity);
        this.qrCodeActivity = qrCodeActivity;
    }
}
