package com.baibai.view;

import com.baibai.R;
import com.baibai.R.id;
import com.baibai.R.layout;
import com.baibai.tools.LoginCacheUtils;
import com.baibai.tools.ScreenProperties;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity implements OnClickListener {
    private final String HOME = "home";

    private final String CURRENT_POSITION = "current_position";

    private final String COLLECT = "collect";

    private final String DISCOVERY = "discovery";

    private final String MINE = "mine";
    public TabHost tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initTabhost();

        startService(new Intent(this, IbeconService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (LoginCacheUtils.isLogin())
            ((TextView) findViewById(id.main_tv_point)).setText("  " + LoginCacheUtils.userScore);
    }

    private void initView() {
        ScreenProperties.initScreenProperties(this);
        findViewById(R.id.main_tv_search).setOnClickListener(this);
        findViewById(R.id.main_tv_location).setOnClickListener(this);
        findViewById(id.main_tv_point).setOnClickListener(this);
        findViewById(id.main_tab_home).setOnClickListener(this);
        findViewById(id.main_tab_current_position).setOnClickListener(this);
        findViewById(id.main_tab_collect).setOnClickListener(this);
        findViewById(id.main_tab_discovery).setOnClickListener(this);
        findViewById(id.main_tab_mine).setOnClickListener(this);
    }

    public void initTabhost() {
        addTab(HOME, "首页", HomeActivity.class);
        addTab(CURRENT_POSITION, "附近", CurrentPositionActivity.class);
        addTab(COLLECT, "收藏", CollectActivity.class);
        addTab(DISCOVERY, "发现", DiscoveryActivity.class);
        addTab(MINE, "我的", MineActivity.class);
        tab = getTabHost();
    }

    public void addTab(String tag, String indicator, Class<?> cls) {
        getTabHost().addTab(getTabHost().newTabSpec(tag).setIndicator(indicator).setContent(new Intent(this, cls)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case id.main_tv_point:
                if (LoginCacheUtils.isLogin()) {
                    Intent pointIntent = new Intent(MainActivity.this, CreditLifeActivity.class);
                    MainActivity.this.startActivity(pointIntent);
                } else {
                    startLoginActivity();
                }
                break;
            case id.main_tab_home:
                getTabHost().setCurrentTabByTag(HOME);
                break;
            case id.main_tab_current_position:
                getTabHost().setCurrentTabByTag(CURRENT_POSITION);
                break;
            case id.main_tab_collect:
                if (LoginCacheUtils.isLogin())
                    getTabHost().setCurrentTabByTag(COLLECT);
                else
                    startLoginActivity();
                break;
            case id.main_tab_discovery:
                getTabHost().setCurrentTabByTag(DISCOVERY);
                break;
            case id.main_tab_mine:
                if (LoginCacheUtils.isLogin())
                    getTabHost().setCurrentTabByTag(MINE);
                else
                    startLoginActivity();
                break;
            case R.id.main_tv_location:
                MainActivity.this.startActivity(new Intent(MainActivity.this, CitySelectActivity.class));
                break;
            case R.id.main_tv_search:
                MainActivity.this.startActivity(new Intent(MainActivity.this, SearchGoodsActivity.class));
                break;
        }

    }

    public void startLoginActivity() {
        Intent intent = new Intent(this, LoginOrRegisterActivity.class);
        startActivity(intent);
    }
}
