package com.example.servicesourcecode;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.servicesourcecode.service.ExampleService;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG="MainActivity";

    private ExampleService.LocalBinder mlocalBinder;

    private IMyAidlInterface iMyAidlInterface;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mlocalBinder=(ExampleService.LocalBinder)service;
            iMyAidlInterface=ExampleService.LocalBinder.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private Button bindButton;
    private Button unbindButton;
    private Button callfunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }

    private void Init(){
        bindButton=findViewById(R.id.bind_service);
        unbindButton=findViewById(R.id.unbind_service);
        callfunction=findViewById(R.id.call_function);
        bindButton.setOnClickListener(this);
        unbindButton.setOnClickListener(this);
        callfunction.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bind_service:
                //Service启动
                Intent intent = new Intent(MainActivity.this,ExampleService.class);
                startService(intent);
                bindService(intent,conn, Service.BIND_ABOVE_CLIENT);
                break;
            case R.id.unbind_service:
                unbindService(conn);
                break;

            case R.id.call_function:
                try {
                    iMyAidlInterface.add(1,2);
                }catch (RemoteException e){
                    e.printStackTrace();
                }
                break;
        }
    }

    //监听数据库变化
    public void setMusicListent(){
        MusicContentObserver musicContentObserver = new MusicContentObserver(new Handler());
        getContentResolver().registerContentObserver(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, true,musicContentObserver);
    }
}
