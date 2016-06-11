package com.baibai.view;

import com.baibai.R;
import com.baibai.tools.ScreenProperties;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author will
 * @Comments : TODO(用一句话描述该文件做什么)
 * @CreateDate : 2016年6月1日 上午9:17:44
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月1日 上午9:17:44
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class HomeActivity extends BaseActivity {
    private static final String TAG = "baibai_HomeActivity";
    private ListView mGoodsLv;
    private RollPagerView mRollViewPager;
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init() {
        imageLoader = ImageLoader.getInstance();
        mGoodsLv = (ListView) findViewById(R.id.home_lv);
        GoodsAdapter adapter = new GoodsAdapter();
        mGoodsLv.setAdapter(adapter);
        initViewPagerBound();
//		RecyclerViewPager mRecyclerView = (RecyclerViewPager) findViewById(R.id.list);
//
//// setLayoutManager like normal RecyclerView, you do not need to change any thing.
//		LinearLayoutManager layout = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//		mRecyclerView.setLayoutManager(layout);
//		mRecyclerView.setAdapter(new RecyAdapter());

        mRollViewPager = (RollPagerView) findViewById(R.id.roll_view_pager);

        //设置播放时间间隔
        mRollViewPager.setPlayDelay(2000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new PagerRecycleAdapter());

//        设置指示器（顺序依次）
//        自定义指示器图片
//        设置圆点指示器颜色
//        设置文字指示器
//        隐藏指示器
//        mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));
//        mRollViewPager.setHintView(new TextHintView(this));
//        mRollViewPager.setHintView(null);

    }

    private void initViewPagerBound() {
        // 先算出图片长在屏幕中占多少英寸
        float xInch = (ScreenProperties.getScreenWidth() / ScreenProperties
                .getXdpi());
        // 根据图片宽长比例算出高应该占多少英寸
        double yInch = xInch * (15 / 32.0);
        // 再根据Y轴方向上每英寸多少像素算出图片高应该有多少像素
        int viewPagerHeight = (int) (ScreenProperties.getXdpi() * yInch);
        RollPagerView viewPagerBound = (RollPagerView) findViewById(R.id.roll_view_pager);
        viewPagerBound.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, viewPagerHeight));
    }


    private class PagerRecycleAdapter extends StaticPagerAdapter {
        private String[] imgs = {
                "http://ww4.sinaimg.cn/mw690/9058be1egw1f4iypskbiij20j60y20vb.jpg",
                "http://ww1.sinaimg.cn/mw690/9058be1egw1f4iypthvr1j20j60ssjta.jpg",
                "http://ww1.sinaimg.cn/mw690/9058be1egw1f4iypwba64j20c80c8glv.jpg",
                "http://ww1.sinaimg.cn/mw690/9058be1egw1f4iypt6kxrj20j60y2wij.jpg",
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            imageLoader.displayImage(imgs[position], view);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }


    class GoodsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 10;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_home_goods, null);
                holder.goodsIv = (ImageView) convertView.findViewById(R.id.goods_detail_icon);
                holder.goodsPrice = (TextView) convertView.findViewById(R.id.goods_price);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.goodsIv.setLayoutParams(new LinearLayout.LayoutParams(ScreenProperties.getScreenWidth() / 3, ScreenProperties.getScreenHeight() / 6));
            holder.goodsPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);

            return convertView;
        }

        class ViewHolder {
            ImageView goodsIv;
            TextView shopName;
            TextView goodsName;
            TextView goodsDiscount;
            TextView goodsDistance;
            TextView goodsPrice;
            TextView goodsprePrice;
            TextView goodsContent;
        }

    }
}
