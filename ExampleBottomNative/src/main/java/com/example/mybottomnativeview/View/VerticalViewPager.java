package com.example.mybottomnativeview.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class VerticalViewPager extends ViewPager {

    public VerticalViewPager(@NonNull Context context) {
        this(context, null);
    }

    public VerticalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //设置动画
        setPageTransformer(true, new DefaultTransformer());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }





    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = super.onInterceptTouchEvent(swapEvent(ev));
        swapEvent(ev);
        return intercept;

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapEvent(ev));
    }

    public MotionEvent swapEvent(MotionEvent event) {
        //获取宽高
        float width = getWidth();
        float height = getHeight();
        //将Y轴的移动距离转变成X轴的移动距离
        float swappedX = (event.getY() / height) * width;
        //将X轴的移动距离转变成Y轴的移动距离
        float swappedY = (event.getX() / width) * height;
        //重设event的位置
        event.setLocation(swappedX, swappedY);
        return event;
    }

    private class DefaultTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(@NonNull View view, float v) {
            float alpha = 0;
            if (0 <= v && v <= 1) {
                alpha = 1 - v;
            } else if (-1 < v && v < 0) {
                alpha = v + 1;
            }
            view.setAlpha(alpha);
            float transX =view.getWidth() * -v;
            view.setTranslationX(transX);
            float transY =v * view.getHeight();
            view.setTranslationY(transY);
        }
    }
}
