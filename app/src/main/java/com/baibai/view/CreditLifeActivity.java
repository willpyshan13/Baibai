package com.baibai.view;

import com.baibai.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.TextView;

public class CreditLifeActivity extends BaseActivity {
    private TextView mTvPoint, mTvLess;
    private SpannableStringBuilder mSpannerString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_life);
        initView();
    }

    private void initView() {
        mSpannerString = new SpannableStringBuilder();
        mSpannerString.append("您还差1200\n积分可换购礼品");
        mSpannerString.setSpan(new AbsoluteSizeSpan(80), 3, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setRightBtnVisible(View.GONE);
        setLeftBtnOnclick();
        setLeftText("");
        setCenterText(R.string.point_life);
        mTvPoint = (TextView) findViewById(R.id.credit_tv_point);
        mTvLess = (TextView) findViewById(R.id.credit_tv_less);
        ((TextView) findViewById(R.id.credit_tv_question)).setText("?");
        mTvLess.setText(mSpannerString);
        findViewById(R.id.credit_rl_qiuck).setOnClickListener(this);
        findViewById(R.id.credit_rl_check_all).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_tv_left:
                finish();
                break;
            case R.id.credit_rl_qiuck:
                startActivity(new Intent(CreditLifeActivity.this, QuickGetPointActivity.class));
                break;
            case R.id.credit_rl_check_all:
                startActivity(new Intent(CreditLifeActivity.this, GiftListActivity.class));
                break;
        }
    }
}
