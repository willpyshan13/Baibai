package com.baibai.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.baibai.R;
import com.baibai.bean.MarketInfoReturn;
import com.baibai.tools.ScreenProperties;
import com.google.gson.Gson;

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

    class StoreAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (info!=null&&info.data!=null)
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_current_store, null);
                holder = new ViewHolder();
                holder.storeIcon = (ImageView) convertView.findViewById(R.id.item_current_store);
                holder.storeName = (TextView) convertView.findViewById(R.id.item_current_tv_name);
                holder.storeDistance = (TextView) convertView.findViewById(R.id.item_current_tv_distance);
                holder.rating = (RatingBar) convertView.findViewById(R.id.ratingBar);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.storeIcon.setLayoutParams(new LinearLayout.LayoutParams(ScreenProperties.getScreenWidth() / 3, ScreenProperties.getScreenHeight() / 6));
            holder.storeName.setText(info.data.get(position).marketName);
            holder.rating.setRating(Float.parseFloat(info.data.get(position).marketStar));
            imageLoader.displayImage(info.data.get(position).marketLogo,holder.storeIcon);
            return convertView;
        }

        class ViewHolder {
            ImageView storeIcon;
            TextView storeName;
            TextView storeDistance;
            RatingBar rating;
        }
    }
}
