package com.baibai.view;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baibai.R;
import com.baibai.tools.Logger;
import com.baibai.tools.LoginCacheUtils;
import com.baibai.tools.RequestUrl;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class CreditLifeActivity extends BaseActivity {
    private static final String TAG = "CreditLifeActivity";
    private TextView mTvPoint, mTvLess;
    private SpannableStringBuilder mSpannerString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_life);
        initView();
    }

    private void initView() {
        setRightBtnVisible(View.GONE);
        setLeftBtnOnclick();
        setLeftText("");
        setCenterText(R.string.point_life);
        mTvPoint = (TextView) findViewById(R.id.credit_tv_point);
        mTvLess = (TextView) findViewById(R.id.credit_tv_less);
        ((TextView) findViewById(R.id.credit_tv_question)).setText("?");
        mSpannerString = new SpannableStringBuilder();
        mSpannerString.append("您还差\n积分可换购礼品");
//        mSpannerString.setSpan(new AbsoluteSizeSpan(80), 3, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvLess.setText(mSpannerString);
        findViewById(R.id.credit_rl_qiuck).setOnClickListener(this);
        findViewById(R.id.credit_rl_check_all).setOnClickListener(this);

        processGetPasscode();
    }

    public void processGetPasscode() {
        final JSONObject jsonObject = new JSONObject();
        Logger.e(this,""+ RequestUrl.GETUSERSCORE + LoginCacheUtils.TOKEN);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.GETUSERSCORE + LoginCacheUtils.TOKEN, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Logger.e(this, response.toString() + "  " + response.optString("data") + "  " + response.optString("result"));
                try {
                    JSONObject jsonObject1 = new JSONObject(response.optString("data"));

                    String leftScore = jsonObject1.optString("leftScore");
                    LoginCacheUtils.userScore = jsonObject1.optString("userScore");
                    mTvPoint.setText(LoginCacheUtils.userScore+"");
                    mSpannerString = new SpannableStringBuilder();
                    mSpannerString.append("您还差" + leftScore + "\n积分可换购礼品");
                    mSpannerString.setSpan(new AbsoluteSizeSpan(80), 3, 3 + leftScore.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mTvLess.setText(mSpannerString);
                } catch (JSONException e) {
                    e.printStackTrace();
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
            case R.id.credit_rl_qiuck:
                startActivity(new Intent(CreditLifeActivity.this, QuickGetPointActivity.class));
                break;
            case R.id.credit_rl_check_all:
                startActivity(new Intent(CreditLifeActivity.this, GiftListActivity.class));
                break;
        }
    }
}
