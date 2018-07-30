package com.example.lili.myfirstdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {

    //    MyLoadingView myLoadingView;
//    GestureViewGroup gesture_vp;


//    private ArrayList<com.example.lili.myfirstdemo.Observer> observers;
//    EditText et;

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

//        Button btnShow = findViewById(R.id.btn_show);
//        Button btnHide = findViewById(R.id.btn_hide);
//        btnShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogInstance.newInstance().showDialog(getSupportFragmentManager());
//            }
//        });
//        btnHide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogInstance.newInstance().hideDialog();
//            }
//        });
//        FirstObserver firstObserver=new FirstObserver();
//        SecondObserver secondObserver=new SecondObserver();
//        addObservers(firstObserver);
//        addObservers(secondObserver);
//        et = findViewById(R.id.et);
//        et.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                notifyObserver();
//            }
//        });
//    }
//
//    public void addObservers(com.example.lili.myfirstdemo.Observer observer) {
//        if (observers == null)
//            this.observers = new ArrayList<>();
//        if (!observers.contains(observer))
//            this.observers.add(observer);
//    }
//
//    public void removObserver(com.example.lili.myfirstdemo.Observer observer) {
//        if (observer != null)
//            if (observers.contains(observer)) {
//                this.observers.remove(observer);
//            }
//    }
//
//    public void notifyObserver() {
//        if (observers != null)
//            for (com.example.lili.myfirstdemo.Observer observer : observers) {
//                observer.update(et.getText().toString());
//            }
//    }
//        Button btn = findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new LBaseDialog.Builder()
//                        .setMessage("你好吗")
//                        .setConfirmButton("确定", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Toast.makeText(MainActivity.this, "你点击了确定", Toast.LENGTH_SHORT).show();
//                            }
//                        }).setCancelButton("取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(MainActivity.this, "你点击了取消", Toast.LENGTH_SHORT).show();
//                    }
//                }).build().showDialog(getSupportFragmentManager());
//            }
//        });

    }

}
