package com.example.androidthingsclient.util;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by aiachimov on 6/15/17.
 */

public class SharedPrefUtil {

    public static final String QR_CODE_ID_KEY_SHARED_PREF = "QRCodeId";

    // TODO Dependency injection

    @Inject
    public SharedPrefUtil() {
    }

    private static final String PREFS_NAME = "qr_code_preference";

    public boolean saveQRPreference(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public String getQRPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "");
    }
}
