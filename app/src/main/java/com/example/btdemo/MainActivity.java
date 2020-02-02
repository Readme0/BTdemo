package com.example.btdemo;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  //MainActivity 入口类
    public static final int REQUEST_CODE = 0;  //final代表不可改变，类似const
    private BlueToothController mController = new BlueToothController();
    private Toast mToast;  //此时会用默认的构造器new出一个Toast对象

    private BroadcastReceiver receiver = new BroadcastReceiver() {  //构造器传参是小括号内，这个代表匿名内部类！只要一个类是抽象的或是一个接口，那么其子类中的方法都可以使用匿名内部类来实现
       //这里应该是重写内部类方法，创建了receiver对象，在某个时刻会调用到receiver.onReceiver，这样我们的程序目的就实现了
        @Override
        public void onReceive(Context context, Intent intent) {
        int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,-1);
        switch(state){
            case BluetoothAdapter.STATE_OFF:
                showToast("STATE_OFF");
                break;
            case BluetoothAdapter.STATE_ON:
                showToast("STATE_ON");
                break;
            case BluetoothAdapter.STATE_TURNING_ON:
                showToast("STATE_TURNING_ON");
                break;
            case BluetoothAdapter.STATE_TURNING_OFF:
                showToast("STATE_TURNING_OFF");
                break;
            default:
                showToast("UNKNOWN STATE");
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(receiver,filter);
    }

    public void isSupportBlueTooth(View view){
        boolean ret = mController.isSupportBlueTooth();
        showToast("support Bluetooth?" + ret);
    }  //MainActivity执行顺序？类中的方法是有严格顺序的！！

    public void isBluetoothEnable(View view){
        boolean ret = mController.getBlueToothStatus();
        showToast("Bluetooth enable?" + ret);
    }

    public void requestTurnOnBluetooth(View view){
        mController.turnOnBlueTooth(this,REQUEST_CODE);
    }

    public void turnOffBluetooth(View view){
        mController.turnOffBluetooth();
    }

    private void showToast(String text){
        if (mToast == null) {
            mToast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        }
        else{
            mToast.setText(text);
        }
        mToast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            showToast("打开成功");
        }
        else{
            showToast("打开失败");
        }
    }
}
