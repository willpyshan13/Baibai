package com.baibai.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.baibai.R;

/**
 * @author will
 * @Comments : TODO(用一句话描述该文件做什么)
 * @CreateDate : 2016年6月2日 上午11:00:26
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月2日 上午11:00:26
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class PayActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "baibai_PayActivity";

    private TextView mOrderMoney, mOrderId;
    private CheckBox mCbAliapay, mCbWechatPay, mCbQQPay;
    private Button mPayConfirm;

    public static final String INTENT_EXTRA_ORDER_ID = "intent_extra_order_id";
    public static final String INTENT_EXTRA_ORDER_NUM = "intent_extra_order_num";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        initView();
    }

    private void initView() {
        mOrderId = (TextView) findViewById(R.id.pay_tv_order_id);
        mOrderMoney = (TextView) findViewById(R.id.pay_tv_order_money);

        mCbAliapay = (CheckBox) findViewById(R.id.pay_cb_alipay);
        mCbWechatPay = (CheckBox) findViewById(R.id.pay_cb_wechat);
        mCbQQPay = (CheckBox) findViewById(R.id.pay_cb_qqwallet);

        mPayConfirm = (Button) findViewById(R.id.pay_btn_confirm);
        mPayConfirm.setOnClickListener(this);

        mCbAliapay.setOnCheckedChangeListener(this);
        mCbWechatPay.setOnCheckedChangeListener(this);
        mCbQQPay.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.pay_cb_alipay:
                if (isChecked) {
                    mCbQQPay.setChecked(false);
                    mCbWechatPay.setChecked(false);
                }
                break;
            case R.id.pay_cb_wechat:
                if (isChecked) {
                    mCbQQPay.setChecked(false);
                    mCbAliapay.setChecked(false);
                }
                break;
            case R.id.pay_cb_qqwallet:
                if (isChecked) {
                    mCbAliapay.setChecked(false);
                    mCbWechatPay.setChecked(false);
                }
                break;
        }
    }
}
