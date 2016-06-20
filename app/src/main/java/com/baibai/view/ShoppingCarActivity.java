package com.baibai.view;

import android.os.Bundle;

import com.baibai.R;

/**
 * @author will
 * @Comments : TODO(用一句话描述该文件做什么)
 * @CreateDate : 2016年6月2日 上午10:55:54
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月2日 上午10:55:54
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class ShoppingCarActivity extends BaseActivity {
    private static final String TAG = "baibai_ShoppingCarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);

        initView();
    }

    private void initView() {
        setLeftBtnOnclick();
        setLeftText("");
        setCenterText(R.string.shopping_car);
    }
}
