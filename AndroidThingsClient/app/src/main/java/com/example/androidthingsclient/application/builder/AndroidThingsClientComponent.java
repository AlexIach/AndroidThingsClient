package com.example.androidthingsclient.application.builder;

import android.content.Context;

import com.example.androidthingsclient.util.SharedPrefUtil;
import com.example.androidthingsclient.util.StringUtil;

import dagger.Component;

/**
 * Created by aiachimov on 6/15/17.
 */


@AndroidThingsClientScope
@Component(modules = {AndroidThingsClientModule.class})
public interface AndroidThingsClientComponent {

    Context context();

    StringUtil stringUtil();

    SharedPrefUtil sharedPrefUtil();

}
