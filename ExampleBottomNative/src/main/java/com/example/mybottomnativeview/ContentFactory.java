package com.example.mybottomnativeview;

import android.support.v4.app.Fragment;

//
public class ContentFactory {

    private int firstColumn;
    private int secondColumn;
    public ContentFactory(int firstColumn,int secondColumn){
        this.firstColumn = firstColumn;
        this.secondColumn = secondColumn;
    }

    public Fragment ConcreteFactory(){
        return new Fragment();
    }

}
