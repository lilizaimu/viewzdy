package com.example.lili.myfirstdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.TimeUnit;

/**
 * 一个音量view
 * Created by 13096 on 2018/7/16.
 */

public class MySoundView extends View {

    int count;         //圆弧的个数
    int firstColor;    //第一圈的颜色
    int secondColor;   //第二圈的颜色
    int strokeWidth;   //圆弧的宽度
    public static final double scale = 0.6;   //间隔的距离是圆弧的0.6倍
    float jdArc;                             //一个圆弧的角度
    float jdGroup;                           //一组的角度 圆弧+空格
    int index = 10;                               //当前音量
    Paint mPaint;     //画笔
    int radius;                               //半径

    public MySoundView(Context context) {
        this(context, null);
    }

    public MySoundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    float lastX;
    float lastY;

    public MySoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySoundView, defStyleAttr, 0);
        count = typedArray.getInt(R.styleable.MySoundView_count, 18);
        firstColor = typedArray.getColor(R.styleable.MySoundView_firstColor, Color.RED);
        secondColor = typedArray.getColor(R.styleable.MySoundView_secondColor, Color.BLUE);
        strokeWidth = typedArray.getDimensionPixelSize(R.styleable.MySoundView_circleWidth
                , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        jdGroup = 360f / count;
        jdArc = jdGroup / 16 * 10;
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = motionEvent.getX();
                        lastY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float x = motionEvent.getX();
                        float y = motionEvent.getY();
                        if (Math.abs(y - lastY) > Math.abs(x - lastX)) {
                            if (y - lastY > 20) {
                                if (index < count)
                                    index++;
                                lastX = x;
                                lastY = y;
                                postInvalidate();
                            } else if (y - lastY < -20) {
                                if (index > 0)
                                    index--;
                                lastX = x;
                                lastY = y;
                                postInvalidate();
                            }
                        }
                        break;
                }
                return true;
            }
        });

    }


    @Override
    protected void onDraw(Canvas canvas) {
        radius = getMeasuredHeight() > getMeasuredWidth() ? getMeasuredWidth() / 2 - strokeWidth / 2 : getMeasuredHeight() / 2 - strokeWidth / 2;
        RectF rectF = new RectF(getMeasuredWidth() / 2 - radius, getMeasuredHeight() / 2 - radius
                , getMeasuredWidth() / 2 + radius, getMeasuredHeight() / 2 + radius);
        mPaint.setColor(firstColor);
        for (int i = 0; i < count; i++) {
            canvas.drawArc(rectF, i * jdGroup, jdArc, false, mPaint);
        }
        mPaint.setColor(secondColor);
        for (int i = 0; i < index; i++) {
            canvas.drawArc(rectF, i * jdGroup, jdArc, false, mPaint);
        }
    }

}
