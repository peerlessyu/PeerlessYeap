package com.peerlessyu.peerless.view;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.peerlessyu.peerless.R;
import com.peerlessyu.peerless.bean.Book;
import com.peerlessyu.peerless.bean.Idiom;
import com.peerlessyu.peerless.dao.OnAddBookListener;
import com.peerlessyu.peerless.rv.MultiItemTypeAdapter;
import com.peerlessyu.peerless.rv.RvCommonAdapter;
import com.peerlessyu.peerless.rv.ViewHolder;
import com.peerlessyu.peerless.view.dlg.DlgFactory;

import org.litepal.crud.DataSupport;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    private RvCommonAdapter<Book> adapter;
    private List<Book> list;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private Format format;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_book);
        rv = (RecyclerView) findViewById(R.id.book_rv);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        list = new ArrayList<>();
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        rv.setLayoutManager(new LinearLayoutManager(this));
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        adapter = new RvCommonAdapter<Book>(this, R.layout.item_book, list) {
            @Override
            protected void convert(ViewHolder holder, Book book, int position) {
                holder.setText(R.id.item_book_tv_name, book.getName());
                holder.setText(R.id.item_book_tv_page, book.getPage() + "");
                holder.setText(R.id.item_book_tv_date, format.format(book.getDate()));
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                DlgFactory.showAddBookDlg(BookActivity.this, list.get(position), new OnAddBookListener() {
                    @Override
                    public void onBookAdd(int id, String name, String page, Date date) {
                        Book book = new Book(date, name, page);
                        book.update(id);
                        resetList();
                    }
                });
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, final int position) {
                new AlertDialog.Builder(BookActivity.this).setMessage("delete this item (" + list.get(position).getName() + ")?").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = list.get(position).getId();
                        DataSupport.delete(Book.class, id);
                        resetList();
                    }
                }).create().show();
                return true;
            }
        });
        rv.setAdapter(adapter);
        fab = (FloatingActionButton) findViewById(R.id.book_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DlgFactory.showAddBookDlg(BookActivity.this, null, new OnAddBookListener() {
                    @Override
                    public void onBookAdd(int id, String name, String page, Date date) {
                        Book book = new Book(date, name, page);
                        book.save();
                        resetList();
                    }
                });
            }
        });
        resetList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_output:
                if (list != null && list.size() > 0) {
                    Gson gson = new Gson();
                    DlgFactory.showOutput(this, gson.toJson(list));
                }
                break;
            case R.id.action_input:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mian_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void resetList() {
        List<Book> books = DataSupport.findAll(Book.class);
        if (books != null) {
            list.clear();
            list.addAll(books);
            adapter.notifyDataSetChanged();
        }
    }
}
