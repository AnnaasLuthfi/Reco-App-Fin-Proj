package com.example.nyobasebelumfinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddChapter extends AppCompatActivity {
    EditText chapter_name, comic_id;
    Button button_add, button_chooseImage;
    ImageView back, imageView;

    DBHelper db;

    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chapter);

        db = new DBHelper(AddChapter.this);
        chapter_name = findViewById(R.id.chapter_Title);
        comic_id = findViewById(R.id.comic_Id);
        imageView = findViewById(R.id.imageView);
        button_add = findViewById(R.id.ButtonAdd);
        back = findViewById(R.id.button_back);
        button_chooseImage = findViewById(R.id.ButtonChooseImage);


//        tombol back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddChapter.this, AfterLogReg.class);
                startActivity(intent);
                finish();
            }
        });


//        ADD DATA
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AddChapterName = chapter_name.getText().toString().trim();
                String AddComicId = comic_id.getText().toString().trim();
                ContentValues cv = new ContentValues();

                if(AddChapterName.equals("") || AddComicId.equals("")){
                    Toast.makeText(getApplicationContext(), "Name and Category not null", Toast.LENGTH_SHORT).show();
                }else{
                    cv.put(DBHelper.row_comic_chapter_id, AddComicId);
                    cv.put(DBHelper.row_chapter_title, AddChapterName);
                    cv.put(DBHelper.row_chapter_image, imageViewToByte(imageView));
                    db.addChapter(cv);
                    imageView.setImageResource(R.mipmap.ic_launcher);
                    Toast.makeText(getApplicationContext(),"Add Comic Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddChapter.this,AfterLogReg.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

//        tombol choose image
        button_chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(AddChapter.this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE
                }, REQUEST_CODE_GALLERY);
            }
        });
    }

    //    convert image ke byte
    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    //    buat akses ke gallery
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");      //target untuk ngambil gambar
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }else{
                Toast.makeText(getApplicationContext(), "You don.t have permission to acces file", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //    buat bisa liat fotonya
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                //YG BAGIAN INI PASING FOTONYA DARI GALRI KE APPS
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
