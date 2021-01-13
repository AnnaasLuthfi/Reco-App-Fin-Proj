package com.example.nyobasebelumfinal;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.MyViewHolder> {
private Context context;
private ArrayList chapter_id, comic_id, chapter_title;
private ArrayList<byte[]> chapter_image;

    public ChapterAdapter(Context context, ArrayList<String> chapter_id, ArrayList<String> comic_id, ArrayList<String> chapter_title, ArrayList<byte[]> chapter_image) {
        this.context = context;
        this.chapter_id = comic_id;
        this.comic_id = comic_id;
        this.chapter_title = chapter_title;
        this.chapter_image = chapter_image;
    }
//    private RecyclerViewClikListener listener;


//public void ChapterAdapter(Context context, ArrayList chapter_id, ArrayList comic_id, ArrayList chapter_title, ArrayList chapter_image){
//        this.context = context;
//        this.chapter_id = comic_id;
//        this.comic_id = comic_id;
//        this.chapter_title = chapter_title;
//        this.chapter_image = chapter_image;
//
//        }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.chapter_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv_chapter.setText(String.valueOf(chapter_id.get(position)));
//        holder.comic_category_txt.setText(String.valueOf(comic_id.get(position)));
        holder.tv_title.setText(String.valueOf(chapter_title.get(position)));

        holder.ig_image.setImageBitmap(getImage(chapter_image.get(position)));
        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailChapterActivity.class);
                intent.putExtra("name",String.valueOf(chapter_title.get(position)));
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
            return chapter_id.size();
            }


public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tv_title, tv_chapter;
    ImageView ig_image;
    CardView card;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_title = itemView.findViewById(R.id.tv_title);
        tv_chapter = itemView.findViewById(R.id.tv_chapter);
        ig_image = itemView.findViewById(R.id.img_view);
        card = itemView.findViewById(R.id.card);

//            itemView.setOnClickListener(this);
    }

//        @Override
//        public void onClick(View v) {
//            listener.onClick(v, getAdapterPosition());
//        }
}



}
