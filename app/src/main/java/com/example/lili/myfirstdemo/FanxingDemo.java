package com.example.lili.myfirstdemo;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 13096 on 2018/7/22.
 */

public class FanxingDemo<T>  extends FanXing<ArrayList<T>>{


    @Override
    public void setT(ArrayList<T> ts) {

    }

    @Override
    public ArrayList<T> getT() {
        return null;
    }

    static void init(){
        FanxingDemo<Integer> fanxingDemo=new FanxingDemo<>();



        fanxingDemo.getT();
    }

}
