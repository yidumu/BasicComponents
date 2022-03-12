package com.example.mybottomnativeview.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {

    //是否可以左右滑动
    private boolean scrollable = false;

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(scrollable){
            return super.onInterceptTouchEvent(ev);
        }else {
            return scrollable;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (scrollable){
            return onTouchEvent(ev);
        }else {
            return scrollable;
        }
    }
}
