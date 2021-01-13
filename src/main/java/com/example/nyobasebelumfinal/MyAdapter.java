package com.example.nyobasebelumfinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList comic_id, comic_name, comic_category;
    private ArrayList<byte[]> comic_image;
//    private RecyclerViewClikListener listener;


    public MyAdapter(Context context, ArrayList comic_id, ArrayList comic_name, ArrayList comic_category, ArrayList comic_image){
        this.context = context;
        this.comic_id = comic_id;
        this.comic_name = comic_name;
        this.comic_category = comic_category;
        this.comic_image = comic_image;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.comic_name_txt.setText(String.valueOf(comic_name.get(position)));
        holder.comic_category_txt.setText(String.valueOf(comic_category.get(position)));
        holder.comic_cover_image.setImageBitmap(getImage(comic_image.get(position)));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ChapterViewActivity.class);
                intent.putExtra("name",String.valueOf(comic_name.get(position)));
                context.startActivity(intent);

            }
        });

    }

//    convert byte array ke bitmap
    private static Bitmap getImage(byte[] record) {
        return  BitmapFactory.decodeByteArray(record, 0, record.length);
    }

    @Override
    public int getItemCount() {
        return comic_name.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView comic_name_txt, comic_category_txt;
        ImageView comic_cover_image;
        CardView card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            comic_name_txt = itemView.findViewById(R.id.comic_name_txt);
            comic_category_txt = itemView.findViewById(R.id.comic_category_txt);
            comic_cover_image = itemView.findViewById(R.id.imageComics);
            card = itemView.findViewById(R.id.card);

//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            listener.onClick(v, getAdapterPosition());
//        }
    }

//    public interface RecyclerViewClikListener{
//        void onClick(View v, int position);
//    }
}
