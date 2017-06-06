package com.peerlessyu.peerless.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/5/27.
 */

public class Idiom extends DataSupport{
    private String name;
    private String ex;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }
}
