package com.baibai.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baibai.R;

public class QuickGetPointActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_point);
        initView();
    }

    private  void initView(){
        setRightBtnVisible(View.GONE);
        setLeftBtnOnclick();
        setCenterText(R.string.point_quick_earn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_tv_left:
                break;
        }
    }
}
