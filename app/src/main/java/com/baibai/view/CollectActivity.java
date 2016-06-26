package com.baibai.view;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baibai.R;
import com.baibai.bean.CollectGoodsBean;
import com.baibai.bean.CollectGoodsReturn;
import com.baibai.bean.CollectStoreBean;
import com.baibai.bean.CollectStoreReturn;
import com.baibai.bean.DiscoveryBean;
import com.baibai.bean.DiscoveryReturn;
import com.baibai.tools.CommonConstans;
import com.baibai.tools.Logger;
import com.baibai.tools.LoginCacheUtils;
import com.baibai.tools.RequestUrl;
import com.baibai.tools.ScreenProperties;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author will
 * @Comments : TODO(用一句话描述该文件做什么)
 * @CreateDate : 2016年6月1日 上午9:19:10
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月1日 上午9:19:10
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class CollectActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2{
    private static final String TAG = "baibai_CollectActivity";
    private PullToRefreshListView mStoreLv;
    private GoodsAdapter adapter;
    private List<CollectGoodsBean> collectGoodsBeanList;
    private List<CollectStoreBean> collectStoreBeanList;

    private String currentType = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initView();
    }

    public void processgetAddrList(final int type) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("attendType", "" + type);
            jsonObject.put("pageIndex", 0);
            jsonObject.put("pageSize", 15);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.GETADDRLIST + LoginCacheUtils.TOKEN, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Logger.e(CollectActivity.this, response.toString());
                if (response.optString("result").equals("true")) {
                    if (type == CommonConstans.COLLECT_TYPE_STORE) {
                        CollectStoreReturn info = gson.fromJson(response.toString(), CollectStoreReturn.class);
                        collectStoreBeanList = new ArrayList<CollectStoreBean>();
                        collectStoreBeanList.addAll(info.data);
                    } else {
                        CollectGoodsReturn info = gson.fromJson(response.toString(), CollectGoodsReturn.class);
                        collectGoodsBeanList = new ArrayList<CollectGoodsBean>();
                        collectGoodsBeanList.addAll(info.data);
                    }
                    adapter.notifyDataSetChanged();
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
        mStoreLv = (PullToRefreshListView) findViewById(R.id.collect_lv);
        mStoreLv.setMode(PullToRefreshBase.Mode.BOTH);
        mStoreLv.setOnRefreshListener(this);
        adapter = new GoodsAdapter();
        mStoreLv.setAdapter(adapter);

        processgetAddrList(CommonConstans.COLLECT_TYPE_STORE);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }

    class GoodsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 10;
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
                convertView = getLayoutInflater().inflate(R.layout.item_collect_goods, null);
                holder.goodsIv = (ImageView) convertView.findViewById(R.id.collect_goods_iv_icon);
                holder.goodsprePrice = (TextView) convertView.findViewById(R.id.collect_goods_tv_preprice);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.goodsIv.setLayoutParams(new LinearLayout.LayoutParams(ScreenProperties.getScreenWidth() / 3, ScreenProperties.getScreenHeight() / 6));
            holder.goodsprePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            return convertView;
        }

        class ViewHolder {
            ImageView goodsIv;
            TextView shopName;
            TextView goodsName;
            TextView goodsDiscount;
            TextView goodsDistance;
            TextView goodsPrice;
            TextView goodsprePrice;
            TextView goodsContent;
        }

    }
}
