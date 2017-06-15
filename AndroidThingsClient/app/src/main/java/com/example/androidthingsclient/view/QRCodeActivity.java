package com.example.androidthingsclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.androidthingsclient.MainActivity;
import com.example.androidthingsclient.R;
import com.example.androidthingsclient.util.SharedPrefUtil;
import com.example.androidthingsclient.util.StringUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QRCodeActivity extends AppCompatActivity {

    @BindView(R.id.lottie_animation_view)
    LottieAnimationView lottieAnimationView;
    @BindView(R.id.buttonScan)
    Button buttonScan;

    private StringUtil stringUtil;
    private SharedPrefUtil sharedPrefUtil;

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

        stringUtil = new StringUtil(); // TODO Dependency injection
        sharedPrefUtil = new SharedPrefUtil(); // TODO Dependency injection
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(this, stringUtil.getStringFromRes(this, R.string.QR_error_message), Toast.LENGTH_SHORT).show();
            } else {
                sharedPrefUtil.saveQRPreference(this, SharedPrefUtil.QR_CODE_ID_KEY_SHARED_PREF, intentResult.getContents());
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
