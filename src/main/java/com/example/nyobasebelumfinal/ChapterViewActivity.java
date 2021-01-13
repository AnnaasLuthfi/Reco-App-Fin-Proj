package com.example.nyobasebelumfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ChapterViewActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Button logout;
    ImageButton add;
    String getcategory;
    String catIndex;
    String test;
    Integer paramChapter;

    RecyclerView recyclerView;
    ArrayList<String> chapter_id;
    ArrayList<String> comic_id;
    ArrayList<String> chapter_title;
    ArrayList<byte[]> chapter_image;
    MyAdapter myAdapter;
    ChapterAdapter chapterAdapter;
    TextView tv_name,tv_comic_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_view);

        //jika pakai id
//  String param = getIntent().getStringExtra("name");
//    paramChapter =Integer.parseInt(param);
//        tv_name = findViewById(R.id.tv_title);
//        tv_name.setText(param);


        dbHelper = new DBHelper(ChapterViewActivity.this);
        catIndex = getIntent().getStringExtra("name");
        ShowRecyclerView();

        tv_comic_title = findViewById(R.id.tv_comic_title);
        tv_comic_title.setText(catIndex);

        BottomNavigationView bottomNavigationView = findViewById(R.id.NavMainButtom);
        bottomNavigationView.setSelectedItemId(R.id.homes);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ViewProfile.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.buttonAdd:
                        startActivity(new Intent(getApplicationContext(), AddChapter.class));
                        overridePendingTransition(0,0);
                        finish();
                    case R.id.homes:
                        return true;
                }
                return false;
            }
        });

    }
    private void ShowRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        comic_id = new ArrayList<>();
        chapter_id = new ArrayList<>();
        chapter_title = new ArrayList<>();
        chapter_image = new ArrayList<>();

        storeDataInArrays();

        //setOnClikListener();        //otw ke detail
        int numberColoumns = 2;
        chapterAdapter = new ChapterAdapter(ChapterViewActivity.this, chapter_id, comic_id, chapter_title, chapter_image);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chapterAdapter);
    }

    //    untuk recyler (ambil data)
    private void storeDataInArrays() {
        Cursor cursor = dbHelper.readAllComic2(catIndex);
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                chapter_id.add(cursor.getString(0));
                comic_id.add(cursor.getString(1));
                chapter_title.add(cursor.getString(2));
                chapter_image.add(cursor.getBlob(3));
            }
        }
    }
}