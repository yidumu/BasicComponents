package com.example.servicesourcecode.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.servicesourcecode.IMyAidlInterface;

public class ExampleService extends Service {

    private static final String TAG = "exampleservice";

    private LocalBinder localBinder = new LocalBinder();

    public class LocalBinder extends IMyAidlInterface.Stub {

        public void startDownload() {
            Log.d(TAG, "startDownload");
        }

        @Override
        public void add(int num1, int num2) throws RemoteException {
            Log.d(TAG,"ADD"+num1+num2);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return localBinder;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
