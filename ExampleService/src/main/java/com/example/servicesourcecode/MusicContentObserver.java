package com.example.servicesourcecode;

import android.database.ContentObservable;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

public class MusicContentObserver extends ContentObserver {
    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
    }

    public MusicContentObserver(Handler handler) {
        super(handler);
    }

    @Override
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
    }
}
