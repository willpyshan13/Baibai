package com.baibai.view;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baibai.R;
import com.baibai.bean.GoodsBean;
import com.baibai.bean.GoodsListBean;
import com.baibai.tools.RequestUrl;
import com.baibai.tools.ScreenProperties;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author will
 * @Comments : TODO(用一句话描述该文件做什么)
 * @CreateDate : 2016年6月1日 上午9:17:44
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月1日 上午9:17:44
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class HomeActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener {
    private static final String TAG = "baibai_HomeActivity";
    private ListView mGoodsLv;
    private PullToRefreshScrollView mPulltoRefreshScorllView;
    private RollPagerView mRollViewPager;
    private PagerRecycleAdapter mAdapter;
    private GoodsAdapter adapter;
    private Button mBtnDistance, mBtnPrice, mBtnDiscount, mBtnSort;
    private String currentDistance = "", currentPrice = "", currentDiscount = "", CurrentSort = "", currentSelect = "";

    //a1表示销量降序、a2表示销量升序；b1表示价格降序、b2表示价格升序；c1表示折扣率降序、c2表示折扣率升序;
    public static final String a1 = "a1";
    public static final String a2 = "a2";
    public static final String b1 = "b1";
    public static final String b2 = "b2";
    public static final String c1 = "c1";
    public static final String c2 = "c2";
    private int currentIndex = 0;
    private int pageSize = 15;

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init() {
        processGetBanner();
        processGetTypesList();
        processGetGoodssList(currentIndex, pageSize, a1, false);

        mBtnDistance = (Button) findViewById(R.id.home_bt_sort_distance);
        mBtnPrice = (Button) findViewById(R.id.home_bt_sort_price);
        mBtnDiscount = (Button) findViewById(R.id.home_bt_sort_discount);
        mBtnSort = (Button) findViewById(R.id.home_bt_sort);
        mBtnDistance.setOnClickListener(this);
        mBtnPrice.setOnClickListener(this);
        mBtnDiscount.setOnClickListener(this);
        mBtnSort.setOnClickListener(this);

        mGoodsLv = (ListView) findViewById(R.id.home_lv);
        mGoodsLv.setOnItemClickListener(this);
        mPulltoRefreshScorllView = (PullToRefreshScrollView) findViewById(R.id.scrollView1);
        mPulltoRefreshScorllView.setMode(PullToRefreshBase.Mode.BOTH);
        mPulltoRefreshScorllView.setOnRefreshListener(this);
        adapter = new GoodsAdapter();
        mGoodsLv.setAdapter(adapter);
        initViewPagerBound();
        mRollViewPager = (RollPagerView) findViewById(R.id.roll_view_pager);
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(2000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        mAdapter = new PagerRecycleAdapter();
        //设置适配器
        mRollViewPager.setAdapter(mAdapter);
//        设置指示器（顺序依次）
//        自定义指示器图片
//        设置圆点指示器颜色
//        设置文字指示器
//        隐藏指示器
        mRollViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_bt_sort_price:
                mBtnDiscount.setTextColor(Color.BLACK);
                mBtnDistance.setTextColor(Color.BLACK);
                mBtnPrice.setTextColor(Color.WHITE);
                mBtnSort.setTextColor(Color.BLACK);
                if (currentPrice.equals(b1)) {
                    currentPrice = b2;
                } else {
                    currentPrice = b1;
                }
                currentSelect = currentPrice;
                currentIndex = 0;
                processGetGoodssList(currentIndex, pageSize, currentPrice, true);

                break;
            case R.id.home_bt_sort_distance:
                mBtnDiscount.setTextColor(Color.BLACK);
                mBtnDistance.setTextColor(Color.WHITE);
                mBtnPrice.setTextColor(Color.BLACK);
                mBtnSort.setTextColor(Color.BLACK);
                if (currentDistance.equals(a1)) {
                    currentDiscount = a2;
                } else {
                    currentDiscount = a1;
                }
                currentSelect = currentDiscount;
                currentIndex = 0;
                processGetGoodssList(currentIndex, pageSize, currentDistance, true);
                break;
            case R.id.home_bt_sort_discount:
                mBtnDiscount.setTextColor(Color.WHITE);
                mBtnDistance.setTextColor(Color.BLACK);
                mBtnPrice.setTextColor(Color.BLACK);
                mBtnSort.setTextColor(Color.BLACK);
                if (currentDiscount.equals(c1)) {
                    currentDiscount = c2;
                } else {
                    currentDiscount = c1;
                }
                currentSelect = currentDiscount;
                currentIndex = 0;
                processGetGoodssList(currentIndex, pageSize, currentDiscount, true);
                break;
            case R.id.home_bt_sort:
                break;
        }
    }

    private JSONArray mBannerJsonArray;
    private JSONArray mGoodsTypeArray;

    private List<GoodsBean> mGoodsList;

    public void processGetBanner() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                final JSONObject jsonObject = new JSONObject();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.GETBANNERLIST, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                Logger.e(this, response.toString() + "  " + response.optString("data") + "  " + response.optString("result"));
                        try {
                            mBannerJsonArray = new JSONArray(response.optString("data"));
                            mAdapter.notifyDataSetChanged();
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

    public void processGetTypesList() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                final JSONObject jsonObject = new JSONObject();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.GETCLAZZLIST, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                Logger.e(this, response.toString() + "  " + response.optString("data") + "  " + response.optString("result"));
                        try {
                            mGoodsTypeArray = new JSONArray(response.optString("data"));

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

    public void processGetGoodssList(final int index, final int size, final String sort, final boolean resetList) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("clazzId", "0");
                    jsonObject.put("keyword", "");
                    jsonObject.put("pageIndex", "" + index);
                    jsonObject.put("pageSize", "" + size);
                    jsonObject.put("sortType", sort);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, RequestUrl.GETGOODSPAGE, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                Logger.e(this, response.toString() + "  " + response.optString("data") + "  " + response.optString("result"));
                        Gson gson = new Gson();
                        GoodsListBean goodsListBean = gson.fromJson(response.optString("data"), GoodsListBean.class);
                        if (mGoodsList == null)
                            mGoodsList = new ArrayList<GoodsBean>();
                        if (resetList)
                            mGoodsList.clear();
                        mGoodsList.addAll(goodsListBean.dataList);
                        adapter.notifyDataSetChanged();

                        if (mPulltoRefreshScorllView.isRefreshing())
                            mPulltoRefreshScorllView.onRefreshComplete();
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

    private void initViewPagerBound() {
        // 先算出图片长在屏幕中占多少英寸
        float xInch = (ScreenProperties.getScreenWidth() / ScreenProperties
                .getXdpi());
        // 根据图片宽长比例算出高应该占多少英寸
        double yInch = xInch * (15 / 32.0);
        // 再根据Y轴方向上每英寸多少像素算出图片高应该有多少像素
        int viewPagerHeight = (int) (ScreenProperties.getXdpi() * yInch);
        RollPagerView viewPagerBound = (RollPagerView) findViewById(R.id.roll_view_pager);
        viewPagerBound.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, viewPagerHeight));
    }

    private boolean isRefresh = false;

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mPulltoRefreshScorllView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        processGetGoodssList(currentIndex++, pageSize, currentSelect, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(HomeActivity.this, GoodsDetailActivity.class).putExtra(GoodsDetailActivity.extraGoodeId, mGoodsList.get(position).goodsId));
    }

    private class PagerRecycleAdapter extends StaticPagerAdapter {

        @Override
        public View getView(ViewGroup container, final int position) {
            ImageView view = new ImageView(container.getContext());

            try {
                imageLoader.displayImage(mBannerJsonArray.getJSONObject(position).optString("bannerImg"), view);
                String goodsId = mBannerJsonArray.getJSONObject(position).optString("bannerImg");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, GoodsDetailActivity.class).putExtra(GoodsDetailActivity.extraGoodeId, ""));
                }
            });
            return view;
        }

        @Override
        public int getCount() {
            if (mBannerJsonArray == null) return 0;
            else return mBannerJsonArray.length();
        }
    }


    class GoodsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (mGoodsList != null)
                return mGoodsList.size();
            else {
                return 10;
            }

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
                convertView = getLayoutInflater().inflate(R.layout.item_home_goods, null);
                holder.goodsIv = (ImageView) convertView.findViewById(R.id.goods_detail_icon);
                holder.goodsPrice = (TextView) convertView.findViewById(R.id.home_goods_tv_disprice);
                holder.shopName = (TextView) convertView.findViewById(R.id.home_goods_tv_storename);
                holder.goodsName = (TextView) convertView.findViewById(R.id.home_goods_tv_goodsname);
                holder.goodsDiscount = (TextView) convertView.findViewById(R.id.home_goods_tv_discount);
                holder.goodsDistance = (TextView) convertView.findViewById(R.id.home_goods_tv_distance);
                holder.goodsprePrice = (TextView) convertView.findViewById(R.id.home_goods_tv_pre_price);
                holder.goodsContent = (TextView) convertView.findViewById(R.id.home_goods_tv_content);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.goodsIv.setLayoutParams(new LinearLayout.LayoutParams(ScreenProperties.getScreenWidth() / 4, ScreenProperties.getScreenHeight() / 6));
            if (mGoodsList != null) {
                imageLoader.displayImage(mGoodsList.get(position).goodsThumb, holder.goodsIv);
                holder.goodsPrice.setText("￥" + mGoodsList.get(position).mallPrice + "元");
                holder.shopName.setText(mGoodsList.get(position).marketName);
                holder.goodsName.setText(mGoodsList.get(position).goodsName);
                holder.goodsDiscount.setText((Float.parseFloat(mGoodsList.get(position).goodsDiscount) * 100) / 10 + " 折");
//                holder.goodsDistance.setText(mGoodsListArray.getJSONObject(position).optString("marketName"));
                holder.goodsprePrice.setText("￥" + mGoodsList.get(position).marketPrice + "元");
//                holder.goodsContent.setText(mGoodsListArray.getJSONObject(position).optString("marketName"));
            }
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
