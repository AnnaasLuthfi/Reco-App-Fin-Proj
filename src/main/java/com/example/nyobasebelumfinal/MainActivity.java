package com.example.nyobasebelumfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //    private ViewPager2 viewPager2;
//    private Handler sliderhandler = new Handler();
//    MyAdapter.RecyclerViewClikListener listener;    //ke detail
    ViewFlipper flipper;
    ImageButton login;

    private CardView card1, card2, card3, card4;

    DBHelper dbHelper;
    Button logout;
    ImageButton add;

    RecyclerView recyclerView;
    ArrayList<String> comic_id;
    ArrayList<String> comic_name;
    ArrayList<String> comic_category;
    ArrayList<byte[]> comic_image;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(MainActivity.this);

        //banner
        int images[] = {R.drawable.shinchan, R.drawable.age, R.drawable.eggnoid, R.drawable.onepiece, R.drawable.pasutrigaje, R.drawable.letsplay_s2};
        flipper = findViewById(R.id.flipperView);
        for (int image : images) {
            flipperImages(image);
        }


        card1 = (CardView) findViewById(R.id.card_1);
        card2 = (CardView) findViewById(R.id.card_2);
        card3 = (CardView) findViewById(R.id.card_3);
        card4 = (CardView) findViewById(R.id.card_4);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
//        recyclerView
        //  ShowRecyclerView();


//        NAVBAR
        BottomNavigationView bottomNavigationView = findViewById(R.id.NavMainButtom);
        bottomNavigationView.setSelectedItemId(R.id.homes);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.buttonAdd:
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        overridePendingTransition(0, 0);
                    case R.id.homes:
                        return true;
                }
                return false;
            }
        });

    }

    private void flipperImages(int images) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);

        flipper.addView(imageView);
        flipper.setFlipInterval(4000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this, android.R.anim.fade_in);
        flipper.setOutAnimation(this, android.R.anim.fade_out);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.card_1:
                Toast.makeText(getApplicationContext(), "Please Login First", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_2:
                Toast.makeText(getApplicationContext(), "Please Login First Honey", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_3:
                Toast.makeText(getApplicationContext(), "Click Login Logo on below please", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_4:
                Toast.makeText(getApplicationContext(), "Please Login First", Toast.LENGTH_SHORT).show();
                break;
        }
    }

//    //    recyler
//    private void ShowRecyclerView() {
//        recyclerView = findViewById(R.id.recyclerview);
//        comic_id = new ArrayList<>();
//        comic_name = new ArrayList<>();
//        comic_category = new ArrayList<>();
//        comic_image = new ArrayList<>();
//
//        storeDataInArrays();
//
//        setOnClikListener();        //otw ke detail
//        int numberColoumns = 2;
//        myAdapter = new MyAdapter(MainActivity.this, comic_id, comic_name, comic_category, comic_image, (MyAdapter.RecyclerViewClikListener) listener);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, numberColoumns));
//        recyclerView.setAdapter(myAdapter);
//    }
//
//    //    untuk recyler (ambil data)
//    private void storeDataInArrays() {
//        Cursor cursor = dbHelper.readAllData();
//        if(cursor.getCount() == 0){
//            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
//        }else{
//            while (cursor.moveToNext()){
//                comic_id.add(cursor.getString(0));
//                comic_name.add(cursor.getString(1));
//                comic_category.add(cursor.getString(2));
//                comic_image.add(cursor.getBlob(3));
//            }
//        }
//    }

        //    otw ke detail
//    private void setOnClikListener() {
//        listener = new MyAdapter.RecyclerViewClikListener() {
//            @Override
//            public void onClick(View v, int position) {
//                Intent intent = new Intent(getApplicationContext(), DetailComic.class);
//                intent.putExtra("comic_name", comic_name.get(position));
//                intent.putExtra("comic_category", comic_category.get(position));
//                intent.putExtra("comic_image", comic_image.get(position));
//
////                ByteArrayOutputStream stream = new ByteArrayOutputStream();
////                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
////                byte[] byteArray = stream.toByteArray();
////                intent.putExtra("comic_image", byteArray);
//                startActivity(intent);
//            }
//        };
//    }


}