package com.baibai.view;

import com.baibai.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        initView();
    }

    private void initView() {
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
