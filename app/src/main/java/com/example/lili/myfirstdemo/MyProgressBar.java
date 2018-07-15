package com.example.lili.myfirstdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
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
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 圆环状态
 * Created by 13096 on 2018/7/15.
 */

public class MyProgressBar extends View {

    int firstColor;    //第一圈颜色
    int secondColor;   //第二圈颜色
    int mSpeed;        //多长时间转一圈  单位为毫秒
    int circleWidth;   //圆环的宽度
    Paint mPaint;
    int radius;       //半径
    float mProgress;    //当前角度


    public MyProgressBar(Context context) {
        this(context, null);
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.MyProgressBar, defStyleAttr, 0);
        firstColor = typedArray.getColor(R.styleable.MyProgressBar_firstColor, Color.BLUE);
        secondColor = typedArray.getColor(R.styleable.MyProgressBar_secondColor, Color.RED);
        mSpeed = typedArray.getInt(R.styleable.MyProgressBar_mSpeed, 1000);
        circleWidth = typedArray.getDimensionPixelSize(R.styleable.MyProgressBar_circleWidth
                , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
                        , 20, getResources().getDisplayMetrics()));
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setStrokeWidth(circleWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 360);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setDuration(mSpeed).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float progress = (float) valueAnimator.getAnimatedValue();
                mProgress = progress;
                postInvalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                int currentColor = secondColor;
                secondColor = firstColor;
                firstColor = currentColor;
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("TAG","dd");
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        //半径
        radius = getMeasuredHeight() > getMeasuredWidth()     //元钱半径半径
                ? getMeasuredWidth() / 2 - circleWidth / 2
                : getMeasuredHeight() / 2 - circleWidth / 2;
        RectF rectF = new RectF(getMeasuredWidth() / 2 - radius
                , getMeasuredHeight() / 2 - radius
                , getMeasuredWidth() / 2 + radius
                , getMeasuredHeight() / 2 + radius);


        mPaint.setColor(firstColor);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radius, mPaint);
        mPaint.setColor(secondColor);
        canvas.drawArc(rectF, -90, mProgress, false, mPaint);
    }
}
