package com.example.servicesourcecode;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class Shuffler {

    public static final int MAX_HISTORY_SIZE = 1000;

    private final LinkedList<Integer> mHistoryOfNumbers = new LinkedList<Integer>();

    private final TreeSet<Integer> mPreviousNumbers = new TreeSet<Integer>();

    private  ArrayList<Integer> mArrayList;

    private final Random mRandom = new Random();

    private int mPrevious;

    public Shuffler() {
        super();
    }

    public int nextInt(final int interval) {
        int next;
        do {
            next = mRandom.nextInt(interval);
        } while (next == mPrevious && interval > 1
                && !mPreviousNumbers.contains(Integer.valueOf(next)));
        mPrevious = next;
        mHistoryOfNumbers.add(mPrevious);
        mPreviousNumbers.add(mPrevious);
        cleanUpHistory();
        Log.d("Shuffler",mPrevious+"---"+mPreviousNumbers.toString());
        return next;
    }

    private void cleanUpHistory() {
        if (!mHistoryOfNumbers.isEmpty() && mHistoryOfNumbers.size() >= MAX_HISTORY_SIZE) {
            for (int i = 0; i < Math.max(1, MAX_HISTORY_SIZE / 2); i++) {
                mPreviousNumbers.remove(mHistoryOfNumbers.removeFirst());
            }
        }
    }

    /**
     * 随机一个数，然后从数组中去掉
     */
    public void removeRandomNum(){
        int next;
        if(mArrayList.size() >= 1) {
            next = mRandom.nextInt(mArrayList.size());
            mArrayList.remove(next);
            Log.d("Shuffler", mArrayList.size() + "---" + next+"---"+mArrayList.toString());
        }
    }

    /**
     * 将数组随机，状态改变后重新随机
     */
}
