package com.baibai.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baibai.R;

public class CustomServiceActivity extends BaseActivity {
    private ListView mCustomServiceLv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_service);
        initView();
    }

    public void initView(){
        setRightBtnVisible(View.GONE);
        setLeftBtnOnclick();
        setLeftText("");
        setCenterText(R.string.my_order);

        mCustomServiceLv = (ListView) findViewById(R.id.custom_service_lv);
        CustomServiceAdapter adapter = new CustomServiceAdapter();
        mCustomServiceLv.setAdapter(adapter);
    }

    class CustomServiceAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 5;
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
            if (convertView==null){
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_custom_service,null);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        class ViewHolder{
            TextView orderStore;
            TextView orderStatus;
            TextView storeDetail;
            TextView orderNum;
            TextView orderMoneye;
            TextView orderMoneyRefound;
            Button refoundBtn;
            ImageView orderIv;
        }
    }
}
