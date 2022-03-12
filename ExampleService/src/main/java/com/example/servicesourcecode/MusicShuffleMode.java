package com.example.servicesourcecode;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class MusicShuffleMode {

    private static final String TAG = "MusicShuffleMode";

    LinkedList<Integer> linkedList =new LinkedList<>();

    Shuffler shuffler = new Shuffler();

    private int flag = 0;//标记歌曲位置

    private int currentFlag;//标记歌曲当前位置

    public MusicShuffleMode(){
        Log.d(TAG, "list size :" + linkedList.size());
        init();
        test();
    }

    private void init(){
        for(int i=100;i<110;i++){
            linkedList.add(i);
        }
    }

    public void test() {
        Collections.shuffle(linkedList);
        Log.d(TAG, "shuffle list :" + linkedList.toString());
        for(int i=0;i<linkedList.size();i++){

        }
    }

    //下一首
    public int nextSong(){
        Log.d(TAG,"nextSong");
        flag++;
        return flag;
    }

    //上一首
    public int lastSong(){
        Log.d(TAG,"lastSong");
        flag--;
        return flag;
    }

    //下一首和上一首

    //添加一首歌
    public void AddOneSong(Integer integer){
        linkedList.addFirst(integer);
    }
    //删除一首歌
    public void RemoveOneSong(Integer integer){
        linkedList.remove(integer);
    }
}
