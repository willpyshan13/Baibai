package com.baibai.view;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baibai.R;
import com.baibai.bean.MarketInfoBean;
import com.baibai.bean.MarketInfoReturn;
import com.baibai.tools.Logger;
import com.baibai.tools.LoginCacheUtils;
import com.baibai.tools.MD5Utils;
import com.baibai.tools.RequestUrl;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.clusterutil.clustering.ClusterManager;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.clusterutil.clustering.ClusterItem;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author will
 * @Comments : TODO(用一句话描述该文件做什么)
 * @CreateDate : 2016年6月1日 上午9:18:08
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月1日 上午9:18:08
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class CurrentPositionActivity extends BaseActivity {
    private static final String TAG = "baibai_CurrentPositionActivity";
    private ClusterManager<MyItem> mClusterManager;
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    MapView mMapView;
    BaiduMap mBaiduMap;
    boolean isFirstLoc = true;// 是否首次定位

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_position);
        initView();
    }

    /**
     * 向地图添加Marker点
     */
    public void addMarkers(List<MarketInfoBean> list) {
        // 添加Marker点
//        LatLng llA = new LatLng(24.521174, 118.144676);
//        LatLng llB = new LatLng(24.521274, 118.144676);
//        LatLng llC = new LatLng(24.521374, 118.144676);
//        LatLng llD = new LatLng(24.521474, 118.144676);
//        LatLng llE = new LatLng(24.521574, 118.144676);
//        LatLng llF = new LatLng(24.521674, 118.144676);
//        LatLng llG = new LatLng(24.521974, 118.144676);

        LatLng lat = null;
        List<MyItem> items = new ArrayList<MyItem>();
        for (int i = 0; i < list.size(); i++) {
            lat = new LatLng(Double.parseDouble(list.get(i).marketLt), Double.parseDouble(list.get(i).marketLg));
            items.add(new MyItem(lat));
        }
        mClusterManager.addItems(items);
    }

    /**
     * 每个Marker点，包含Marker点坐标以及图标
     */
    public class MyItem implements ClusterItem {
        private final LatLng mPosition;

        public MyItem(LatLng latLng) {
            mPosition = latLng;
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        @Override
        public BitmapDescriptor getBitmapDescriptor() {
            return BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_gcoding);
        }
    }

    private void initView() {
        // 地图初始化
        mMapView = (MapView) findViewById(R.id.bmapView);
        findViewById(R.id.current_position_btn).setOnClickListener(this);
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定义点聚合管理类ClusterManager
        mClusterManager = new ClusterManager<MyItem>(this, mBaiduMap);

        // 设置地图监听，当地图状态发生改变时，进行点聚合运算
        mBaiduMap.setOnMapStatusChangeListener(mClusterManager);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                processRegister(location);
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    private String jsonReturn = "";

    public void processRegister(BDLocation location) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userLg", location.getLongitude() + "");
            jsonObject.put("userLt", location.getLatitude() + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestUrl;
        if (LoginCacheUtils.isLogin()) {
            requestUrl = RequestUrl.GETNEARESTLIST + "?token=" + LoginCacheUtils.TOKEN;
        } else {
            requestUrl = RequestUrl.GETNEARESTLIST;
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, requestUrl, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Logger.e(CurrentPositionActivity.this, response.toString());
                if (response.optString("result").equals("true")) {
                    Gson gson = new Gson();
                    jsonReturn = response.toString();
                    MarketInfoReturn info = gson.fromJson(response.toString(), MarketInfoReturn.class);
                    addMarkers(info.data);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        getBaseRequest().add(jsonObjectRequest);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.current_position_btn:
                Intent intent = new Intent(CurrentPositionActivity.this, LocationStoreActivity.class);
                intent.putExtra(LocationStoreActivity.EXTRA_LOCATION,jsonReturn);
                CurrentPositionActivity.this.startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

}
