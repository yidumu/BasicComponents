package com.example.sample.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;

import com.example.sample.IRemoteService;

import androidx.annotation.Nullable;

public class RemoteService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private final IRemoteService.Stub binder = new IRemoteService.Stub() {
        public int getPid() {
            return Process.myPid();
        }

        public void basicTypes(int anInt, long aLong, boolean aBoolean,
                               float aFloat, double aDouble, String aString) {
            // Does nothing
        }
    };
}
