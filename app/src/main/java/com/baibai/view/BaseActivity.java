package com.baibai.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baibai.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author will
 * @Comments : TODO(用一句话描述该文件做什么)
 * @CreateDate : 2016年6月1日 上午9:17:10
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月1日 上午9:17:10
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class BaseActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "baibai_BaseActivity";
    ImageLoader imageLoader;
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = ImageLoader.getInstance();
    }

    public void setLeftBtnVisible(int flag){
        findViewById(R.id.title_tv_left).setVisibility(flag);
    }
    public void setRightBtnVisible(int flag){
        findViewById(R.id.title_tv_right).setVisibility(flag);
    }
    public void setCenterTextVisible(int flag){
        findViewById(R.id.title_tv_center).setVisibility(flag);
    }

    public void setCenterText(String text){
        ((TextView)findViewById(R.id.title_tv_center)).setText(text);
    }

    public void setTitleBackcolor(int color){
        findViewById(R.id.title_layout).setBackgroundColor(color);
    }

    public void setCenterText(int text){
        ((TextView)findViewById(R.id.title_tv_center)).setText(text);
    }
    public void setLeftText(String text){
        ((TextView)findViewById(R.id.title_tv_left)).setText(text);
    }

    public void setLeftText(int text){
        ((TextView)findViewById(R.id.title_tv_left)).setText(text);
    }
    public void setRightText(String text){
        ((TextView)findViewById(R.id.title_tv_right)).setText(text);
    }

    public void setRightText(int text){
        ((TextView)findViewById(R.id.title_tv_right)).setText(text);
    }

    public void setLeftBtnOnclick(){
        findViewById(R.id.title_tv_left).setOnClickListener(this);
    }

    public void setRightBtnOnclick(){
        findViewById(R.id.title_tv_right).setOnClickListener(this);
    }

    public RequestQueue getBaseRequest() {

        return Volley.newRequestQueue(this);
    }
}
