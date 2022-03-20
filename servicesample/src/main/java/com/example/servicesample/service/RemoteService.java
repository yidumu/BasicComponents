package com.example.servicesample.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import com.example.servicesample.IRemoteService;
import com.example.servicesample.Rect;

import androidx.annotation.Nullable;

public class RemoteService extends Service {
    private static final String TAG = RemoteService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() called with: intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private final IRemoteService.Stub binder = new IRemoteService.Stub() {
        @Override
        public Bundle setInPid(Bundle bundle) throws RemoteException {
            int pid = bundle.getInt("KEY");
            Log.d(TAG, "setInPid: " + pid);
            return bundle;
        }

        @Override
        public Bundle setOutPid(Bundle bundle) throws RemoteException {
            int pid = bundle.getInt("KEY");
            Log.d(TAG, "setOutPid: " + pid);
            return bundle;
        }

        @Override
        public Bundle setInoutPid(Bundle bundle) throws RemoteException {
            int pid = bundle.getInt("KEY");
            Log.d(TAG, "setInoutPid: " + pid);
            return bundle;
        }

        @Override
        public void saveRect(Bundle bundle) throws RemoteException {
            bundle.setClassLoader(getClass().getClassLoader());
            Rect rect = bundle.getParcelable("rect");
            //process(rect); // Do more with the parcelable.

        }

        public int getPid() {
            return Process.myPid();
        }

        public void basicTypes(int anInt, long aLong, boolean aBoolean,
                               float aFloat, double aDouble, String aString) {
            // Does nothing
        }
    };
}
