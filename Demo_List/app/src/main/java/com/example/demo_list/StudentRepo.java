package com.example.demo_list;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;

import java.util.ArrayList;
import java.util.HashMap;


public class StudentRepo {
    private DBHelper dbHelper;

    public StudentRepo(Context context){
        dbHelper=new DBHelper(context);
    }

        public int insert(Student student){                         //添加学生信息
            //打开连接，写入数据
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            db.execSQL("INSERT INTO Student(name,num,ima) values(?,?,?)",
                    new Object[]{student.name,student.num,student.ima});
        /*long student_Id=db.insert(Student.TABLE,null,values);
        db.close();
        return (int)student_Id;*/
        return 0;
    }

    public void delete(String num){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(Student.TABLE,Student.KEY_num+"=?", new String[]{num});
        db.close();
    }

    public void update(Student student,String num){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.execSQL("UPDATE Student SET name = ?,num = ?,ima = ? WHERE num = ?",
                new Object[]{student.name,student.num,student.ima,num});
    }

    public ArrayList<HashMap<String, String>> getStudentList(){     //返回所有学生信息
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                Student.KEY_ID+","+
                Student.KEY_name+","+
                Student.KEY_num+","+
                Student.S_ima+" FROM "+Student.TABLE+
                " ORDER BY "+Student.KEY_num+" ASC";
        ArrayList<HashMap<String,String>> studentList=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> student=new HashMap<String,String>();
                student.put("id",cursor.getString(cursor.getColumnIndex(Student.KEY_ID)));
                student.put("name",cursor.getString(cursor.getColumnIndex(Student.KEY_name)));
                student.put("num",cursor.getString(cursor.getColumnIndex(Student.KEY_num)));
                student.put("ima",Base64.encodeToString(cursor.getBlob(cursor.getColumnIndex(Student.S_ima)), Base64.DEFAULT));
                studentList.add(student);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return studentList;
    }

    public Student getStudentById(String num){                      //根据学号查找学生信息
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                Student.KEY_name + "," +
                Student.KEY_num +"," +
                Student.S_ima+
                " FROM " + Student.TABLE
                + " WHERE " +
                Student.KEY_num + "=?";
        Student student=new Student();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{num});
        if(cursor.moveToFirst()){
            do{
                student.name =cursor.getString(cursor.getColumnIndex(Student.KEY_name));
                student.num =cursor.getString(cursor.getColumnIndex(Student.KEY_num));
                student.ima=cursor.getBlob(cursor.getColumnIndex(Student.S_ima));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return student;
    }

    public int getCount()                                       //返回学生人数
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT COUNT (*) FROM Student",null);
        cursor.moveToFirst();
        int result = cursor.getInt(0);
        cursor.close();
        db.close();
        return result;
    }

}
