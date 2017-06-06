package com.peerlessyu.peerless.view.dlg;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.peerlessyu.peerless.bean.Book;
import com.peerlessyu.peerless.dao.OnAddBookListener;


/**
 * Created by Administrator on 2017/6/6.
 */

public class DlgFactory {
    public static Dialog showAddBookDlg(Context context, Book book, OnAddBookListener listener) {
        AddBookDlg dialog = new AddBookDlg(context, listener, book);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();// 设置Dialog的位置
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);// 垂直居中和水平居中
        dialogWindow.setAttributes(lp);
        try {
            dialog.show();
        } catch (Exception e) {
        }
        return dialog;
    }
}
