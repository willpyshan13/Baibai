package com.baibai.view;

import android.graphics.PointF;
import android.os.Bundle;
import android.widget.Toast;

import com.baibai.R;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

public class QRCodeViewActivity extends BaseActivity implements QRCodeReaderView.OnQRCodeReadListener {
    private QRCodeReaderView mydecoderview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_view);

        mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        mydecoderview.setOnQRCodeReadListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mydecoderview.getCameraManager().startPreview();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mydecoderview.getCameraManager().stopPreview();
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cameraNotFound() {

    }

    @Override
    public void QRCodeNotFoundOnCamImage() {

    }
}
