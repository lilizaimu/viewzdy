package com.example.lili.myfirstdemo;

import java.util.ArrayList;

/**
 * Created by 13096 on 2018/7/22.
 */

public abstract class FanXing<T extends ArrayList> {

    T t;
    public abstract void setT(T t);

    public abstract T getT();

}
