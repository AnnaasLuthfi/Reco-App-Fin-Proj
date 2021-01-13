package com.example.nyobasebelumfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    //MyAdapter.RecyclerViewClikListener listener;
    DBHelper dbHelper;
    Button logout;
    ImageButton add;
    String getcategory;
    String catIndex;
    String test;

    RecyclerView recyclerView;
    ArrayList<String> comic_id;
    ArrayList<String> comic_name;
    ArrayList<String> comic_category;
    ArrayList<byte[]> comic_image;
    MyAdapter myAdapter;
    TextView tv_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        dbHelper = new DBHelper(CategoryActivity.this);
        catIndex = getIntent().getStringExtra("menus");
        tv_category = findViewById(R.id.tv_category);
        tv_category.setText(catIndex);
        ShowRecyclerView();


        //        NAVBAR
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
        comic_name = new ArrayList<>();
        comic_category = new ArrayList<>();
        comic_image = new ArrayList<>();

        storeDataInArrays();

        //setOnClikListener();        //otw ke detail
        int numberColoumns = 2;
        myAdapter = new MyAdapter(CategoryActivity.this, comic_id, comic_name, comic_category, comic_image);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberColoumns));
        recyclerView.setAdapter(myAdapter);
    }

    //    untuk recyler (ambil data)
    private void storeDataInArrays() {
        Cursor cursor = dbHelper.readAllData2(catIndex);
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                comic_id.add(cursor.getString(0));
                comic_name.add(cursor.getString(1));
                comic_category.add(cursor.getString(2));
                comic_image.add(cursor.getBlob(3));
            }
        }
    }
}