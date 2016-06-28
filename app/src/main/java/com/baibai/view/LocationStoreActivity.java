package com.baibai.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baibai.R;
import com.baibai.bean.MarketInfoReturn;
import com.baibai.tools.CommonConstans;
import com.baibai.tools.Logger;
import com.baibai.tools.LoginCacheUtils;
import com.baibai.tools.RequestUrl;
import com.baibai.tools.ScreenProperties;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author will
 * @Comments : TODO(用一句话描述该文件做什么)
 * @CreateDate : 2016年6月2日 上午11:00:04
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月2日 上午11:00:04
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class LocationStoreActivity extends BaseActivity {
    private static final String TAG = "baibai_LocationStoreActivity";

    public static final String EXTRA_LOCATION = "extra_location";
    private ListView mStoreLv;
    private String json;
    private MarketInfoReturn info;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_store);
        initView();
    }

    private void initView() {
        setRightBtnVisible(View.GONE);
        setLeftBtnOnclick();
        setLeftText("");
        setCenterText(R.string.current_store);
        json = getIntent().getExtras().getString(EXTRA_LOCATION);
        Gson gson = new Gson();
        info = gson.fromJson(json, MarketInfoReturn.class);
        mStoreLv = (ListView) findViewById(R.id.current_store_lv);
        StoreAdapter adapter = new StoreAdapter();
        mStoreLv.setAdapter(adapter);
    }

    public void processAddAttend(final String storeId) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("attendType", CommonConstans.COLLECT_TYPE_STORE + "");
                    jsonObject.put("recordId", storeId + "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.ADDATTEND + LoginCacheUtils.TOKEN, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.e(this, response.optString("data") + "  " + response.optString("result"));
                        if (response.optString("result").equals("true"))
                            Toast.makeText(LocationStoreActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                getBaseRequest().add(jsonObjectRequest);
            }
        });
    }

    class StoreAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (info != null && info.data != null)
                return info.data.size();
            else
                return 0;
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_current_store, null);
                holder = new ViewHolder();
                holder.storeIcon = (ImageView) convertView.findViewById(R.id.item_current_store);
                holder.storeName = (TextView) convertView.findViewById(R.id.item_current_tv_name);
                holder.storeDistance = (TextView) convertView.findViewById(R.id.item_current_tv_distance);
                holder.rating = (RatingBar) convertView.findViewById(R.id.ratingBar);
                holder.collect = (ImageView) convertView.findViewById(R.id.item_current_store_iv_collect);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.storeIcon.setLayoutParams(new LinearLayout.LayoutParams(ScreenProperties.getScreenWidth() / ScreenProperties.WIDTH_NUM, ScreenProperties.getScreenHeight() / ScreenProperties.HEIGHT_NUM));
            holder.storeName.setText(info.data.get(position).marketName);
            holder.storeDistance.setText(info.data.get(position).marketRange);
            holder.rating.setRating(Float.parseFloat(info.data.get(position).marketStar));
            imageLoader.displayImage(info.data.get(position).marketLogo, holder.storeIcon);
            holder.collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (LoginCacheUtils.isLogin())
                        processAddAttend(info.data.get(position).marketId);
                    else
                        Toast.makeText(LocationStoreActivity.this, "请先登录！", Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }

        class ViewHolder {
            ImageView storeIcon;
            TextView storeName;
            TextView storeDistance;
            RatingBar rating;
            ImageView collect;
        }
    }
}
