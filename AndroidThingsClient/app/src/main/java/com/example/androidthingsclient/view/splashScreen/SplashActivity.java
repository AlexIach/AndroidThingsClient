package com.example.androidthingsclient.view.splashScreen;

import android.content.Intent;

import com.badoo.mobile.util.WeakHandler;
import com.daimajia.androidanimations.library.Techniques;
import com.example.androidthingsclient.Injector;
import com.example.androidthingsclient.MainActivity;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.util.Constants;
import com.example.androidthingsclient.util.SharedPrefUtil;
import com.example.androidthingsclient.util.StringUtil;
import com.example.androidthingsclient.view.qrCodeScreen.QRCodeActivity;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import javax.inject.Inject;

public class SplashActivity extends AwesomeSplash {


    @Inject
    StringUtil stringUtil;
    @Inject
    SharedPrefUtil sharedPrefUtil;

    @Override
    public void initSplash(ConfigSplash configSplash) {
        Injector.INSTANCE.initSplashComponent(this);
        Injector.INSTANCE.getSplashActivityComponent().inject(this);
        initSplashScreen(configSplash);
    }

    @Override
    public void animationsFinished() {
        WeakHandler handler = new WeakHandler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPrefUtil.getQRPreference(getBaseContext(), SharedPrefUtil.QR_CODE_ID_KEY_SHARED_PREF).equals("")) {
                    Intent i = new Intent(getBaseContext(), QRCodeActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }, Constants.SPLASH_DELAY);
    }

    private void initSplashScreen(ConfigSplash configSplash) {
        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.PaleGreen); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(1000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Customize Path
        configSplash.setPathSplash(Constants.DROID_LOGO); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(1000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.White); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(1000);
        configSplash.setPathSplashFillColor(R.color.White); //path object filling color


        //Customize Title
        configSplash.setTitleSplash(getResources().getString(R.string.android_things));
        configSplash.setTitleTextColor(R.color.White);
        configSplash.setTitleTextSize(40f); //float value
        configSplash.setAnimTitleDuration(1000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
        configSplash.setTitleFont(stringUtil.getStringFromRes(this, R.string.volatire_font)); //provide string to your font located in assets/fonts/
    }
}
