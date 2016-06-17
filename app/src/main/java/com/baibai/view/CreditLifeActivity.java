package com.baibai.view;

import com.baibai.R;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CreditLifeActivity extends BaseActivity {
    private TextView mTvPoint,mTvLess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_life);
        initView();
    }

    private void initView() {
        setLeftBtnOnclick();
        setLeftText("");
        setCenterText(R.string.point_life);
        mTvPoint = (TextView) findViewById(R.id.credit_tv_point);
        mTvLess = (TextView) findViewById(R.id.credit_tv_less);
        mTvLess.setText(Html.fromHtml("您还差<h1>1200</h1>/n积分可换购礼品"));
        findViewById(R.id.credit_rl_qiuck).setOnClickListener(this);
        findViewById(R.id.credit_rl_check_all).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_tv_left:
                break;
            case R.id.credit_rl_qiuck:
                break;
            case R.id.credit_rl_check_all:
                break;
        }
    }
}
