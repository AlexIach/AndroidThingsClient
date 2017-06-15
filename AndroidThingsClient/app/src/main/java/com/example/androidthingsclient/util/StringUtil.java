package com.example.androidthingsclient.util;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by aiachimov on 6/15/17.
 */

public class StringUtil {

    // TODO Dependency injection

    @Inject
    public StringUtil() {
    }

    public String getStringFromRes(Context context, int stringId) {
        return context.getResources().getString(stringId);
    }
}
