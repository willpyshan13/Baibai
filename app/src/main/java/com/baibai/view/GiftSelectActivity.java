package com.baibai.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baibai.R;

public class GiftSelectActivity extends BaseActivity {
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gift);

        initView();
    }

    private void initView() {
        setLeftBtnOnclick();
        setLeftText("");
        setCenterText(R.string.title_activity_select_gift);

        mGridView = (GridView) findViewById(R.id.select_gift_gv);
        GiftSelectAdapter adapter = new GiftSelectAdapter();
        mGridView.setAdapter(adapter);
    }

    class GiftSelectAdapter extends BaseAdapter {

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
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_gift_select,null);
                holder.point = (TextView) convertView.findViewById(R.id.item_gift_select_tv_point);
                holder.cb = (CheckBox) convertView.findViewById(R.id.item_gift_select_cb);
                holder.icon = (ImageView) convertView.findViewById(R.id.item_gift_select_iv_head);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        class ViewHolder{
            TextView point;
            CheckBox cb;
            ImageView icon;
        }
    }
}
