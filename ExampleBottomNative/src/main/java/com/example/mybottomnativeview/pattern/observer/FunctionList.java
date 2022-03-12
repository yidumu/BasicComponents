package com.example.mybottomnativeview.pattern.observer;

import android.util.Log;

public class FunctionList implements UpdateObserver {
    @Override
    public void update(Subject subject) {
        //更新功能列表
        Log.d("TAG","FunctionList"+subject.getState());
    }
}

