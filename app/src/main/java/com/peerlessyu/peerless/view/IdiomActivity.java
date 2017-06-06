package com.peerlessyu.peerless.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.peerlessyu.peerless.R;
import com.peerlessyu.peerless.bean.Idiom;
import com.peerlessyu.peerless.rv.MultiItemTypeAdapter;
import com.peerlessyu.peerless.rv.RvCommonAdapter;
import com.peerlessyu.peerless.rv.ViewHolder;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class IdiomActivity extends Activity {
    private RecyclerView rv;
    private EditText et, etName, etEx;
    private List<Idiom> list = new ArrayList<>();
    private RvCommonAdapter<Idiom> adapter;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_idiom_main);
        Connector.getDatabase();
        rv = (RecyclerView) findViewById(R.id.rv);
        et = (EditText) findViewById(R.id.et);
        etName = (EditText) findViewById(R.id.et_name);
        etEx = (EditText) findViewById(R.id.et_ex);
        rv.setLayoutManager(new LinearLayoutManager(this));
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String d = editable.toString();
                i = 0;
                if (!TextUtils.isEmpty(d) && list.size() > 0) {
                    for (Idiom idiom : list) {
                        if (idiom.getName().contains(d)) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    rv.smoothScrollToPosition(i);
                                }
                            });
                            break;
                        }
                        i++;
                    }
                }
            }
        });
        adapter = new RvCommonAdapter<Idiom>(this, R.layout.lv_item, list) {
            @Override
            protected void convert(ViewHolder holder, Idiom idiom, int position) {
                holder.setText(R.id.tv_name, idiom.getId() + ":" + idiom.getName());
                holder.setText(R.id.tv_ex, idiom.getEx());
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, final int position) {
                new AlertDialog.Builder(IdiomActivity.this).setMessage("delete this item?").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = list.get(position).getId();
                        DataSupport.delete(Idiom.class, id);
                        resetList();
                    }
                }).create().show();
                return true;
            }
        });
        rv.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etName.getText().toString()) && !TextUtils.isEmpty(etEx.getText().toString())) {
                    Idiom idiom = new Idiom();
                    idiom.setName(etName.getText().toString());
                    idiom.setEx(etEx.getText().toString());
                    idiom.save();
                    list.add(idiom);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        resetList();
    }

    private void resetList() {
        List<Idiom> idioms = DataSupport.findAll(Idiom.class);
        if (idioms != null) {
            list.clear();
            list.addAll(idioms);
            adapter.notifyDataSetChanged();
        }
    }

}
