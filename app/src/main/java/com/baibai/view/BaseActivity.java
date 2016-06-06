package com.baibai.view;

import android.app.Activity;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

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

    @Override
    public void onClick(View v) {

    }

    public RequestQueue getBaseRequest() {
        return Volley.newRequestQueue(this);
    }
}
