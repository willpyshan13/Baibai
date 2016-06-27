package com.baibai.view;

import com.baibai.R;
import com.baibai.tools.LoginCacheUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * @author will
 * @Comments : TODO(用一句话描述该文件做什么)
 * @CreateDate : 2016年6月1日 上午9:18:49
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月1日 上午9:18:49
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class MineActivity extends BaseActivity {
    private static final String TAG = "baibai_MineActivity";

    private TextView mTvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTvName.setText(LoginCacheUtils.nickName);

    }

    private void initView() {
        mTvName = (TextView) findViewById(R.id.mine_tv_username);
        findViewById(R.id.mine_rl_order_address).setOnClickListener(this);
        findViewById(R.id.mine_rl_favorite).setOnClickListener(this);
        findViewById(R.id.mine_rl_order).setOnClickListener(this);
        findViewById(R.id.mine_rl_shopping_car).setOnClickListener(this);
        findViewById(R.id.mine_rl_setting).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_rl_order_address:
                startActivity(new Intent(MineActivity.this, AddressManagerActivity.class));
                break;
            case R.id.mine_rl_favorite:
                break;
            case R.id.mine_rl_order:
                startActivity(new Intent(MineActivity.this, CustomServiceActivity.class));
                break;
            case R.id.mine_rl_shopping_car:
                startActivity(new Intent(MineActivity.this, ShoppingCarActivity.class));
                break;
            case R.id.mine_rl_setting:
                startActivity(new Intent(MineActivity.this, SettingActivity.class));
                break;
        }
    }
}
