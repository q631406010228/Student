package com.example.demo_list;

        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Base64;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.LinkedList;
        import java.util.List;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private List<Animal> mData = null;
    private Context mContext;
    private AnimalAdapter mAdapter = null;
    private ListView list_animal;
    HashMap<String, String> student;
    String[] Snum=new String[200];
    int ID[]=new int[200];
    private int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentRepo repo=new StudentRepo(this);
        ArrayList<HashMap<String,String>> studentList=repo.getStudentList();
        mData = new LinkedList<Animal>();
        if(studentList.size()!=0){
            while(i<studentList.size()) {
                student = studentList.get(i++);
                Snum[i-1]=student.get("num");       //得到升序的学号
                ID[i-1]=Integer.parseInt(student.get("id"));
                byte[] imagedata1= Base64.decode(student.get("ima"), Base64.DEFAULT);               //数据库中的图片转化为byte[]
                Bitmap imagebitmap= BitmapFactory.decodeByteArray(imagedata1, 0, imagedata1.length);//转化为Bitmap
                mData.add(new Animal(student.get("name"), student.get("num"), imagebitmap));
            }
        }
        mContext = MainActivity.this;
        mAdapter = new AnimalAdapter((LinkedList<Animal>) mData, mContext);
        list_animal = (ListView)findViewById(R.id.listView);
        list_animal.setAdapter(mAdapter);
        list_animal.setOnItemClickListener(this);

        ImageView iv=(ImageView) findViewById(R.id.fab);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this,time.class);
                Bundle bd=new Bundle();
                bd.putStringArray("Snum",Snum);
                bd.putIntArray("ID",ID);
                intent.putExtras(bd);
                startActivity(intent);
            }
        });
    }

    public void Plus(View view) {
        Intent intent =new Intent(MainActivity.this,a.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView content=(TextView) view.findViewById(R.id.t_num);
        Intent intent =new Intent(MainActivity.this,Detail.class);
        Bundle bd=new Bundle();
        bd.putString("num",(String)content.getText());
        intent.putExtras(bd);
        startActivity(intent);
    }
}
