package com.baibai.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baibai.R;
import com.baibai.tools.LoginCacheUtils;
import com.baibai.tools.RequestUrl;

import org.json.JSONException;
import org.json.JSONObject;

public class ModifyUserInfoActivity extends BaseActivity {
    private static final String TAG = "ModifyUserInfoActivity";
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

        setRightBtnVisible(View.GONE);
        setLeftText("");
        mUserinfo = (EditText) findViewById(R.id.modify_et_userinfo);
        mBtnSummit = (Button) findViewById(R.id.modify_btn_summit);
    }

    public void processModify() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userCollege", "");
            jsonObject.put("homeTown", "");
            jsonObject.put("userBirth", "");
            if (extra == MODIFY_TYPE_NICKNAME) {
                jsonObject.put("nickName", mUserinfo.getText().toString());
                jsonObject.put("userGendar", LoginCacheUtils.getGender());
            } else if (extra == MODIFY_TYPE_SEX) {
                jsonObject.put("nickName", LoginCacheUtils.nickName);
                jsonObject.put("userGendar", LoginCacheUtils.getGender());
            } else {

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.USERREGISTER, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e(TAG, response.toString() + "  " + response.optString("data") + "  " + response.optString("result"));
                if (response.optString("result").equals("false")) {
                    Toast.makeText(ModifyUserInfoActivity.this, response.optString("error"), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ModifyUserInfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        getBaseRequest().add(jsonObjectRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_tv_left:
                finish();
                break;
            case R.id.modify_btn_summit:
                processModify();
                break;
        }
    }
}
