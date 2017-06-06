package com.peerlessyu.peerless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.peerlessyu.peerless.bean.FuncBean;
import com.peerlessyu.peerless.rv.MultiItemTypeAdapter;
import com.peerlessyu.peerless.rv.RvCommonAdapter;
import com.peerlessyu.peerless.rv.ViewHolder;
import com.peerlessyu.peerless.view.IdiomActivity;

import java.util.ArrayList;
import java.util.List;

public class FunctionListActivity extends AppCompatActivity {
    private RvCommonAdapter<FuncBean> adapter;
    private List<FuncBean> list;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_function_list);
        rv = (RecyclerView) findViewById(R.id.func_rv);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        list = new ArrayList<>();
        list.add(new FuncBean(getString(R.string.Idiom), IdiomActivity.class));
        adapter = new RvCommonAdapter<FuncBean>(this, R.layout.item_func, list) {
            @Override
            protected void convert(ViewHolder holder, FuncBean s, int position) {
                holder.setText(R.id.item_func_tv, s.getName());
            }
        };
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                startActivity(new Intent(FunctionListActivity.this, list.get(position).getActivity()));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }
}
