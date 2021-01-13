package com.example.nyobasebelumfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.collection.ArraySet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AfterLogReg extends AppCompatActivity implements View.OnClickListener {

//    private ViewPager2 viewPager2;
//    private Handler sliderhandler = new Handler();
//    MyAdapter.RecyclerViewClikListener listener;    //ke detail

    DBHelper dbHelper;
    Button logout;
    ImageButton add;
    ViewFlipper flipper;

    private CardView card1,card2,card3,card4;

    RecyclerView recyclerView;
    ArrayList<String> comic_id;
    ArrayList<String> comic_name;
    ArrayList<String> comic_category;
    ArrayList<byte[]> comic_image;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_log_reg);

        dbHelper = new DBHelper(AfterLogReg.this);

        card1 = (CardView) findViewById(R.id.card_1);
        card2 = (CardView) findViewById(R.id.card_2);
        card3 = (CardView) findViewById(R.id.card_3);
        card4 = (CardView) findViewById(R.id.card_4);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);

        int images[] = {R.drawable.shinchan, R.drawable.age, R.drawable.eggnoid, R.drawable.onepiece, R.drawable.pasutrigaje, R.drawable.letsplay_s2};
        flipper = findViewById(R.id.flipperView);
        for(int image : images){
            flipperImages(image);
        }

//        recyclerView
//        ShowRecyclerView();


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
                        startActivity(new Intent(getApplicationContext(), AddData.class));
                        overridePendingTransition(0,0);
                        finish();
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
        switch (v.getId()){
            case R.id.card_1 :
                i = new Intent(this,CategoryActivity.class);
                i.putExtra("menus", "Action");
                startActivity(i);
                break;
            case R.id.card_2 :
                i = new Intent(this,CategoryActivity.class);
               i.putExtra("menus", "Romance");
                startActivity(i);
                break;
            case R.id.card_3 :
                i = new Intent(this,CategoryActivity.class);
                i.putExtra("menus", "Drama");
                startActivity(i);
                break;
            case R.id.card_4 :
                i = new Intent(this,CategoryActivity.class);
                i.putExtra("menus", "Comedy");
                startActivity(i);
                break;

        }

    }
}