package com.baibai.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baibai.R;

public class TheMoneyGoingActivity extends BaseActivity {
    private TextView mTvMoneyGoing, mTvMoneyNum, mTvMoneyQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_money_going);

        initView();
    }

    private void initView() {
        setLeftText("");
        setRightBtnVisible(View.GONE);
        setLeftBtnOnclick();
        setCenterText(R.string.money_going);
    }
}
