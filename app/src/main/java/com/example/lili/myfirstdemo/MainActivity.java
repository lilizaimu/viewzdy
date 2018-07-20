package com.example.lili.myfirstdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //    MyLoadingView myLoadingView;
//    GestureViewGroup gesture_vp;

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
//        gesture_vp = findViewById(R.id.gesture_vp);
//        gesture_vp.setGestureListener(new GestureViewGroup.GestureListener() {
//            @Override
//            public void onAnswerRight() {
//                Toast.makeText(MainActivity.this, "你已经成功了", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onUnmatchedExceedBoundary() {
//                Toast.makeText(MainActivity.this, "你没机会了", Toast.LENGTH_SHORT).show();
//            }
//        });
////        int[] answer={1,2,3,7,6,5};
//        gesture_vp.setAnswer(answer);

        Button btnShow = findViewById(R.id.btn_show);
        Button btnHide = findViewById(R.id.btn_hide);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInstance.newInstance().showDialog(getSupportFragmentManager());
            }
        });
        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInstance.newInstance().hideDialog();
            }
        });
    }
}
