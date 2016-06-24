package com.baibai.view;

import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baibai.R;
import com.baibai.tools.Logger;
import com.baibai.tools.RequestUrl;

import org.json.JSONException;
import org.json.JSONObject;

public class GoodsDetailActivity extends BaseActivity {
    private static final String TAG = "GoodsDetailActivity";
    public static String extraGoodeId = "extra_goods_id";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private String goodsId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);

        initView();
    }

    private void initView() {
        setRightText(R.string.into_shop);
        setLeftText("");
        setRightBtnOnclick();
        setLeftBtnOnclick();
        setCenterText("");
        goodsId = getIntent().getExtras().getString(extraGoodeId);
        processGetGoodsDetail();
    }

    public void processGetGoodsDetail() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("goodsId", goodsId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.GETGOODSDETAIL, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.e(this, response.optString("data") + "  " + response.optString("result"));
                        try {
                            JSONObject jsonObject1 = new JSONObject(response.optString("data"));
                            imageLoader.displayImage(jsonObject1.optString("imgDesc"), (ImageView) findViewById(R.id.goods_detail_iv));
                            setCenterText(jsonObject1.optString("marketName"));
                            ((TextView) findViewById(R.id.goode_detail_tv_name)).setText(jsonObject1.optString("goodsName"));
                            ((TextView) findViewById(R.id.goode_detail_tv_price)).setText(jsonObject1.optString("mallPrice") + "元");
                            ((TextView) findViewById(R.id.goode_detail_tv_pre_price)).setText(jsonObject1.optString("marketPrice") + "元");
                            ((TextView) findViewById(R.id.goode_detail_tv_pre_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                            ((TextView) findViewById(R.id.goode_detail_tv_stockcount)).setText(jsonObject1.optString("stockCount"));
//                            ((TextView)findViewById(R.id.goode_detail_tv_dicount_time)).setText(jsonObject1.optString("goodsName"));
                            ((TextView) findViewById(R.id.goode_detail_tv_commount)).setText("商品评价(" + jsonObject1.optString("soldCount") + ")");
//                            ((TextView)findViewById(R.id.goode_detail_tv_package_type)).setText(jsonObject1.optString("goodsName"));
//                            ((TextView)findViewById(R.id.goode_detail_tv_band)).setText(jsonObject1.optString("goodsName"));
//                            ((TextView)findViewById(R.id.goode_detail_tv_type)).setText(jsonObject1.optString("goodsName"));
//                            ((TextView)findViewById(R.id.goode_detail_tv_smale)).setText(jsonObject1.optString("goodsName"));
//                            ((TextView)findViewById(R.id.goode_detail_tv_sale_package)).setText(jsonObject1.optString("goodsName"));
//                            ((TextView)findViewById(R.id.goode_detail_tv_piece)).setText(jsonObject1.optString("goodsName"));
//                            ((TextView)findViewById(R.id.goode_detail_tv_size)).setText(jsonObject1.optString("goodsName"));
//                            ((TextView)findViewById(R.id.goode_detail_tv_layer)).setText(jsonObject1.optString("goodsName"));

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
        });

    }

}
