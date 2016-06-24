package com.baibai.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.BaseAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baibai.R;
import com.baibai.tools.Logger;
import com.baibai.tools.LoginCacheUtils;
import com.baibai.tools.RequestUrl;

import org.json.JSONException;
import org.json.JSONObject;

public class AddNewAddressActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);
        initView();
    }

    private void initView() {
        setRightBtnOnclick();
        setRightText(R.string.save);
        setLeftBtnOnclick();
        setCenterText(R.string.title_activity_add_new_address);
    }

    public void processAddAddress() {
        final JSONObject jsonObject = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.ADDADDR + LoginCacheUtils.TOKEN, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Logger.e(this, response.toString() + "  " + response.optString("data") + "  " + response.optString("result"));
                try {
                    JSONObject jsonObject1 = new JSONObject(response.optString("data"));


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
            case R.id.title_tv_right:
                break;
            case R.id.add_new_tv_area_add:
                break;
            case R.id.add_new_tv_street_add:
                break;
        }
    }
}
