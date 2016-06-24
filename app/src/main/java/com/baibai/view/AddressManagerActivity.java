package com.baibai.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

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

public class AddressManagerActivity extends BaseActivity {
    private Button mBtnAddNewAddress;
    private ListView mLvDefaultAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);
        initView();
    }

    private void initView() {
        setRightBtnVisible(View.GONE);
        setLeftBtnOnclick();
        setLeftText("");
        setCenterText(R.string.title_activity_address_manager);
        mBtnAddNewAddress = (Button) findViewById(R.id.address_manager_btn);
        mBtnAddNewAddress.setOnClickListener(this);
        mLvDefaultAddress = (ListView) findViewById(R.id.address_manager_lv);
        DefaultAddressAdapter addressAdapter = new DefaultAddressAdapter();
        mLvDefaultAddress.setAdapter(addressAdapter);
        processGetAddress();
    }

    public void processGetAddress() {
        final JSONObject jsonObject = new JSONObject();
        Logger.e(this, "" + RequestUrl.GETUSERSCORE + LoginCacheUtils.TOKEN);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.GETADDRLIST + LoginCacheUtils.TOKEN, jsonObject, new Response.Listener<JSONObject>() {
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

    public void processAddAddress() {
        final JSONObject jsonObject = new JSONObject();
        Logger.e(this, "" + RequestUrl.GETUSERSCORE + LoginCacheUtils.TOKEN);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.GETADDRLIST + LoginCacheUtils.TOKEN, jsonObject, new Response.Listener<JSONObject>() {
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


    public void processDelAddress() {
        final JSONObject jsonObject = new JSONObject();
        Logger.e(this, "" + RequestUrl.GETUSERSCORE + LoginCacheUtils.TOKEN);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.GETADDRLIST + LoginCacheUtils.TOKEN, jsonObject, new Response.Listener<JSONObject>() {
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
            case R.id.address_manager_btn:
                startActivity(new Intent(AddressManagerActivity.this, AddNewAddressActivity.class));
                break;
            case R.id.title_tv_left:
                finish();
                break;
        }
    }

    class DefaultAddressAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_address_manager, null);
                convertView.setTag(viewHolder);
                viewHolder.username = (TextView) convertView.findViewById(R.id.address_manager_tv_username);
                viewHolder.phone = (TextView) convertView.findViewById(R.id.address_manager_tv_telphone);
                viewHolder.address = (TextView) convertView.findViewById(R.id.address_manager_tv_address);
                viewHolder.isDefault = (CheckBox) convertView.findViewById(R.id.address_manager_cb);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        class ViewHolder {
            TextView username;
            TextView phone;
            TextView address;
            CheckBox isDefault;
        }
    }
}
