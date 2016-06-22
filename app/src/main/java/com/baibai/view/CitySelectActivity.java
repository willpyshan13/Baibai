package com.baibai.view;

import android.os.Bundle;
import android.view.View;

import com.baibai.R;
import com.baibai.adapter.IndexableRecyclerViewAdapter;
import com.baibai.adapter.ItemModel;
import com.baibai.widget.IndexableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CitySelectActivity extends BaseActivity {
    private IndexableRecyclerView mIndexableRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select);

        initViews();
        initData();
    }


    private void initViews() {
        setRightBtnVisible(View.GONE);
        setLeftBtnOnclick();
        setLeftText("");
        setCenterText(getResources().getString(R.string.current_city) + "-厦门市");
        mIndexableRecyclerView = (IndexableRecyclerView) findViewById(R.id.indexable_recyclerview);
    }

    private void initData() {
        List<ItemModel> models = new ArrayList<>();
        models.add(new ItemModel("国睿科技", R.mipmap.ic_launcher));
        models.add(new ItemModel("海格雷", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒华", R.mipmap.ic_launcher));
        models.add(new ItemModel("海的", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒瑞", R.mipmap.ic_launcher));
        models.add(new ItemModel("皖中", R.mipmap.ic_launcher));
        models.add(new ItemModel("东昕", R.mipmap.ic_launcher));
        models.add(new ItemModel("真武", R.mipmap.ic_launcher));
        models.add(new ItemModel("日生", R.mipmap.ic_launcher));
        models.add(new ItemModel("化陶", R.mipmap.ic_launcher));
        models.add(new ItemModel("玉泉", R.mipmap.ic_launcher));
        models.add(new ItemModel("老田", R.mipmap.ic_launcher));
        models.add(new ItemModel("女孩", R.mipmap.ic_launcher));
        models.add(new ItemModel("国睿科技", R.mipmap.ic_launcher));
        models.add(new ItemModel("海格雷", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒华", R.mipmap.ic_launcher));
        models.add(new ItemModel("海的", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒瑞", R.mipmap.ic_launcher));
        models.add(new ItemModel("佳美", R.mipmap.ic_launcher));
        models.add(new ItemModel("皖中", R.mipmap.ic_launcher));
        models.add(new ItemModel("东昕", R.mipmap.ic_launcher));
        models.add(new ItemModel("真武", R.mipmap.ic_launcher));
        models.add(new ItemModel("日生", R.mipmap.ic_launcher));
        models.add(new ItemModel("化陶", R.mipmap.ic_launcher));
        models.add(new ItemModel("玉泉", R.mipmap.ic_launcher));
        models.add(new ItemModel("老田", R.mipmap.ic_launcher));
        models.add(new ItemModel("女孩", R.mipmap.ic_launcher));
        models.add(new ItemModel("国睿科技", R.mipmap.ic_launcher));
        models.add(new ItemModel("海格雷", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒华", R.mipmap.ic_launcher));
        models.add(new ItemModel("海的", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒瑞", R.mipmap.ic_launcher));
        models.add(new ItemModel("佳美", R.mipmap.ic_launcher));
        models.add(new ItemModel("皖中", R.mipmap.ic_launcher));
        models.add(new ItemModel("安徽", R.mipmap.ic_launcher));
        models.add(new ItemModel("阿杜", R.mipmap.ic_launcher));
        models.add(new ItemModel("按掉的", R.mipmap.ic_launcher));
        models.add(new ItemModel("安慰我", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒华", R.mipmap.ic_launcher));
        models.add(new ItemModel("海的", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒瑞", R.mipmap.ic_launcher));
        models.add(new ItemModel("佳美", R.mipmap.ic_launcher));
        models.add(new ItemModel("皖中", R.mipmap.ic_launcher));
        models.add(new ItemModel("东昕", R.mipmap.ic_launcher));
        models.add(new ItemModel("真武", R.mipmap.ic_launcher));
        models.add(new ItemModel("日生", R.mipmap.ic_launcher));
        models.add(new ItemModel("化陶", R.mipmap.ic_launcher));
        models.add(new ItemModel("版本", R.mipmap.ic_launcher));
        models.add(new ItemModel("拜拜", R.mipmap.ic_launcher));
        models.add(new ItemModel("播报", R.mipmap.ic_launcher));
        models.add(new ItemModel("国睿科技", R.mipmap.ic_launcher));
        models.add(new ItemModel("海格雷", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒华", R.mipmap.ic_launcher));
        models.add(new ItemModel("海的", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒瑞", R.mipmap.ic_launcher));
        models.add(new ItemModel("皖中", R.mipmap.ic_launcher));
        models.add(new ItemModel("东昕", R.mipmap.ic_launcher));
        models.add(new ItemModel("真武", R.mipmap.ic_launcher));
        models.add(new ItemModel("日生", R.mipmap.ic_launcher));
        models.add(new ItemModel("化陶", R.mipmap.ic_launcher));
        models.add(new ItemModel("玉泉", R.mipmap.ic_launcher));
        models.add(new ItemModel("老田", R.mipmap.ic_launcher));
        models.add(new ItemModel("女孩", R.mipmap.ic_launcher));
        models.add(new ItemModel("国睿科技", R.mipmap.ic_launcher));
        models.add(new ItemModel("海格雷", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒华", R.mipmap.ic_launcher));
        models.add(new ItemModel("海的", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒瑞", R.mipmap.ic_launcher));
        models.add(new ItemModel("佳美", R.mipmap.ic_launcher));
        models.add(new ItemModel("皖中", R.mipmap.ic_launcher));
        models.add(new ItemModel("东昕", R.mipmap.ic_launcher));
        models.add(new ItemModel("真武", R.mipmap.ic_launcher));
        models.add(new ItemModel("日生", R.mipmap.ic_launcher));
        models.add(new ItemModel("化陶", R.mipmap.ic_launcher));
        models.add(new ItemModel("玉泉", R.mipmap.ic_launcher));
        models.add(new ItemModel("老田", R.mipmap.ic_launcher));
        models.add(new ItemModel("女孩", R.mipmap.ic_launcher));
        models.add(new ItemModel("国睿科技", R.mipmap.ic_launcher));
        models.add(new ItemModel("海格雷", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒华", R.mipmap.ic_launcher));
        models.add(new ItemModel("海的", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒瑞", R.mipmap.ic_launcher));
        models.add(new ItemModel("佳美", R.mipmap.ic_launcher));
        models.add(new ItemModel("皖中", R.mipmap.ic_launcher));
        models.add(new ItemModel("安徽", R.mipmap.ic_launcher));
        models.add(new ItemModel("阿杜", R.mipmap.ic_launcher));
        models.add(new ItemModel("按掉的", R.mipmap.ic_launcher));
        models.add(new ItemModel("安慰我", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒华", R.mipmap.ic_launcher));
        models.add(new ItemModel("海的", R.mipmap.ic_launcher));
        models.add(new ItemModel("国睿科技", R.mipmap.ic_launcher));
        models.add(new ItemModel("海格雷", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒华", R.mipmap.ic_launcher));
        models.add(new ItemModel("海的", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒瑞", R.mipmap.ic_launcher));
        models.add(new ItemModel("皖中", R.mipmap.ic_launcher));
        models.add(new ItemModel("东昕", R.mipmap.ic_launcher));
        models.add(new ItemModel("真武", R.mipmap.ic_launcher));
        models.add(new ItemModel("日生", R.mipmap.ic_launcher));
        models.add(new ItemModel("化陶", R.mipmap.ic_launcher));
        models.add(new ItemModel("玉泉", R.mipmap.ic_launcher));
        models.add(new ItemModel("老田", R.mipmap.ic_launcher));
        models.add(new ItemModel("女孩", R.mipmap.ic_launcher));
        models.add(new ItemModel("国睿科技", R.mipmap.ic_launcher));
        models.add(new ItemModel("海格雷", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒华", R.mipmap.ic_launcher));
        models.add(new ItemModel("海的", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒瑞", R.mipmap.ic_launcher));
        models.add(new ItemModel("佳美", R.mipmap.ic_launcher));
        models.add(new ItemModel("皖中", R.mipmap.ic_launcher));
        models.add(new ItemModel("东昕", R.mipmap.ic_launcher));
        models.add(new ItemModel("真武", R.mipmap.ic_launcher));
        models.add(new ItemModel("日生", R.mipmap.ic_launcher));
        models.add(new ItemModel("化陶", R.mipmap.ic_launcher));
        models.add(new ItemModel("玉泉", R.mipmap.ic_launcher));
        models.add(new ItemModel("老田", R.mipmap.ic_launcher));
        models.add(new ItemModel("女孩", R.mipmap.ic_launcher));
        models.add(new ItemModel("国睿科技", R.mipmap.ic_launcher));
        models.add(new ItemModel("海格雷", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒华", R.mipmap.ic_launcher));
        models.add(new ItemModel("海的", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒瑞", R.mipmap.ic_launcher));
        models.add(new ItemModel("佳美", R.mipmap.ic_launcher));
        models.add(new ItemModel("皖中", R.mipmap.ic_launcher));
        models.add(new ItemModel("安徽", R.mipmap.ic_launcher));
        models.add(new ItemModel("阿杜", R.mipmap.ic_launcher));
        models.add(new ItemModel("按掉的", R.mipmap.ic_launcher));
        models.add(new ItemModel("安慰我", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒华", R.mipmap.ic_launcher));
        models.add(new ItemModel("海的", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒瑞", R.mipmap.ic_launcher));
        models.add(new ItemModel("佳美", R.mipmap.ic_launcher));
        models.add(new ItemModel("皖中", R.mipmap.ic_launcher));
        models.add(new ItemModel("东昕", R.mipmap.ic_launcher));
        models.add(new ItemModel("真武", R.mipmap.ic_launcher));
        models.add(new ItemModel("日生", R.mipmap.ic_launcher));
        models.add(new ItemModel("化陶", R.mipmap.ic_launcher));
        models.add(new ItemModel("版本", R.mipmap.ic_launcher));
        models.add(new ItemModel("拜拜", R.mipmap.ic_launcher));
        models.add(new ItemModel("播报", R.mipmap.ic_launcher));
        models.add(new ItemModel("恒瑞", R.mipmap.ic_launcher));
        models.add(new ItemModel("佳美", R.mipmap.ic_launcher));
        models.add(new ItemModel("皖中", R.mipmap.ic_launcher));
        models.add(new ItemModel("东昕", R.mipmap.ic_launcher));
        models.add(new ItemModel("真武", R.mipmap.ic_launcher));
        models.add(new ItemModel("日生", R.mipmap.ic_launcher));
        models.add(new ItemModel("化陶", R.mipmap.ic_launcher));
        models.add(new ItemModel("版本", R.mipmap.ic_launcher));
        models.add(new ItemModel("拜拜", R.mipmap.ic_launcher));
        models.add(new ItemModel("播报", R.mipmap.ic_launcher));
        IndexableRecyclerViewAdapter indexableRecyclerViewAdapter = new IndexableRecyclerViewAdapter(this, models);
        mIndexableRecyclerView.setAdapter(indexableRecyclerViewAdapter);

    }
}
