package com.example.demo_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 秦 on 2016/11/10.
 */

public class time extends AppCompatActivity {
    int weeks;
    int times;
    int count;
    int id=0;
    String[] Snum=new String[200];
    int ID[]=new int[200];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time);
        Intent intent=getIntent();
        Bundle bd=intent.getExtras();
        Snum=bd.getStringArray("Snum");
        ID=bd.getIntArray("ID");

        Spinner s1 = (Spinner) findViewById(R.id.s_weeks);
        Spinner s2 = (Spinner) findViewById(R.id.s_times);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                weeks=pos+1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                times=pos+1;
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void roll(View view) {
        StudentRepo repo=new StudentRepo(this);
        Intent intent =new Intent(time.this,Roll.class);
        Bundle bd=new Bundle();
        bd.putInt("weeks",weeks);
        bd.putInt("times",times);
        bd.putInt("count",repo.getCount());
        bd.putStringArray("Snum",Snum);
        bd.putIntArray("ID",ID);
        intent.putExtras(bd);
        startActivity(intent);
    }

    public int getWeeks(){
        return weeks;
    }

    public int getTimes(){
        return times;
    }

    public int getCount(){
        return count;
    }

    public int getId(){
        return id++;
    }
}
