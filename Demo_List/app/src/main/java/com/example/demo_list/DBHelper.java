package com.example.demo_list;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by 秦 on 2016/11/5.
 */

class DBHelper extends SQLiteOpenHelper {
    //数据库版本号
    private static final int DATABASE_VERSION=4;

    //数据库名称
    private static final String DATABASE_NAME="crud.db";

    DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据表
        String CREATE_TABLE_STUDENT="CREATE TABLE "+ Student.TABLE+"("
                +Student.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +Student.KEY_name+" TEXT, "
                +Student.KEY_num+" TEXT，"
                +Student.S_ima+" BLOB,"
                +Student.S_ima+" BLOB"+             //鬼知道为什么数据库忽略最后一行的代码
                ")";
        db.execSQL(CREATE_TABLE_STUDENT);
        //创建数据表
        String CREATE_TABLE_CLASS="CREATE TABLE Class ( id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "QingKuang TEXT, " +
                "num TEXT，" +
                "Student_Id INTEGER,"+
                "Student_Id INTEGER,"+
                "week INTEGER，" +
                "Student_Id INTEGER,"+
                "time INTEGER，" +
                "time INTEGER)";
              //  "foreign key (time) references Student(id) on delete cascade)" ;        //好吧，不是最后一行，是所有行都闹鬼了
        db.execSQL(CREATE_TABLE_CLASS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //如果旧表存在，删除，所以数据将会消失
        db.execSQL("DROP TABLE IF EXISTS "+ Student.TABLE);

        //再次创建表
        onCreate(db);
    }
}
