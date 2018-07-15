package com.example.lili.myfirstdemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.lang.reflect.TypeVariable;

/**
 * Created by 13096 on 2018/7/15.
 */

public class MyLoadingView extends View {

    Paint mPaint;
    int colorAll;      //原本的颜色
    int colorSecond;   //第二圈的颜色
    int background;    //背景颜色
    int lineHeight;    //线的长度
    int lineWidth;     //线的宽度
    int lines = 100;   //一共有的线条个数
    float desgress;    //一份旋转的角度
    int percent = 0;   //百分之几

    ValueAnimator valueAnimator;

    public MyLoadingView(Context context) {
        this(context, null);
    }

    public MyLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyLoadingView, defStyleAttr, 0);
        colorAll = typedArray.getColor(R.styleable.MyLoadingView_colorAll, Color.BLACK);
        colorSecond = typedArray.getColor(R.styleable.MyLoadingView_colorSecond, Color.BLUE);
        background = typedArray.getColor(R.styleable.MyLoadingView_mBackground, Color.WHITE);
        lineHeight = typedArray.getDimensionPixelSize(R.styleable.MyLoadingView_lineHeight
                , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20
                        , getResources().getDisplayMetrics()));
        lineWidth = typedArray.getDimensionPixelSize(R.styleable.MyLoadingView_lineWidth
                , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3
                        , getResources().getDisplayMetrics()));
        typedArray.recycle();
        mPaint = new Paint();
        desgress = (float) (360.0 / lines);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int d = widthSize > heightSize ? heightSize : widthSize;
        Log.d("widthSize",widthSize+"");
        Log.d("heightSize",heightSize+"");
        setMeasuredDimension(d, d);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.drawColor(background);
        drawLine(canvas, lines, colorAll);      //画出背景的线条
        drawLine(canvas, percent, colorSecond);
    }

    //用动画效果画出对应的Value
    public void startAnimatoingValue(final int value) {
        percent = value;
        valueAnimator = ValueAnimator.ofInt(value);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                percent = (int) valueAnimator.getAnimatedValue();
                invalidate();

            }
        });
        valueAnimator.start();
    }

    /**
     * 停止画线
     */
    public void stopAnimation() {
        if (valueAnimator != null) {
            valueAnimator.cancel();
            valueAnimator.addUpdateListener(null);
            valueAnimator = null;
            percent = 0;
            invalidate();
        }
    }

    /**
     * 根据传入的角度画出画线
     *
     * @param canvas
     * @param value
     */
    public void drawLine(Canvas canvas, int value, int lineColor) {
        mPaint.setColor(lineColor);
        mPaint.setStrokeWidth(lineWidth);
        for (int i = 0; i < value; i++) {
            canvas.save();
            canvas.rotate(i * desgress - 90, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
            canvas.drawLine(0, getMeasuredHeight() / 2, lineHeight, getMeasuredHeight() / 2, mPaint);
            canvas.restore();

        }
    }

}
