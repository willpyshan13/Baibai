package com.baibai.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baibai.R;
import com.baibai.tools.CommonConstans;
import com.baibai.tools.Logger;
import com.baibai.tools.LoginCacheUtils;
import com.baibai.tools.MD5Utils;
import com.baibai.tools.RequestUrl;
import com.baibai.tools.SharePreferenceUtil;
import com.mob.tools.utils.UIHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by will on 16/6/2.
 */
public class LoginActivity extends BaseActivity implements PlatformActionListener {
    private static final String TAG = "LoginActivity";

    private Button mLogin;
    private ImageButton mWechat, mQQ;
    private EditText mUsername, mPassword;
    private TextView mForgetPassword;
    private SharePreferenceUtil mPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        mPreference = new SharePreferenceUtil(this, CommonConstans.USERINFO);
        setRightBtnOnclick();
        setRightText(R.string.register);
        setLeftBtnOnclick();
        setLeftText("");
        setCenterText(R.string.register_title);
        mUsername = (EditText) findViewById(R.id.login_username);
        mPassword = (EditText) findViewById(R.id.login_password);
        mLogin = (Button) findViewById(R.id.login_btn_login);
        mForgetPassword = (TextView) findViewById(R.id.login_btn_forgetpassword);
//        mForgetPassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mWechat = (ImageButton) findViewById(R.id.login_btn_wechat);
        mQQ = (ImageButton) findViewById(R.id.login_btn_qq);

        mUsername.setText(mPreference.getUsername());
        mPassword.setText(mPreference.getPasswd());

        mLogin.setOnClickListener(this);
        mForgetPassword.setOnClickListener(this);
        mWechat.setOnClickListener(this);
        mQQ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_tv_left:
                finish();
                break;
            case R.id.title_tv_right:
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
                break;
            case R.id.login_btn_forgetpassword:
                Intent forgetIntent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                LoginActivity.this.startActivity(forgetIntent);
                break;
            case R.id.login_btn_qq:
                authorize(QQ.NAME);
                break;
            case R.id.login_btn_wechat:
                authorize(Wechat.NAME);
                break;
            case R.id.login_btn_login:
                if (mUsername.getText().toString().equals(""))
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                else if (mPassword.getText().toString().equals(""))
                    Toast.makeText(LoginActivity.this, "请输入用户密码", Toast.LENGTH_SHORT).show();
                else
                    processLogin();
                break;
        }
    }

    private void authorize(String plat) {
        Platform platform = ShareSDK.getPlatform(plat);
        if (platform == null) {
//            popupOthers();
            return;
        }
        //判断指定平台是否已经完成授权
//        if (plat.isAuthValid()) {
//            String userId = plat.getDb().getUserId();
//            if (userId != null) {
////                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
////                login(plat.getName(), userId, null);
//                return;
//            }
//        }
        platform.setPlatformActionListener(this);
        // true不使用SSO授权，false使用SSO授权
//        plat.SSOSetting(true);
        //获取用户资料
        platform.showUser(null);
    }

    public void processLogin() {
        final JSONObject jsonObject = new JSONObject();
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
                if (response.optString("result").equals("true")) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
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
                } else {
                    Toast.makeText(LoginActivity.this, response.optString("error"), Toast.LENGTH_SHORT).show();
                }

                LoginActivity.this.finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        getBaseRequest().add(jsonObjectRequest);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Logger.e(LoginActivity.class, hashMap.toString());
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {
        Logger.e(LoginActivity.class, "onCancel");
    }
}
