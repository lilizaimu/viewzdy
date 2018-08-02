package com.example.lili.myfirstdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 13096 on 2018/7/30.
 */

public class FlowLayout extends ViewGroup {


    ArrayList<View> views;

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int count = getChildCount();

        float maxWidth = 0;
        float allHeight = 0;
        float lineHeight = 0;
        float lineWidth = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = layoutParams.width;
            int childHeight = layoutParams.height;

            if (lineWidth + childWidth < widthSize) {
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth = childWidth + lineWidth;
            } else {
                maxWidth = Math.max(lineWidth, maxWidth);
                allHeight += lineHeight;
                lineHeight = 0;
                lineWidth = 0;
            }
            if (i == count - 1) {  //如果是最后一个
                lineHeight = Math.max(lineHeight, childHeight);
                allHeight += lineHeight;
                maxWidth = Math.max(maxWidth, childWidth);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new ViewGroup.MarginLayoutParams(getContext(), attrs);
    }
}
