package com.example.demo_list;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 秦 on 2016/11/11.
 */

public class ClassRepo {
    private DBHelper dbHelper;

    public ClassRepo(Context context){
        dbHelper=new DBHelper(context);
    }

    public int insert(Class c){
        //打开连接，写入数据
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO Class(QingKuang,week,time,num,Student_Id) values(?,?,?,?,?)",
                new Object[]{c.QingKuang,c.week,c.time,c.num,c.Student_Id});
        return 0;
    }

    public ArrayList<HashMap<String,String>> getClassById(String num){                      //根据学号查找学生上课状态
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT QingKuang,week,time"+
                " FROM Class"+
                " WHERE num=?";
        ArrayList<HashMap<String,String>> ClassList=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{num});
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> c=new HashMap<String,String>();
                c.put("QingKuang",cursor.getString(cursor.getColumnIndex("QingKuang")));
                c.put("week",cursor.getString(cursor.getColumnIndex("week")));
                c.put("time",cursor.getString(cursor.getColumnIndex("time")));
                ClassList.add(c);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ClassList;
    }
}
