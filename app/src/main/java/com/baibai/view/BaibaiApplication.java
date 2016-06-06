package com.baibai.view;

import com.baidu.mapapi.SDKInitializer;

import android.app.Application;
import android.content.Intent;

/**
 * @Comments : TODO(用一句话描述该文件做什么)
 * @author will
 * @CreateDate : 2016年6月1日 下午9:07:50
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月1日 下午9:07:50
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class BaibaiApplication extends Application {
	private static final String TAG = "baibai_BaibaiApplication";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		SDKInitializer.initialize(this);


	}
}
