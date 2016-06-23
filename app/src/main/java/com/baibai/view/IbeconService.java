package com.baibai.view;

import android.app.ProgressDialog;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.widget.Toast;

import com.baibai.R;
import com.yunliwuli.beacon.kit.data.BluetoothDeviceAndRssi;
import com.yunliwuli.beacon.kit.manager.YlwlManager;
import com.yunliwuli.beacon.kit.manager.YlwlManagerListener;

import java.util.ArrayList;

/**
 * @author will
 * @Comments : TODO(用一句话描述该文件做什么)
 * @CreateDate : 2016年6月2日 上午10:53:26
 * @ModifiedBy : will
 * @ModifiedDate: 2016年6月2日 上午10:53:26
 * @Modified: TODO(用一句话描述该文件做什么)
 */
public class IbeconService extends Service {
    private static final String TAG = "baibai_IbeconService";
    YlwlManager mIbeconManager;
    private ArrayList<BluetoothDeviceAndRssi> deviceList = new ArrayList<BluetoothDeviceAndRssi>();

    private static final int REQUEST_ENABLE_BT = 2;
    /**
     * 是否连接设备
     */
    public boolean connect_device;
    public long connectCurrentTime = 0;
    protected static String mBluetoothDeviceAddress;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mIbeconManager = YlwlManager.getInstance(this);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT)
                    .show();

        }

        if (!mIbeconManager.isBluetoothEnabled()) {
            Toast.makeText(this, R.string.ble_not_open, Toast.LENGTH_SHORT).show();
            showBLEDialog();
        }

        /**
         *  开启服务
         */
        mIbeconManager.startService();
        /**
         *  扫描
         */
        mIbeconManager.scanLeDevice(true);
        YlwlManagerListener lis = new YlwlManagerListener() {

            @Override
            public void onUpdateBeacon(final ArrayList<BluetoothDeviceAndRssi> beacons) {
                /**
                 * 传多个beacon过来   已经做好了排序  ，  距离      连接状态(BluetoothDeviceAndRssi isConn方法) 也随时改变
                 */
                deviceList = beacons;
                Toast.makeText(IbeconService.this, "扫描到：" + beacons.size() + "个设备", Toast.LENGTH_SHORT).show();
//				runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						deviceList=  beacons   ;
//						Collections.sort(deviceList);//距离排序
//						deviceAdapter = new DeviceAdapter(DeviceListActivity.this, deviceList);
//						newDevicesListView.setAdapter(deviceAdapter);
//					}
//				});

            }

            @Override
            public void onNewBeacon(BluetoothDeviceAndRssi beacon) {
                /**
                 * 传单个beacon过来
                 */
            }

            @Override
            public void onNewBeaconDataChang(BluetoothDeviceAndRssi beacon) {
                /**
                 * 传单个beacon过来           而且是mac地址不变     距离变      连接状态变
                 */
            }
        };
        mIbeconManager.setYlwlManagerListener(lis);
    }

    public BluetoothDeviceAndRssi bluetoothdeviceandrssi_onItem;
    ProgressDialog mpDialog;

    protected void dialogshow() {
        mpDialog = new ProgressDialog(this);
        mpDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//
        mpDialog.setTitle(null);//
        mpDialog.setIcon(null);//
        mpDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface arg0) {


            }
        });
        mpDialog.setCancelable(true);//
        mpDialog.setCanceledOnTouchOutside(false);
    }

    public void connectDevices() {
        bluetoothdeviceandrssi_onItem = deviceList.get(0);
        if (!bluetoothdeviceandrssi_onItem.isCONN()) {
            Toast.makeText(getApplicationContext(), getString(R.string.gangcailianjieguole), Toast.LENGTH_SHORT).show();
            return;
        }
        bluetoothdeviceandrssi_onItem.setCONN(false);//连接的时候  设置为不可连接。
        mpDialog.setMessage(getString(R.string.connecting) + bluetoothdeviceandrssi_onItem.getBluetoothdevice().getName());
        mpDialog.show();
        connectCurrentTime = System.currentTimeMillis();
        new Thread() {
            public void run() {
                connect_device = true;

                mIbeconManager.connect(bluetoothdeviceandrssi_onItem);
                mBluetoothDeviceAddress = bluetoothdeviceandrssi_onItem.getBluetoothdevice().getAddress();
            }

            ;
        }.start();

    }

    protected void showBLEDialog() {
        Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        enableIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(enableIntent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * 释放资源
         */
        mIbeconManager.unbindService();
        /**
         *       关闭扫描
         */
        mIbeconManager.scanLeDevice(false);
    }
}
