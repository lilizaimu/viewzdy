package com.example.lili.myfirstdemo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * 单例模式
 * Created by 13096 on 2018/7/20.
 */

public class DialogInstance extends DialogFragment {

    Dialog dialog;
    private static DialogInstance instance;

    public static DialogInstance newInstance() {
        if (instance == null) {
            synchronized (DialogInstance.class) {
                if (instance == null) {
                    instance = new DialogInstance();
                }
            }
        }
        return instance;
    }

    public void showDialog(FragmentManager fragmentManager) {
        if (instance != null)
            instance.show(fragmentManager, "");
    }

    public void hideDialog() {
        if (instance != null) {
            instance.dismiss();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogfragment_layout);
        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.dimAmount = 0.1f;
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
        return dialog;
    }
}
