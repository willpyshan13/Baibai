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
import com.baibai.tools.CommonConstans;
import com.baibai.tools.Logger;
import com.baibai.tools.LoginCacheUtils;
import com.baibai.tools.RequestUrl;
import com.baibai.tools.ScreenProperties;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
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
public class CollectActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2 {
    private static final String TAG = "baibai_CollectActivity";
    private PullToRefreshListView mStoreLv;
    private GoodsAdapter adapter;
    private StoreAdapter mStoreAdapter;
    private List<CollectGoodsBean> collectGoodsBeanList;
    private List<CollectStoreBean> collectStoreBeanList;

    private Button mCollectGoods, mCollectStore;
    private int currentType = 1, currentPage = 1, currrentPageSize = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initView();
    }

    public void processGetAttendList(int page, int pageSize, final int type, final boolean reset) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("attendType", "" + type);
            jsonObject.put("pageIndex", page);
            jsonObject.put("pageSize", pageSize);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.GETATTENDPAGE + LoginCacheUtils.TOKEN, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Logger.e(CollectActivity.this, response.toString());
                if (mStoreLv.isRefreshing())
                    mStoreLv.onRefreshComplete();
                if (response.optString("result").equals("true")) {
                    if (type == CommonConstans.COLLECT_TYPE_STORE) {
                        CollectStoreReturn info = gson.fromJson(response.toString(), CollectStoreReturn.class);
                        collectStoreBeanList = new ArrayList<CollectStoreBean>();
                        if (reset) {
                            collectStoreBeanList.clear();
                            mStoreLv.setAdapter(mStoreAdapter);
                        }
                        collectStoreBeanList.addAll(info.data);
                        mStoreAdapter.notifyDataSetChanged();
                    } else {
                        CollectGoodsReturn info = gson.fromJson(response.toString(), CollectGoodsReturn.class);
                        collectGoodsBeanList = new ArrayList<CollectGoodsBean>();
                        if (reset) {
                            collectGoodsBeanList.clear();
                            mStoreLv.setAdapter(adapter);
                        }
                        collectGoodsBeanList.addAll(info.data);
                        adapter.notifyDataSetChanged();
                    }

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
        mCollectGoods = (Button) findViewById(R.id.collect_btn_goods);
        mCollectStore = (Button) findViewById(R.id.collect_btn_store);
        mCollectGoods.setOnClickListener(this);
        mCollectStore.setOnClickListener(this);
        mStoreLv = (PullToRefreshListView) findViewById(R.id.collect_lv);
        mStoreLv.setMode(PullToRefreshBase.Mode.BOTH);
        mStoreLv.setOnRefreshListener(this);
        mStoreAdapter = new StoreAdapter();
        adapter = new GoodsAdapter();
//        mStoreLv.setAdapter(adapter);
        mStoreLv.setAdapter(mStoreAdapter);
        processGetAttendList(currentPage, currrentPageSize, CommonConstans.COLLECT_TYPE_STORE, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collect_btn_goods:
                currentType = CommonConstans.COLLECT_TYPE_GOODS;
                currentPage = 1;
                processGetAttendList(currentPage, currrentPageSize, currentType, true);
                break;
            case R.id.collect_btn_store:
                currentPage = 1;
                currentType = CommonConstans.COLLECT_TYPE_STORE;
                processGetAttendList(currentPage, currrentPageSize, currentType, true);
                break;

        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        processGetAttendList(currentPage, currrentPageSize, currentType, true);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        currentPage++;
        processGetAttendList(currentPage, currrentPageSize, currentType, false);
    }

    class GoodsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (collectGoodsBeanList != null)
                return collectGoodsBeanList.size();
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
                convertView = getLayoutInflater().inflate(R.layout.item_collect_goods, null);
                holder.goodsIv = (ImageView) convertView.findViewById(R.id.collect_goods_iv_icon);
                holder.goodsprePrice = (TextView) convertView.findViewById(R.id.collect_goods_tv_preprice);
                holder.goodsPrice = (TextView) convertView.findViewById(R.id.item_collect_goods_price);
                holder.shopName = (TextView) convertView.findViewById(R.id.item_collect_goods_storename);
                holder.goodsName = (TextView) convertView.findViewById(R.id.item_collect_goods_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.goodsIv.setLayoutParams(new LinearLayout.LayoutParams(ScreenProperties.getScreenWidth() / ScreenProperties.WIDTH_NUM, ScreenProperties.getScreenHeight() / ScreenProperties.HEIGHT_NUM));
            holder.goodsprePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            imageLoader.displayImage(collectGoodsBeanList.get(position).goodsThumb, holder.goodsIv);
            holder.goodsName.setText(collectGoodsBeanList.get(position).goodsName);
            holder.shopName.setText(collectGoodsBeanList.get(position).marketName);
            holder.goodsPrice.setText("¥" + collectGoodsBeanList.get(position).mallPrice + "元");
            holder.goodsprePrice.setText("¥" + collectGoodsBeanList.get(position).marketPrice + "元");
            return convertView;
        }

        class ViewHolder {
            ImageView goodsIv;
            TextView shopName;
            TextView goodsName;
            TextView goodsPrice;
            TextView goodsprePrice;
        }

    }

    class StoreAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (collectStoreBeanList != null)
                return collectStoreBeanList.size();
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
                convertView = getLayoutInflater().inflate(R.layout.item_current_store, null);
                holder.goodsIv = (ImageView) convertView.findViewById(R.id.item_current_store);
                holder.shopName = (TextView) convertView.findViewById(R.id.item_current_tv_name);
                holder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
                holder.collect = (ImageButton) convertView.findViewById(R.id.item_current_store_iv_collect);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.goodsIv.setLayoutParams(new LinearLayout.LayoutParams(ScreenProperties.getScreenWidth() / ScreenProperties.WIDTH_NUM, ScreenProperties.getScreenHeight() / ScreenProperties.HEIGHT_NUM));
            imageLoader.displayImage(collectStoreBeanList.get(position).marketLogo, holder.goodsIv);
            holder.shopName.setText(collectStoreBeanList.get(position).marketName);
            holder.ratingBar.setRating(Float.parseFloat(collectStoreBeanList.get(position).marketStar));
            holder.collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return convertView;
        }

        class ViewHolder {
            ImageView goodsIv;
            TextView shopName;
            RatingBar ratingBar;
            ImageButton collect;
        }

    }
}
