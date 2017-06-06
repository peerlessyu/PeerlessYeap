package com.peerlessyu.peerless.bean;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/6.
 */

public class Book extends DataSupport{
    private int id;
    private Date date;
    private String name;
    private String page;

    public Book() {
    }

    public Book(Date date, String name, String page) {
        this.date = date;
        this.name = name;
        this.page = page;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
