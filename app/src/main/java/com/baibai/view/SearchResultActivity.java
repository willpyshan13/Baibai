package com.baibai.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baibai.R;

public class SearchResultActivity extends BaseActivity {
    private TextView mTvBack, mTvMore, mTvSort, mTvSaleFirst, mTvFilter;
    private ImageView mIvShowType;
    private EditText mEtKeyword;
    private ListView mLvSearchResult;
    private GridView mGvSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        initView();
    }

    private void initView() {
        mTvBack = (TextView) findViewById(R.id.search_result_tv_back);
        mTvMore = (TextView) findViewById(R.id.search_result_tv_more);
        mTvSort = (TextView) findViewById(R.id.search_result_tv_sort);
        mIvShowType = (ImageView) findViewById(R.id.search_result_iv_show_type);
        mTvSaleFirst = (TextView) findViewById(R.id.search_result_tv_sale_first);
        mTvFilter = (TextView) findViewById(R.id.search_result_tv_filter);
        mEtKeyword = (EditText) findViewById(R.id.search_result_et_keyword);

        mLvSearchResult = (ListView) findViewById(R.id.search_result_lv);
        mGvSearchResult = (GridView) findViewById(R.id.search_result_gv);
        ResultAdapter adapter = new ResultAdapter();
        mGvSearchResult.setAdapter(adapter);
    }

    class ResultAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
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
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_search_result_grid, null);
                holder.goodsIcon = (ImageView) convertView.findViewById(R.id.item_search_result_iv_icon);
                holder.goodsName = (TextView) convertView.findViewById(R.id.item_search_result_tv_name);
                holder.goodsPrice = (TextView) convertView.findViewById(R.id.item_search_result_tv_price);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        class ViewHolder {
            private ImageView goodsIcon;
            private TextView goodsName;
            private TextView goodsPrice;
        }
    }
}
