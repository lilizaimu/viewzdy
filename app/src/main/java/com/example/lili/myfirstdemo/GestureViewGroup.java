package com.example.lili.myfirstdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by 13096 on 2018/7/19.
 */

public class GestureViewGroup extends RelativeLayout {

    private int colorInterNoFinger;  //内圆的颜色
    private int colorOuterNoFinger;  //外圆的颜色
    private int colorFingerUp;       //手指抬起时的效果
    private int colorFingerOn;       //手指放上的颜色
    private int tryTimes;            //尝试次数
    private int count;               //行列各有几个
    private Path path;               //轨迹
    private Paint mPaint;            //画笔

    public GestureViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs
                , R.styleable.GestureViewGroup, defStyleAttr, 0);
        colorInterNoFinger = typedArray.getColor(R.styleable.GestureViewGroup_colorInterNoFinger, Color.BLUE);
        colorOuterNoFinger = typedArray.getColor(R.styleable.GestureViewGroup_colorOuterNoFinger, Color.GREEN);
        colorFingerUp = typedArray.getColor(R.styleable.GestureViewGroup_colorFingerUp, Color.RED);
        colorFingerOn = typedArray.getColor(R.styleable.GestureViewGroup_colorFingerOn, Color.YELLOW);
        tryTimes = typedArray.getInt(R.styleable.GestureViewGroup_tryTimes, 4);
        count = typedArray.getInt(R.styleable.GestureViewGroup_count, 4);
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setColor(colorOuterNoFinger);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
