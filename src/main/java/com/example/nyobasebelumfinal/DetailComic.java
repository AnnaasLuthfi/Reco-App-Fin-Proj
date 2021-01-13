package com.example.nyobasebelumfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailComic extends AppCompatActivity {

    TextView titleComic, categoryComic;
    ImageView btnBack;
    ImageView imageComic;
//    ImageView imageComic = new ImageView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_comic);

//        kembali ke home after login
        btnBack = findViewById(R.id.button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailComic.this, AfterLogReg.class);
                startActivity(intent);
                finish();
            }
        });


//        munculin data
        passingData();

    }

    private void passingData() {

        titleComic = findViewById(R.id.titleA);
        categoryComic = findViewById(R.id.categoryComic);
        imageComic = findViewById(R.id.imageComicDetail);

        String title = "";
        String category = "";
        Bitmap bmp = null;
        ImageView image = null;

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            title = extras.getString("comic_name");
            category = extras.getString("comic_category");
//            image.setImageBitmap(getImage("imageComic"));
        }
        titleComic.setText(title);
        categoryComic.setText(category);
        imageComic.setImageResource(getIntent().getIntExtra("image",0));

//        byte[] byteArray = getIntent().getByteArrayExtra("image");
//        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//        imageComic.setImageBitmap(bmp);

//        if(getIntent().hasExtra("byteArray")) {
//            b = BitmapFactory.decodeByteArray(
//                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
//            imageComic.setImageBitmap(b);
//        }



    }

}