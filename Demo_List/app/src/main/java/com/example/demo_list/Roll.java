package com.example.demo_list;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static android.R.attr.bitmap;
import static android.R.attr.button;


/**
 * Created by 秦 on 2016/11/7.
 */

public class Roll extends AppCompatActivity {
    String[] Snum=new String[200];
    int ID[]=new int[200];
    Student student;
    TextView t_name;
    TextView t_num;
    ImageView i_icon;
    int weeks;
    int times;
    int count;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roll);
        Intent intent=getIntent();
        Bundle bd=intent.getExtras();
        weeks=bd.getInt("weeks");
        times=bd.getInt("times");
        count=bd.getInt("count");
        Snum=bd.getStringArray("Snum");
        ID=bd.getIntArray("ID");
        set();
    }

    public void onClick(View view) {
        ClassRepo classRepo=new ClassRepo(this);
        Class c=new Class();
        Button button=(Button)view;
        c.num=Snum[id-1];
        c.QingKuang=button.getText().toString();
        c.week=weeks;
        c.time=times;
        c.Student_Id=ID[id-1];
        classRepo.insert(c);
        set();
    }

    public void set(){
        t_name=(TextView) findViewById(R.id.t_name);
        t_num=(TextView) findViewById(R.id.t_num);
        i_icon=(ImageView)findViewById(R.id.icon);

        if(id<count) {
            StudentRepo repo=new StudentRepo(this);
            student = repo.getStudentById(Snum[id++]);
            t_name.setText(student.name);
            t_num.setText(student.num);
            Bitmap imagebitmap = BitmapFactory.decodeByteArray(student.ima, 0, student.ima.length);//转化为Bitmap
            i_icon.setImageBitmap(imagebitmap);
        }
        else{
            Intent intent =new Intent(Roll.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
