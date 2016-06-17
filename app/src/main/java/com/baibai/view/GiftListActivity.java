package com.baibai.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baibai.R;
import com.baibai.tools.ScreenProperties;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class GiftListActivity extends AppCompatActivity {
    private RollPagerView mRollViewPager;
    private ImageLoader imageLoader;
    private GridView mGiftList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_list);

        initView();
    }

    private void initView() {
        GiftListAdpater giftAdapter = new GiftListAdpater();
        mGiftList = (GridView) findViewById(R.id.gift_list_gv);
        mGiftList.setAdapter(giftAdapter);
        initViewPagerBound();
        imageLoader = ImageLoader.getInstance();
        mGiftList = (GridView) findViewById(R.id.gift_list_gv);
        mRollViewPager = (RollPagerView) findViewById(R.id.roll_view_pager);
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(2000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new PagerRecycleAdapter());
        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        mRollViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));
    }

    private void initViewPagerBound() {
        // 先算出图片长在屏幕中占多少英寸
        float xInch = (ScreenProperties.getScreenWidth() / ScreenProperties
                .getXdpi());
        // 根据图片宽长比例算出高应该占多少英寸
        double yInch = xInch * (15 / 32.0);
        // 再根据Y轴方向上每英寸多少像素算出图片高应该有多少像素
        int viewPagerHeight = (int) (ScreenProperties.getXdpi() * yInch);
        RollPagerView viewPagerBound = (RollPagerView) findViewById(R.id.gift_list_rollpagerview);
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
            View view = getLayoutInflater().inflate(R.layout.item_gitf_list, null);
            ImageView image = (ImageView) view.findViewById(R.id.item_gift_list_iv_head);
            imageLoader.displayImage(imgs[position], image);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }

    class GiftListAdpater extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
