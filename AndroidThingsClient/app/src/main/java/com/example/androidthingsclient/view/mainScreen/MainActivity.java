package com.example.androidthingsclient.view.mainScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.androidthingsclient.Injector;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.util.ScreenNavigationManager;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ScreenNavigationManager screenNavigationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Injector.INSTANCE.initMainActivityComponent(this);
        Injector.INSTANCE.getMainActivityComponent().inject(this);

    }
}
