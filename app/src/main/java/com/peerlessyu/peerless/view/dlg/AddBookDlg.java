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

public class AddBookDlg extends Dialog implements View.OnClickListener {

    private OnAddBookListener listener;
    private EditText etName;
    private EditText etPage;
    private ImageButton ibPre, ibNext, ibOk;
    private Book book;

    public AddBookDlg(@NonNull Context context, OnAddBookListener listener, Book book) {
        this(context, R.style.dialog);
        this.listener = listener;
        this.book = book;
    }

    public AddBookDlg(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_add_book);
        etName = (EditText) findViewById(R.id.dlg_add_book_et_name);
        etPage = (EditText) findViewById(R.id.dlg_add_book_et_page);
        ibPre = (ImageButton) findViewById(R.id.dlg_add_book_btn_sub);
        ibNext = (ImageButton) findViewById(R.id.dlg_add_book_btn_add);
        ibOk = (ImageButton) findViewById(R.id.dlg_add_book_btn_commit);
        ibPre.setOnClickListener(this);
        ibNext.setOnClickListener(this);
        ibOk.setOnClickListener(this);
        if (book != null) {
            etName.setText(book.getName());
            etPage.setText(book.getPage());
            currentPage = Integer.valueOf(book.getPage());
        }
        etPage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentPage = Integer.valueOf(editable.toString());
            }
        });
    }

    private int currentPage = 0;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dlg_add_book_btn_sub:
                if (currentPage - 1 >= 0) {
                    currentPage--;
                    etPage.setText(currentPage + "");
                }
                break;
            case R.id.dlg_add_book_btn_add:
                currentPage++;
                etPage.setText(currentPage + "");
                break;
            case R.id.dlg_add_book_btn_commit:
                if (!TextUtils.isEmpty(etName.getText().toString()) && !TextUtils.isEmpty(etPage.getText().toString())) {
                    if (listener != null) {
                        listener.onBookAdd(book == null ? -1 : book.getId(), etName.getText().toString(), etPage.getText().toString(), new Date());
                        dismiss();
                    }
                }
                break;

        }
    }
}
