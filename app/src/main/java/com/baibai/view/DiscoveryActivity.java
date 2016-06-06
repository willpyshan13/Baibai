package com.baibai.view;

import com.baibai.R;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @Comments : TODO(用一句话描述该文件做什么)
 * @author will
 * @CreateDate : 2016年6月1日 上午9:18:33
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月1日 上午9:18:33
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class DiscoveryActivity extends BaseActivity {
	private static final String TAG = "baibai_DiscoveryActivity";
	private GridView mStoreGv, mTypeGv;
	private int[] mStoreCollect = { R.drawable.carrefour, R.drawable.tesco, R.drawable.vanguard, R.drawable.carrefour,
			R.drawable.tesco, R.drawable.vanguard };
	private int[] mTypeCollect = { R.drawable.icon_discovery_drink, R.drawable.icon_discovery_food,
			R.drawable.icon_discovery_hair, R.drawable.icon_discovery_hourse, R.drawable.icon_discovery_drink,
			R.drawable.icon_discovery_food, R.drawable.icon_discovery_hair, R.drawable.icon_discovery_hourse,
			R.drawable.icon_discovery_drink, R.drawable.icon_discovery_food, R.drawable.icon_discovery_hair,
			R.drawable.icon_discovery_hourse };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discovery);

		initView();
	}

	private void initView() {
		mStoreGv = (GridView) findViewById(R.id.discovery_store_gv);
		StoreAdapter storeAdapter = new StoreAdapter();
		mStoreGv.setAdapter(storeAdapter);
		mTypeGv = (GridView) findViewById(R.id.discovery_type_gv);
		TypeAdapter typeAdapter = new TypeAdapter();
		mTypeGv.setAdapter(typeAdapter);
	}

	class StoreAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mStoreCollect.length;
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
			holder.storeIcon.setImageResource(mStoreCollect[position]);
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
			return mTypeCollect.length;
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
			holder.typeIcon.setImageResource(mTypeCollect[position]);

			return convertView;
		}

		class ViewHolder {
			ImageView typeIcon;
			TextView typeTitle;
		}

	}
}
