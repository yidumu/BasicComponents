package com.example.sample.socket;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.example.sample.R;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();

    private DataService mdataService;


    private String Host = "169.254.80.45";
    private int Port = 7654;

    // Code to manage Service lifecycle.管理服务生命周期的代码
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        //活动与服务绑定
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mdataService = ((DataService.LocalBinder) service).getService();
        }

        //活动与服务断开连接
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent gattServiceIntent = new Intent(this, DataService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 1:
                Log.i(TAG, "Connet");
                mdataService.ConnectSocket();
                break;
            case 2:
                mdataService.sendData("aaa");
                break;
            case 3:
                mdataService.ReceiveData();
                break;
        }
    }
}
