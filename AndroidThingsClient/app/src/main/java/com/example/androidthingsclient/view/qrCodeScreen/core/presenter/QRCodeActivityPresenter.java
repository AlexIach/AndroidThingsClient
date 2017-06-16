package com.example.androidthingsclient.view.qrCodeScreen.core.presenter;

import android.content.Context;

import com.example.androidthingsclient.R;
import com.example.androidthingsclient.util.SharedPrefUtil;
import com.example.androidthingsclient.util.StringUtil;

import javax.inject.Inject;

/**
 * Created by aiachimov on 6/16/17.
 */

public class QRCodeActivityPresenter {

    private SharedPrefUtil sharedPrefUtil;
    private StringUtil stringUtil;

    private QRCodeCallBack callback;

    @Inject
    public QRCodeActivityPresenter(SharedPrefUtil sharedPrefUtil, StringUtil stringUtil) {
        this.sharedPrefUtil = sharedPrefUtil;
        this.stringUtil = stringUtil;
    }

    public void setUpCallback(QRCodeCallBack callback) {
        this.callback = callback;
    }

    public void saveQrCode(Context context, String stringKey, String stringValue) {
        if (sharedPrefUtil.saveQRPreference(context, stringKey, stringValue)) {
            callback.onQRCodeSaved(true);
        } else {
            callback.onFailedQRCodeSave(stringUtil.getStringFromRes(context, R.string.common_message));
        }
    }


    public interface QRCodeCallBack {
        void onQRCodeSaved(boolean isSaved);

        void onFailedQRCodeSave(String errorMessage);
    }
}
