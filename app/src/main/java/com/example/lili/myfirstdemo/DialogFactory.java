package com.example.lili.myfirstdemo;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * 工厂模式
 * Created by 13096 on 2018/7/20.
 */

public class DialogFactory {


    public void getdialog(Context context) {
        new AlertDialog.Builder(context).show();
    }

}
