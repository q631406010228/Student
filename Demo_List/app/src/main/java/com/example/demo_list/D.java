package com.example.demo_list;

import android.graphics.Bitmap;

/**
 * Created by 秦 on 2016/11/11.
 */

public class D {
    private String QingKuang;
    private int week;
    private int time;

    public D(String QingKuang, int week, int time) {
        this.QingKuang = QingKuang;
        this.week = week;
        this.time = time;
    }

    public String getQingKuang() {
        return QingKuang;
    }

    public int getWeek() {
        return week;
    }

    public int getTime() {
        return time;
    }
}
