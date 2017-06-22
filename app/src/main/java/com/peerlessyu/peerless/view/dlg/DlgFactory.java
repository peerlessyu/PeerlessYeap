package com.peerlessyu.peerless.view.dlg;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.peerlessyu.peerless.bean.Book;
import com.peerlessyu.peerless.dao.OnAddBookListener;
import com.peerlessyu.peerless.dao.OnInputJsonListener;


/**
 * Created by Administrator on 2017/6/6.
 */

public class DlgFactory {
    public static Dialog showAddBookDlg(Context context, Book book, OnAddBookListener listener) {
        AddBookDlg dialog = new AddBookDlg(context, listener, book);
        settingDlg(dialog);
        return dialog;
    }

    public static Dialog showOutput(Context context, String msg) {
        OutputDlg dialog = new OutputDlg(context, msg);
        settingDlg(dialog);
        return dialog;
    }

    public static Dialog showInput(Context context, OnInputJsonListener msg) {
        InputDlg dialog = new InputDlg(context, msg);
        settingDlg(dialog);
        return dialog;
    }

    private static void settingDlg(Dialog dialog) {
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();// 设置Dialog的位置
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);// 垂直居中和水平居中
        dialogWindow.setAttributes(lp);
        try {
            dialog.show();
        } catch (Exception e) {
        }
    }
}
