package com.example.lili.myfirstdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyLoadingView myLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        myLoadingView = findViewById(R.id.myLoadingView);
//        myLoadingView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    myLoadingView.startAnimatoingValue(100);
//                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    myLoadingView.stopAnimation();
//                }
//                return true;
//            }
//        });
    }
}
