package com.example.servicesourcecode.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.example.servicesourcecode.mode.Album;
import com.example.servicesourcecode.IMusicAidlInterface;

public class MediaService extends Service {
    private static final String TAG = MediaService.class.getSimpleName();
    private Album mAlbum;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Return the interface
        return binder;
    }

    private final IMusicAidlInterface.Stub binder = new IMusicAidlInterface.Stub() {
        @Override
        public void getMusicName() throws RemoteException {
            Parcel parcel=Parcel.obtain();
            parcel.writeString("aaa");
            parcel.writeString("bbb");
            parcel.writeString("ccc");
            parcel.writeLong(10);
            parcel.marshall();
            parcel.setDataPosition(0);
            parcel.recycle();
        }

        public void basicTypes(int anInt, long aLong, boolean aBoolean,
                               float aFloat, double aDouble, String aString) {
            // Does nothing
        }
    };
}
