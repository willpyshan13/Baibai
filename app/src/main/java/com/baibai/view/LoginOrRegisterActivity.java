package com.baibai.view;

import com.baibai.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author will
 * @Comments : TODO(用一句话描述该文件做什么)
 * @CreateDate : 2016年6月2日 下午2:03:53
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月2日 下午2:03:53
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class LoginOrRegisterActivity extends BaseActivity {
    private static final String TAG = "baibai_LoginOrRegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        init();
    }

    private void init() {
        findViewById(R.id.login_register_btn_login).setOnClickListener(this);
        findViewById(R.id.login_register_btn_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.login_register_btn_login:
                Intent loginIntent = new Intent(LoginOrRegisterActivity.this, LoginActivity.class);
                LoginOrRegisterActivity.this.startActivity(loginIntent);
                finish();
                break;
            case R.id.login_register_btn_register:
                Intent registerIntent = new Intent(LoginOrRegisterActivity.this, RegisterActivity.class);
                LoginOrRegisterActivity.this.startActivity(registerIntent);
                finish();
                break;
        }
    }
}
