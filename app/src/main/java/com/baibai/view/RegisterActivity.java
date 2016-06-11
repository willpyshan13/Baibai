package com.baibai.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baibai.R;

import java.util.Timer;

/**
 * @author will
 * @Comments : TODO(用一句话描述该文件做什么)
 * @CreateDate : 2016年6月2日 上午10:52:38
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月2日 上午10:52:38
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class RegisterActivity extends BaseActivity {
    private static final String TAG = "baibai_RegisterActivity";
    private Button mGetPasscode, mRegister;
    private EditText mUsername, mPassword, mRePassword, mPasscode;
    private long countDown = 60 * 1000, tick = 1000;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        setRightBtnVisible(View.GONE);
        setCenterText(R.string.user_register);
        setLeftBtnOnclick();
        setLeftText(R.string.cancel);
        mGetPasscode = (Button) findViewById(R.id.register_btn_getpasscode);
        mRegister = (Button) findViewById(R.id.register_btn_getpasscode);
        mUsername = (EditText) findViewById(R.id.register_et_username);
        mPassword = (EditText) findViewById(R.id.register_et_password);
        mRePassword = (EditText) findViewById(R.id.register_et_repassword);
        mPasscode = (EditText) findViewById(R.id.register_et_passcord);

        mGetPasscode.setOnClickListener(this);
        mRegister.setOnClickListener(this);

        mUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    mGetPasscode.setEnabled(true);
                else
                    mGetPasscode.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mTimer = new Timer();

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
