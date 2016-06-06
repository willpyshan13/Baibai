package com.baibai.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @Comments : TODO(用一句话描述该文件做什么)
 * @author will
 * @CreateDate : 2016年6月1日 上午11:36:52
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月1日 上午11:36:52
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class CommonListview extends ListView {

	public CommonListview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CommonListview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CommonListview(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	private static final String TAG = "baibai_CommonListview";

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
