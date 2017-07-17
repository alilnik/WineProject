package com.inno.ilyadmt.wineproject.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.zxing.Result;
import com.inno.ilyadmt.wineproject.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by mjazz on 17.07.2017.
 */

public class QRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView scannerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_sample);
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("RESULTS");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void qrclick(View view) {
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}
