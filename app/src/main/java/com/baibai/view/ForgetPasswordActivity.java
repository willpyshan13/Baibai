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
import com.baibai.tools.RequestUrl;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 */
public class ForgetPasswordActivity extends BaseActivity {
    private static final String TAG = "ForgetPasswordActivity";
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

        mBtnGetCode.setOnClickListener(this);
        mBtnNextSetp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fotget_btn_getcode:
                processGetPasscode();
                break;
            case R.id.fotget_btn_next:
                processResetPassword();
                break;
        }
    }

    public void processGetPasscode() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userPhone", mEtPhone.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.SYSTEMGETPHONECODE, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(ForgetPasswordActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                Log.e(TAG, response.toString() + "  " + response.optString("data") + "  " + response.optString("result"));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        getBaseRequest().add(jsonObjectRequest);
    }

    public void processResetPassword() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userPhone", mEtPhone.getText().toString());
//            jsonObject.put("userPwd", MD5Utils.GetMD5Code(mPassword.getText().toString()));
            jsonObject.put("identifyCode", "888888");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.USERREGISTER, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e(TAG, response.toString() + "  " + response.optString("data") + "  " + response.optString("result"));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        getBaseRequest().add(jsonObjectRequest);
    }
}
