package com.example.lili.myfirstdemo;

import android.util.Log;

/**
 * Created by 13096 on 2018/7/21.
 */

public class SecondObserver implements Observer{

    @Override
    public void update(String msg) {
        Log.d("TAG","我是secondObserver");
    }
}
