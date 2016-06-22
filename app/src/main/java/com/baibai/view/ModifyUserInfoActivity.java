package com.baibai.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baibai.R;

public class ModifyUserInfoActivity extends BaseActivity {

    public static final String MODIFY_TYPE = "modify_type";
    public static final int MODIFY_TYPE_USERNAME = 1;
    public static final int MODIFY_TYPE_NICKNAME = 2;
    public static final int MODIFY_TYPE_SEX = 3;

    private EditText mUserinfo;
    private Button mBtnSummit;

    private int extra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user_info);

        initView();
    }

    private void initView() {
        extra = getIntent().getExtras().getInt(MODIFY_TYPE);
        setRightBtnVisible(View.GONE);
        if (extra == MODIFY_TYPE_NICKNAME) {
            setCenterText(R.string.common_nickname);
        } else if (extra == MODIFY_TYPE_SEX) {
            setCenterText(R.string.common_sex);
        } else {
            setCenterText(R.string.common_username);
        }

        setLeftText("");
        mUserinfo = (EditText) findViewById(R.id.modify_et_userinfo);
        mBtnSummit = (Button) findViewById(R.id.modify_btn_summit);
    }
}
