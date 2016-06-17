package com.baibai.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.baibai.R;

public class AddressManagerActivity extends BaseActivity {
    private Button mBtnAddNewAddress;
    private ListView mLvDefaultAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);
        initView();
    }

    private void initView() {
        setLeftBtnOnclick();
        setLeftText("");
        setCenterText(R.string.title_activity_address_manager);
        mBtnAddNewAddress = (Button) findViewById(R.id.address_manager_btn);
        mBtnAddNewAddress.setOnClickListener(this);
        mLvDefaultAddress = (ListView) findViewById(R.id.address_manager_lv);
        DefaultAddressAdapter addressAdapter = new DefaultAddressAdapter();
        mLvDefaultAddress.setAdapter(addressAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address_manager_btn:
                break;
            case R.id.title_tv_left:
                finish();
                break;
        }
    }

    class DefaultAddressAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 3;
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
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_address_manager, null);
                convertView.setTag(viewHolder);
                viewHolder.username = (TextView) convertView.findViewById(R.id.address_manager_tv_username);
                viewHolder.phone = (TextView) convertView.findViewById(R.id.address_manager_tv_telphone);
                viewHolder.address = (TextView) convertView.findViewById(R.id.address_manager_tv_address);
                viewHolder.isDefault = (CheckBox) convertView.findViewById(R.id.address_manager_cb);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        class ViewHolder {
            TextView username;
            TextView phone;
            TextView address;
            CheckBox isDefault;
        }
    }
}
