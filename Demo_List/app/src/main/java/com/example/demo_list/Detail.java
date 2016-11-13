package com.example.demo_list;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 秦 on 2016/11/11.
 */

public class Detail extends AppCompatActivity {
    String num;
    private List<D> mData = null;
    private Context mContext;
    private DetailAdapter mAdapter = null;
    private ListView list_Class;
    int i=0;
    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        Intent intent=getIntent();
        Bundle bd=intent.getExtras();
        num=bd.getString("num");
        set();

        ClassRepo repo=new ClassRepo(this);
        mData = new LinkedList<D>();
        ArrayList<HashMap<String,String>> ClassList=repo.getClassById(num);
        if(ClassList.size()!=0){
            while(i<ClassList.size()) {
                HashMap<String,String> c=new HashMap<String,String>();
                c = ClassList.get(i++);
                mData.add(new D(c.get("QingKuang"), Integer.parseInt(c.get("week")), Integer.parseInt(c.get("time"))));
            }
        }
        mContext = Detail.this;
        mAdapter = new DetailAdapter((LinkedList<D>) mData, mContext);
        list_Class = (ListView)findViewById(R.id.l_detail);
        list_Class.setAdapter(mAdapter);
    }

    public void set(){
        TextView t_name=(TextView) findViewById(R.id.name);
        TextView t_num=(TextView) findViewById(R.id.num);
        ImageView i_ima=(ImageView)findViewById(R.id.i_ima);

        StudentRepo repo=new StudentRepo(this);
        Student student = repo.getStudentById(num);
        t_name.setText(student.name);
        t_num.setText(student.num);
        Bitmap imagebitmap = BitmapFactory.decodeByteArray(student.ima, 0, student.ima.length);//转化为Bitmap
        i_ima.setImageBitmap(imagebitmap);
    }

    public void Edit(View view) {
        Intent intent =new Intent(Detail.this,Detail_Edit.class);
        Bundle bd=new Bundle();
        bd.putString("num",num);
        intent.putExtras(bd);
        startActivity(intent);
    }

    public void Back(View view) {
        Intent intent =new Intent(Detail.this,MainActivity.class);
        startActivity(intent);
    }

    public void Delete(View view) {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(Detail.this);
        normalDialog.setIcon(R.drawable.miniions);
        normalDialog.setTitle("警报！");
        normalDialog.setMessage("你确定删除此同学?");
        normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        StudentRepo repo=new StudentRepo(mContext);
                        repo.delete(num);
                        Intent intent =new Intent(Detail.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
        normalDialog.setNegativeButton("关闭",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }
}
