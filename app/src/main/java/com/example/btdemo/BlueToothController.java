package com.example.btdemo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

/*蓝牙适配器 类*/
public class BlueToothController {

    private BluetoothAdapter mAdapter;  //类里面创建对象?

    public BlueToothController(){
        mAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    /*是否支持蓝牙*/
    public boolean isSupportBlueTooth(){
        if (mAdapter != null) {
            return true;
        }
        else{
            return false;
        }
    }

    /*判断当前蓝牙状态，true打开，false关闭*/
    public boolean getBlueToothStatus(){
        assert (mAdapter != null);
        return mAdapter.isEnabled();
    }

    /*打开一个蓝牙设备*/
    public void turnOnBlueTooth(Activity activity, int requestCode){
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(intent, requestCode);
//        mAdapter.enable();
    }


    public void turnOffBluetooth() {
        mAdapter.disable();
    }
}
