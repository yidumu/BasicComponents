package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String string;
        StringBuffer stringBuffer;
        StringBuilder stringBuilder;

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.remove(0));

        //Proxy.newProxyInstance();
        Class cl;
        try {
            cl = Class.forName("UseCaseTracker");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        UseCaseTracker useCaseTracker = new UseCaseTracker();
        cl = useCaseTracker.getClass();
        cl = UseCaseTracker.class;
    }
}
