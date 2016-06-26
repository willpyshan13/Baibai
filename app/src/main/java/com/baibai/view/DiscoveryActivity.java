package com.baibai.view;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baibai.R;
import com.baibai.bean.DiscoveryBean;
import com.baibai.bean.DiscoveryReturn;
import com.baibai.bean.DiscoveryTypeBean;
import com.baibai.bean.DiscoveryTypeReturn;
import com.baibai.bean.MarketInfoReturn;
import com.baibai.tools.Logger;
import com.baibai.tools.RequestUrl;
import com.google.gson.Gson;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author will
 * @Comments : TODO(用一句话描述该文件做什么)
 * @CreateDate : 2016年6月1日 上午9:18:33
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月1日 上午9:18:33
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class DiscoveryActivity extends BaseActivity {
    private static final String TAG = "baibai_DiscoveryActivity";
    private GridView mStoreGv, mTypeGv;
    private List<DiscoveryBean> mDiscoveryList;
    private List<DiscoveryTypeBean> mDiscoveryTypeList;
    private StoreAdapter mStoreAdapter;
    private TypeAdapter typeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);

        initView();
    }

    public void processGetMarkeyList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("rcdSize", 6);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.GETMARKETLIST, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Logger.e(DiscoveryActivity.this, response.toString());
                if (response.optString("result").equals("true")) {
                    Gson gson = new Gson();
                    DiscoveryReturn info = gson.fromJson(response.toString(), DiscoveryReturn.class);
                    mDiscoveryList = new ArrayList<DiscoveryBean>();
                    mDiscoveryList.addAll(info.data);
                    mStoreAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        getBaseRequest().add(jsonObjectRequest);
    }


    public void processgetGoodsClazzList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("rcdSize", 6);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.GETGOODSCLAZZLIST, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Logger.e(DiscoveryActivity.this, response.toString());
                if (response.optString("result").equals("true")) {
                    Gson gson = new Gson();
                    DiscoveryTypeReturn info = gson.fromJson(response.toString(), DiscoveryTypeReturn.class);
                    mDiscoveryTypeList = new ArrayList<DiscoveryTypeBean>();
                    mDiscoveryTypeList.addAll(info.data);
                    typeAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        getBaseRequest().add(jsonObjectRequest);
    }


    private void initView() {
        mStoreGv = (GridView) findViewById(R.id.discovery_store_gv);
        mStoreAdapter = new StoreAdapter();
        mStoreGv.setAdapter(mStoreAdapter);
        mTypeGv = (GridView) findViewById(R.id.discovery_type_gv);
        typeAdapter = new TypeAdapter();
        mTypeGv.setAdapter(typeAdapter);

        processGetMarkeyList();
        processgetGoodsClazzList();
    }

    class StoreAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (mDiscoveryList != null)
                return mDiscoveryList.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_discovery_store, null);
                holder.storeIcon = (ImageView) convertView.findViewById(R.id.discovery_iv_head);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            imageLoader.displayImage(mDiscoveryList.get(position).marketLogo, holder.storeIcon);

            return convertView;
        }

        class ViewHolder {
            ImageView storeIcon;
        }

    }

    class TypeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (mDiscoveryTypeList != null)
                return mDiscoveryTypeList.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_discovery_type, null);
                holder.typeIcon = (ImageView) convertView.findViewById(R.id.discovery_type_iv);
                holder.typeTitle = (TextView) convertView.findViewById(R.id.discovery_type_tv_title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            imageLoader.displayImage(mDiscoveryTypeList.get(position).clazzImg, holder.typeIcon);
            holder.typeTitle.setText(mDiscoveryTypeList.get(position).clazzName);
            return convertView;
        }

        class ViewHolder {
            ImageView typeIcon;
            TextView typeTitle;
        }

    }
}
