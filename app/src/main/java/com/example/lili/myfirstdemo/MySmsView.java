package com.example.lili.myfirstdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by 13096 on 2018/7/29.
 */

public class MySmsView extends LinearLayout implements TextWatcher, View.OnKeyListener {

    int count;
    int normalRes;   //child的背景
    int foceRes;     //选中的背景
    int childWidth;  //child宽度
    int childPadding;//child的的margin
    ArrayList<EditText> editTexts;

    public MySmsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs
                , R.styleable.MySmsView, 0, 0);
        count = typedArray.getInt(R.styleable.MySmsView_count, 4);
        normalRes = typedArray.getResourceId(R.styleable.MySmsView_child_normal_background, R.drawable.sms_normal_res);
        foceRes = typedArray.getResourceId(R.styleable.MySmsView_child_foces_background, R.drawable.sms_normal_res);
        childWidth = typedArray.getDimensionPixelSize(R.styleable.MySmsView_childWidth,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40
                        , getResources().getDisplayMetrics()));
        childPadding = typedArray.getDimensionPixelSize(R.styleable.MySmsView_childPadding
                , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
                        , 20, getResources().getDisplayMetrics()));
        typedArray.recycle();
        initViews();
    }

    public void initViews() {
        editTexts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            EditText editText = new EditText(getContext());
            LinearLayout.LayoutParams layoutParams = new LayoutParams(childWidth, childWidth);
            layoutParams.gravity = Gravity.CENTER_VERTICAL;
            layoutParams.leftMargin = childPadding;
            layoutParams.rightMargin = childPadding;
            editText.setMaxEms(1);
            editText.setGravity(Gravity.CENTER);
            editText.setBackgroundResource(normalRes);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
            editText.setOnKeyListener(this);
            editText.setCursorVisible(false);
            editText.addTextChangedListener(this);
            editText.setFocusable(false);
            editText.setFocusableInTouchMode(false);
            addView(editText, layoutParams);
            editTexts.add(editText);
        }
        editTexts.get(0).setFocusable(true);
        editTexts.get(0).setFocusableInTouchMode(true);
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_DEL) {
            for (int j = count - 1; j >= 0; j--) {
                if (editTexts.get(j).getText().length() != 0) {
                    editTexts.get(j).setText("");
                    break;
                }
            }
        }
        return false;
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i0, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        boolean b = true;
        for (int i = 0; i < count; i++) {
            String str = editTexts.get(i).getText().toString();
            if (str.length() == 0) {
                editTexts.get(i).setFocusable(b);
                editTexts.get(i).setFocusableInTouchMode(b);
                if (b) {
                    editTexts.get(i).requestFocus();
                    b = false;
                }
            } else {
                editTexts.get(i).setFocusable(false);
                editTexts.get(i).setFocusableInTouchMode(false);
            }
        }
        if (b) {
            editTexts.get(count - 1).setFocusable(true);
            editTexts.get(count - 1).setFocusableInTouchMode(true);
            editTexts.get(count - 1).requestFocus();
        }
    }
}
