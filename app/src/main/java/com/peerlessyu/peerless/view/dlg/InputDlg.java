package com.peerlessyu.peerless.view.dlg;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.peerlessyu.peerless.R;
import com.peerlessyu.peerless.dao.OnInputJsonListener;

/**
 * Created by Administrator on 2017/6/6.
 */

public class InputDlg extends Dialog {

    private EditText etName;
    private Button btn;
    private OnInputJsonListener listener;

    public InputDlg(@NonNull Context context, OnInputJsonListener listener) {
        this(context, R.style.dialog);
        this.listener = listener;
    }

    public InputDlg(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_input);
        etName = (EditText) findViewById(R.id.dlg_input_et);
        btn = (Button) findViewById(R.id.dlg_input_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null && !TextUtils.isEmpty(etName.getText().toString())) {
                    listener.onInputJson(etName.getText().toString());
                    dismiss();
                }
            }
        });
    }

}
