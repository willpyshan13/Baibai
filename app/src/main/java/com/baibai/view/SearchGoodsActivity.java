package com.baibai.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baibai.R;
import com.baibai.widget.FlowLayout;

public class SearchGoodsActivity extends BaseActivity {
    private String mNames[] = {
            "welcome","android","TextView",
            "apple","jamy","kobe bryant",
            "jordan","layout","viewgroup",
            "margin","padding","text",
            "name","type","search","logcat"
    };
    private FlowLayout mFlowLayoutHistory,mFlowLayoutHot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_search);

        initView();
    }

    private void initView() {
        mFlowLayoutHistory = (FlowLayout) findViewById(R.id.flowlayout_history);
        mFlowLayoutHot = (FlowLayout) findViewById(R.id.flowlayout_hot);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for(int i = 0; i < mNames.length; i ++){
            TextView view = new TextView(this);
            view.setText(mNames[i]);
            view.setTextColor(Color.WHITE);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.serach_text_bg));
            mFlowLayoutHistory.addView(view,lp);
        }

        for(int i = 0; i < mNames.length; i ++){
            TextView view = new TextView(this);
            view.setText(mNames[i]);
            view.setTextColor(Color.WHITE);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.serach_text_bg));
            mFlowLayoutHot.addView(view,lp);
        }
    }
}
