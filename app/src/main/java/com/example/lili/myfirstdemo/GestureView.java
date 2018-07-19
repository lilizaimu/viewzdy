package com.example.lili.myfirstdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * 手势锁的view
 * Created by 13096 on 2018/7/19.
 */

public class GestureView extends View {


    private int colorInterNoFinger;  //内圆的颜色
    private int colorOuterNoFinger;  //外圆的颜色
    private int colorFingerUp;       //手指抬起时的效果
    private int colorFingerOn;       //手指放上的颜色
    private Paint mPaint;            //画笔

    int radius;                      //半径
    int centerX;                     //圆心X坐标
    int centerY;                     //圆心Y坐标
    int strokeWidth;                 //外圆宽度

    float inneCircleRadiusRate = 0.3f;  //内圆：外圆

    STATUS status = STATUS.FINGER_NO;

    enum STATUS {
        FINGER_ON, FINGER_UP, FINGER_NO
    }

    public GestureView(Context context, int colorInterNoFinger, int colorOuterNoFinger, int colorFingerOn, int colorFingerUp) {
        super(context);
        this.colorInterNoFinger = colorInterNoFinger;
        this.colorOuterNoFinger = colorOuterNoFinger;
        this.colorFingerOn = colorFingerOn;
        this.colorFingerUp = colorFingerUp;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        strokeWidth = 4;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int mHeight = MeasureSpec.getSize(heightMeasureSpec);
        radius = Math.min(mWidth, mHeight) / 2 - strokeWidth / 2;
        centerX = mWidth / 2;
        centerY = mHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        switch (status) {
            case FINGER_NO:
                drawCircle(canvas, colorInterNoFinger, colorOuterNoFinger);
                break;
            case FINGER_ON:
                drawCircle(canvas, colorFingerOn, colorFingerOn);
                break;
            case FINGER_UP:
                drawCircle(canvas, colorFingerUp, colorFingerUp);
                break;
        }
    }

    public void drawCircle(Canvas canvas, int colorInner, int colorOuter) {
        //画内圆
        mPaint.setColor(colorInner);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, radius * inneCircleRadiusRate, mPaint);
        //画外圆
        mPaint.setColor(colorOuter);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWidth);
        canvas.drawCircle(centerX, centerY, radius, mPaint);
    }


    /**
     * 设置状态
     *
     * @param status
     */
    public void setStatus(STATUS status) {
        this.status = status;
        invalidate();
    }


}
