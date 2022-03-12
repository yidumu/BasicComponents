package com.example.mybottomnativeview.pattern.observer;

import android.util.Log;

public class DynamicView implements UpdateObserver {
    @Override
    public void update(Subject subject) {
        Log.d("TAG","DynamicView"+subject.getState());
    }
}
