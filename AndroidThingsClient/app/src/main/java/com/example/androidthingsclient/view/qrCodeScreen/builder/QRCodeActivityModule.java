package com.example.androidthingsclient.view.qrCodeScreen.builder;

import com.example.androidthingsclient.util.SharedPrefUtil;
import com.example.androidthingsclient.util.StringUtil;
import com.example.androidthingsclient.view.common.BaseModule;
import com.example.androidthingsclient.view.qrCodeScreen.QRCodeActivity;
import com.example.androidthingsclient.view.qrCodeScreen.core.presenter.QRCodeActivityPresenter;

import dagger.Module;
import dagger.Provides;

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

    @Provides
    public QRCodeActivityPresenter provideQRCodeActivityPresenter(SharedPrefUtil sharedPrefUtil, StringUtil stringUtil) {
        return new QRCodeActivityPresenter(sharedPrefUtil, stringUtil);
    }
}
