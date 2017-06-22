package com.peerlessyu.peerless.view.dlg;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.peerlessyu.peerless.R;
import com.peerlessyu.peerless.bean.Book;
import com.peerlessyu.peerless.dao.OnAddBookListener;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/6.
 */

public class OutputDlg extends Dialog {

    private EditText etName;
    private String msg;

    public OutputDlg(@NonNull Context context, String msg) {
        this(context, R.style.dialog);
        this.msg = msg;
    }

    public OutputDlg(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_output);
        etName = (EditText) findViewById(R.id.dlg_output_et);
        if (!TextUtils.isEmpty(msg)) {
            etName.setText(msg);
        }
    }

}
