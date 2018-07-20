package com.example.lili.myfirstdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by 13096 on 2018/7/19.
 */

public class GestureViewGroup extends RelativeLayout {

    private int colorInterNoFinger;    //内圆的颜色
    private int colorOuterNoFinger;    //外圆的颜色
    private int colorFingerUp;         //手指抬起时的效果
    private int colorFingerOn;         //手指放上的颜色
    private int tryTimes;              //尝试次数
    private int count;                 //行列各有几个
    private Path path;                 //轨迹
    private Paint mPaint;              //画笔

    int cWidth;                      //child的宽度
    private float spaceBetwenChildRate = 0.25f;  //两个子视图之间间隔距离比率
    private float pathRate = 0.29f;    //设置连线的比率比内圆圈稍微大点
    private GestureView[] gestureViews;
    private LinkedList<Integer> choseAnswer;
    private int[] answer;

    float lastX = 0;        //指引线开始x坐标
    float lastY = 0;        //指引线y坐标
    float endX = 0;         //指引线结束x坐标
    float endY = 0;         //指引线结束y坐标

    private GestureListener gestureListener;//事件的回调

    public void setGestureListener(GestureListener gestureListener) {
        this.gestureListener = gestureListener;
    }

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
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        path = new Path();
        choseAnswer = new LinkedList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        width = Math.min(width, height);
        cWidth = 4 * width / (5 * count + 1);

        if (gestureViews == null) {
            gestureViews = new GestureView[count * count];
            for (int i = 0; i < gestureViews.length; i++) {
                GestureView gestureView = new GestureView(getContext(), colorInterNoFinger, colorOuterNoFinger
                        , colorFingerOn, colorFingerUp);
                gestureView.setId(i + 1);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(cWidth, cWidth);
                layoutParams.rightMargin = (int) (cWidth * spaceBetwenChildRate);
                layoutParams.bottomMargin = (int) (cWidth * spaceBetwenChildRate);
                if (i < count) {
                    layoutParams.topMargin = (int) (cWidth * spaceBetwenChildRate);
                }
                if (i % count == 0) {
                    layoutParams.leftMargin = (int) (cWidth * spaceBetwenChildRate);
                }
                if (i % count != 0) {
                    layoutParams.addRule(RIGHT_OF, i);
                }
                if (i >= count) {
                    layoutParams.addRule(BELOW, i - count + 1);
                }
                gestureViews[i] = gestureView;
                this.addView(gestureView, layoutParams);
            }
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        mPaint.setStrokeWidth(cWidth * pathRate);
        mPaint.setAlpha(50);
        canvas.drawPath(path, mPaint);
        canvas.drawLine(lastX, lastY, endX, endY, mPaint);
    }


    boolean draw = true;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!draw) return true;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                reset();
                break;
            case MotionEvent.ACTION_MOVE:
                float pointX = event.getX();
                float pointY = event.getY();
                GestureView gestureView = getChildViewByXy(pointX, pointY);
                if (gestureView != null) {
                    int gid = gestureView.getId();
                    if (!choseAnswer.contains(gid)) {
                        gestureView.setStatus(GestureView.STATUS.FINGER_ON);
                        lastX = (gestureView.getRight() + gestureView.getLeft()) / 2;
                        lastY = (gestureView.getBottom() + gestureView.getTop()) / 2;
                        if (choseAnswer.size() == 0)
                            path.moveTo(lastX, lastY);
                        else
                            path.lineTo(lastX, lastY);
                        choseAnswer.add(gid);
                    }
                }
                endX = pointX;
                endY = pointY;
                mPaint.setColor(colorFingerOn);
                break;
            case MotionEvent.ACTION_UP:
                if (answer == null) {
                    setAnswer(choseAnswer);
                    reset();
                    Toast.makeText(getContext(), "设置密码成功", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (choseAnswer.size() != answer.length) {
                    tryTimes--;
                } else {
                    boolean flag = true;
                    for (int i = 0; i < choseAnswer.size(); i++) {
                        if (answer[i] != choseAnswer.get(i)) {
                            flag = false;
                            tryTimes--;
                            break;
                        }
                    }
                    if (flag) {
                        gestureListener.onAnswerRight();
                        return true;
                    }
                }
                if (tryTimes <= 0) {
                    gestureListener.onUnmatchedExceedBoundary();
                    draw = false;
                }
                mPaint.setColor(colorFingerUp);    //设置画笔颜色
                endX = lastX;                      //将终点设置为起点  取消指引线
                endY = lastY;
                changeChildStatus();               //改变子元素的状态为UP
                break;
        }
        invalidate();
        return true;
    }

    /**
     * 复位 将状态恢复
     */
    public void reset() {
        choseAnswer.clear();
        path.reset();
        for (GestureView gestureView : gestureViews) {
            gestureView.setStatus(GestureView.STATUS.FINGER_NO);
        }
    }


    /**
     * 返回x y坐标所在的child
     *
     * @param x
     * @param y
     * @return
     */
    public GestureView getChildViewByXy(float x, float y) {
        for (int i = 0; i < gestureViews.length; i++) {
            if (checkPositonInChild(i, x, y)) {
                return gestureViews[i];
            }
        }
        return null;
    }

    /**
     * 判断坐标是否在下标为position的child中
     *
     * @param position
     * @param x
     * @param y
     * @return
     */
    public boolean checkPositonInChild(int position, float x, float y) {
        GestureView gestureView = gestureViews[position];
        if (gestureView.getLeft() < x && x < gestureView.getRight()
                && gestureView.getTop() < y && y < gestureView.getBottom()) {
            return true;
        }
        return false;
    }

    /**
     * 改成抬起的状态
     */
    public void changeChildStatus() {
        for (GestureView gestureView : gestureViews) {
            gestureView.setStatus(GestureView.STATUS.FINGER_UP);
        }
    }

    /**
     * 设置答案
     *
     * @param answers
     */
    public void setAnswer(LinkedList<Integer> answers) {
        answer = new int[answers.size()];
        for (int i = 0; i < answers.size(); i++) {
            answer[i] = answers.get(i);
        }
    }


    public interface GestureListener {

        /**
         * 当答案错误
         */
        void onAnswerRight();

        /**
         * 超过最大尝试次数
         */
        void onUnmatchedExceedBoundary();
    }

}
