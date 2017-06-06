package com.peerlessyu.peerless.bean;

import android.app.Activity;

/**
 * Created by Administrator on 2017/6/6.
 */

public class FuncBean {
    private String name;
    private Class activity;

    public FuncBean(String name, Class activity) {
        this.name = name;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getActivity() {
        return activity;
    }

    public void setActivity(Class activity) {
        this.activity = activity;
    }
}
