package com.peerlessyu.peerless.dao;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/6.
 */

public interface OnAddBookListener {
    void onBookAdd(int id,String name, String page, Date date);
}
