package com.example.demo_list;

import android.graphics.Bitmap;

import static android.R.attr.bitmap;

/**
 * Created by 秦 on 2016/11/3.
 */

public class Animal {
    private String aName;
    private String aSpeak;
    private Bitmap aIcon;

    public Animal () {

    }

    public Animal(String aName, String aSpeak, Bitmap aIcon) {
        this.aName = aName;
        this.aSpeak = aSpeak;
        this.aIcon = aIcon;
    }

    public String getaName() {
        return aName;
    }

    public String getaSpeak() {
        return aSpeak;
    }

    public Bitmap getaIcon() {
        return aIcon;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public void setaSpeak(String aSpeak) {
        this.aSpeak = aSpeak;
    }

    public void setaIcon(Bitmap aIcon) {
        this.aIcon = aIcon;
    }

}
