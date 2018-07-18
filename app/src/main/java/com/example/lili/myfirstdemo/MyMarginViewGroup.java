package com.example.lili.myfirstdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 13096 on 2018/7/18.
 */

public class MyMarginViewGroup extends ViewGroup {

    public MyMarginViewGroup(Context context) {
        this(context, null);
    }

    public MyMarginViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyMarginViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onLayout(boolean change, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int cl = 0, ct = 0;
            switch (i) {
                case 0:
                    cl = layoutParams.leftMargin;
                    ct = layoutParams.topMargin;
                    break;
                case 1:
                    cl = getMeasuredWidth() - layoutParams.rightMargin - child.getMeasuredWidth();
                    ct = layoutParams.topMargin;
                    break;
                case 2:
                    cl = getMeasuredWidth() - layoutParams.rightMargin - child.getMeasuredWidth();
                    ct = getMeasuredHeight() - layoutParams.bottomMargin - child.getMeasuredHeight();
                    break;
                case 3:
                    cl = layoutParams.leftMargin;
                    ct = getMeasuredHeight() - child.getMeasuredHeight() - layoutParams.bottomMargin;
                    break;
            }
            int cr = cl + child.getMeasuredWidth();
            int cb = ct + child.getMeasuredHeight();
            child.layout(cl, ct, cr, cb);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;

        int lh = 0, rh = 0, tw = 0, bw = 0;

        measureChildren(widthMeasureSpec, heightMeasureSpec);       //测量每一个child的宽高

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            switch (i) {
                case 0:
                    lh = lh + child.getMeasuredHeight() + layoutParams.bottomMargin + layoutParams.topMargin;
                    tw = tw + child.getMeasuredWidth() + layoutParams.rightMargin + layoutParams.leftMargin;
                    break;
                case 1:
                    rh = rh + child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
                    tw = tw + child.getMeasuredWidth() + layoutParams.rightMargin + layoutParams.leftMargin;
                    break;
                case 2:
                    rh = rh + child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
                    bw = bw + child.getMeasuredWidth() + layoutParams.rightMargin + layoutParams.leftMargin;
                    break;
                case 3:
                    lh = lh + child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
                    bw = bw + child.getMeasuredWidth() + layoutParams.rightMargin + layoutParams.leftMargin;
                    break;
            }
        }
        width = Math.max(bw, tw);
        height = Math.max(rh, lh);
        Log.d("TAG", width + ":" + height);
        setMeasuredDimension(widthMode == MeasureSpec.AT_MOST ? width : widthSize, heightMode == MeasureSpec.AT_MOST ? height : heightSize);
    }
}
