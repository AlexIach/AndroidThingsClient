package com.example.androidthingsproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}