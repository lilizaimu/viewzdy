package com.example.lili.myfirstdemo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * base弹窗接口
 * Created by 13096 on 2018/7/22.
 */

public class LBaseDialog extends DialogFragment {

    Dialog dialog;
    LDialogController lc;

    public LBaseDialog() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.basedialog_layout);
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.dimAmount = 0.1f;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        initView();
        return dialog;
    }

    public void initView() {
        Button btnCancle = dialog.findViewById(R.id.btn_cancle);
        btnCancle.setText(lc.cancelMsg);
        btnCancle.setOnClickListener(lc.cancleListener);
        Button btnConfirm = dialog.findViewById(R.id.btn_confirm);
        btnConfirm.setText(lc.confirmMsg);
        btnConfirm.setOnClickListener(lc.confirmListener);
        TextView tv = dialog.findViewById(R.id.tv_msg);
        tv.setText(lc.msg);
    }

    public void setController(LDialogController lc) {
        this.lc = lc;
    }

    public void showDialog(FragmentManager fragmentManager) {
        show(fragmentManager, "");
    }

    public void hideDialog() {
        dismiss();
    }


    public static class Builder {

        LDialogController lc;

        public Builder() {
            lc = new LDialogController();
        }

        public Builder setMessage(String ms) {
            lc.msg = ms;
            return this;
        }

        public Builder setConfirmButton(String msg, View.OnClickListener listener) {
            lc.confirmMsg = msg;
            lc.confirmListener = listener;
            return this;
        }

        public Builder setCancelButton(String msg, View.OnClickListener listener) {
            lc.cancelMsg = msg;
            lc.cancleListener = listener;
            return this;
        }

        public LBaseDialog build() {
            LBaseDialog lBaseDialog = new LBaseDialog();
            lBaseDialog.setController(lc);
            return lBaseDialog;
        }

    }


    public static class LDialogController {
        String msg;
        String confirmMsg;
        String cancelMsg;
        View.OnClickListener confirmListener;
        View.OnClickListener cancleListener;
    }

}
