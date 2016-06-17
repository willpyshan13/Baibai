package com.baibai.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baibai.R;

/**
 *
 */
public class ForgetPasswordActivity extends BaseActivity {

    private Button mBtnGetCode, mBtnNextSetp;
    private EditText mEtPhone, mEtCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initView();
    }

    private void initView() {
        setRightBtnVisible(View.GONE);
        setLeftBtnOnclick();
        setCenterText(R.string.get_password);

        mBtnGetCode = (Button) findViewById(R.id.fotget_btn_getcode);
        mBtnNextSetp = (Button) findViewById(R.id.fotget_btn_next);

        mEtPhone = (EditText) findViewById(R.id.fotget_et_phone_num);
        mEtCode = (EditText) findViewById(R.id.fotget_et_msmcode);

    }
}
