package com.baibai.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baibai.R;

/**
 * @Comments : TODO(用一句话描述该文件做什么) 
 * @author will
 * @CreateDate : 2016年6月2日 上午11:00:04
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月2日 上午11:00:04
 * @Modified:
 * TODO(用一句话描述该文件做什么) 
 */
public class LocationStoreActivity extends BaseActivity {
	private static final String TAG = "baibai_LocationStoreActivity";

	private ListView mStoreLv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_current_store);
		initView();
	}

	private void initView() {
		mStoreLv = (ListView) findViewById(R.id.current_store_lv);
		StoreAdapter adapter = new StoreAdapter();
		mStoreLv.setAdapter(adapter);
	}

	class StoreAdapter extends BaseAdapter{

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
			if (convertView == null){
				convertView = getLayoutInflater().inflate(R.layout.item_current_store,null);
				holder = new ViewHolder();
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			return convertView;
		}

		class ViewHolder{
			ImageView storeIcon;
			TextView storeName;
			TextView storeDistance;

		}
	}
}
