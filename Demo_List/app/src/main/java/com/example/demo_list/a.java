package com.example.demo_list;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 秦 on 2016/11/5.
 */

public class a extends AppCompatActivity {

    Bitmap bitmap1;
    private EditText e_Name;
    private EditText e_Num;
    private ImageView i_icon;
    private int _student_id = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        e_Name = (EditText) findViewById(R.id.e_Name);
        e_Num = (EditText) findViewById(R.id.e_Num);
        i_icon = (ImageView) findViewById(R.id.i_icon);

        _student_id = 0;
        Intent intent = getIntent();
        _student_id = intent.getIntExtra("student_Id", 0);
        StudentRepo repo = new StudentRepo(this);
    }

    public void cSava(View v) {
        if (v == findViewById(R.id.btnSave)) {
            StudentRepo repo = new StudentRepo(this);
            Student student = new Student();
            student.name = e_Name.getText().toString();
            student.num = e_Num.getText().toString();
            student.ima=getImg();
            student.student_ID = _student_id;

            if (_student_id == 0) {
                _student_id = repo.insert(student);
                Toast.makeText(this, "New Student Insert", Toast.LENGTH_SHORT).show();
            }
        }
        Intent intent = new Intent(a.this, MainActivity.class);
        startActivity(intent);
    }


    public byte[] getImg()                      //将图片转化为BLOB
    {
        //Bitmap bitmap1= BitmapFactory.decodeResource(getResources(), R.drawable.pika);            //将文件里的图片转化为Bitmap
        Bitmap bitmap1=drawableToBitmap(i_icon.getDrawable());
        int size=bitmap1.getWidth()*bitmap1.getHeight()*4;
        ByteArrayOutputStream baos=new ByteArrayOutputStream(size);
        bitmap1.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public Bitmap drawableToBitmap(Drawable drawable)               // drawable 转换成bitmap
    {
        int width = drawable.getIntrinsicWidth();                   // 取drawable的长宽
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ?Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565;// 取drawable的颜色格式
        Bitmap bitmap = Bitmap.createBitmap(width, height, config); // 建立对应bitmap
        Canvas canvas = new Canvas(bitmap);                         // 建立对应bitmap的画布
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);                                      // 把drawable内容画到画布中
        return bitmap;
    }

    public void gallery(View view) {                                //打开系统相册
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }
                                                                    //返回选择图片
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ContentResolver resolver = getContentResolver();
        if (requestCode == 1) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                try {
                    bitmap1 = MediaStore.Images.Media.getBitmap(resolver, uri);
                    i_icon.setImageBitmap(bitmap1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
