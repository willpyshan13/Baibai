package com.baibai.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.baibai.tools.CommonConstans;
import com.baibai.tools.LoginCacheUtils;
import com.baibai.tools.MD5Utils;
import com.baibai.tools.RequestUrl;
import com.baibai.tools.SharePreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

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

    private SharePreferenceUtil mPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mPreference = new SharePreferenceUtil(this, CommonConstans.USERINFO);
        initView();
    }

    private void initView() {
        setRightBtnVisible(View.GONE);
        setCenterText(R.string.user_register);
        setLeftBtnOnclick();
        setLeftText(R.string.cancel);
        mGetPasscode = (Button) findViewById(R.id.register_btn_getpasscode);
        mRegister = (Button) findViewById(R.id.register_btn_register);
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

    public void processGetPasscode() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userPhone", mUsername.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.SYSTEMGETPHONECODE, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(RegisterActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                Log.e(TAG, response.toString() + "  " + response.optString("data") + "  " + response.optString("result"));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        getBaseRequest().add(jsonObjectRequest);
    }

    public void processRegister() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userPhone", mUsername.getText().toString());
            jsonObject.put("userPwd", MD5Utils.GetMD5Code(mPassword.getText().toString()));
            jsonObject.put("identifyCode", "888888");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.USERREGISTER, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e(TAG, response.toString() + "  " + response.optString("data") + "  " + response.optString("result"));
                if (response.optString("result").equals("false")) {
                    Toast.makeText(RegisterActivity.this, response.optString("error"), Toast.LENGTH_SHORT).show();
                } else {
                    processLogin();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        getBaseRequest().add(jsonObjectRequest);
    }

    public void processLogin() {
        LoginCacheUtils.TOKEN = "login";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userPhone", mUsername.getText().toString());
            jsonObject.put("userPwd", MD5Utils.GetMD5Code(mPassword.getText().toString()));
            jsonObject.put("deviceToken", "android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.USERLOGIN, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e(TAG, response.toString() + "  " + response.optString("data") + "  " + response.optString("result"));
                if (response.optString("result").equals("false")) {
                    Toast.makeText(RegisterActivity.this, response.optString("error"), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    mPreference.setUsername(mUsername.getText().toString());
                    mPreference.setPasswd(mPassword.getText().toString());

                    try {
                        JSONObject jsonObject1 = new JSONObject(response.optString("data"));
                        Log.e(TAG, jsonObject1.toString() + "  " + jsonObject1.optString("userToken"));
                        LoginCacheUtils.TOKEN = jsonObject1.optString("userToken");
                        LoginCacheUtils.nickName = jsonObject1.optString("nickName");
                        LoginCacheUtils.userPhone = jsonObject1.optString("userPhone");
                        LoginCacheUtils.userGender = jsonObject1.optString("userGender");
                        LoginCacheUtils.userScore = jsonObject1.optString("userScore");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    RegisterActivity.this.finish();
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
            case R.id.register_btn_getpasscode:
                if (mUsername.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else {
                    processGetPasscode();
                }
                break;
            case R.id.register_btn_register:
                if (mPassword.getText().toString().equals(mRePassword.getText().toString())) {
                    if (mUsername.getText().toString().equals("") || mPassword.getText().toString().equals("")) {
                        Toast.makeText(RegisterActivity.this, "请输入用户名或密码", Toast.LENGTH_SHORT).show();
                    } else {
                        processRegister();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "两次密码不一样，请重新输入", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.title_tv_left:
                finish();
                break;
        }
    }
}
