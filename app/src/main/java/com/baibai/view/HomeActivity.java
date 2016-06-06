package com.baibai.view;

import com.baibai.R;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @Comments : TODO(用一句话描述该文件做什么)
 * @author will
 * @CreateDate : 2016年6月1日 上午9:17:44
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月1日 上午9:17:44
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class HomeActivity extends BaseActivity {
	private static final String TAG = "baibai_HomeActivity";
	private ListView mGoodsLv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		init();
	}

	private void init() {
		mGoodsLv = (ListView) findViewById(R.id.home_lv);
		GoodsAdapter adapter = new GoodsAdapter();
		mGoodsLv.setAdapter(adapter);
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
				convertView = getLayoutInflater().inflate(R.layout.item_home_goods, null);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

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
