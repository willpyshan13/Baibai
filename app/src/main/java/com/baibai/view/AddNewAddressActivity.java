package com.baibai.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.BaseAdapter;

import com.baibai.R;

public class AddNewAddressActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);
        initView();
    }

    private void initView() {
        setRightBtnOnclick();
        setRightText(R.string.save);
        setLeftBtnOnclick();
        setCenterText(R.string.get_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_tv_right:
                break;
            case R.id.add_new_tv_area_add:
                break;
            case R.id.add_new_tv_street_add:
                break;
        }
    }
}
