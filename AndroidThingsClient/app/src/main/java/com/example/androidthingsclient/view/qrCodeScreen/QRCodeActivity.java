package com.example.androidthingsclient.view.qrCodeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.androidthingsclient.Injector;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.util.ScreenNavigationManager;
import com.example.androidthingsclient.util.SharedPrefUtil;
import com.example.androidthingsclient.util.StringUtil;
import com.example.androidthingsclient.view.mainScreen.MainActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QRCodeActivity extends AppCompatActivity {

    @BindView(R.id.lottie_animation_view)
    LottieAnimationView lottieAnimationView;
    @BindView(R.id.buttonScan)
    Button buttonScan;

    @Inject
    StringUtil stringUtil;
    @Inject
    SharedPrefUtil sharedPrefUtil;
    @Inject
    ScreenNavigationManager screenNavigationManager;

    @OnClick(R.id.buttonScan)
    public void scanQRCode(View view) {
        // TODO scan QR Code
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt(stringUtil.getStringFromRes(this, R.string.QR_help_message));
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(false);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);

        Injector.INSTANCE.initQRCodeComponent(this);
        Injector.INSTANCE.getQrCodeActivityComponent().inject(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(this, stringUtil.getStringFromRes(this, R.string.QR_error_message), Toast.LENGTH_SHORT).show();
            } else {
                if (sharedPrefUtil.saveQRPreference(this, SharedPrefUtil.QR_CODE_ID_KEY_SHARED_PREF, intentResult.getContents())) {
                    screenNavigationManager.switchActivity(this, MainActivity.class);
                    finish();
                } else {
                    Toast.makeText(this, stringUtil.getStringFromRes(this, R.string.common_message), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
