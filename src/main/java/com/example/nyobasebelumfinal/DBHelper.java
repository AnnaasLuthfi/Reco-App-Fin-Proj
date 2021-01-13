package com.example.nyobasebelumfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "reco";

    public static final String table_name = "table_login";
    public static final String row_id ="_id";
    public static final String row_username ="Username";
    public static final String row_password ="Password";
    public static final String row_email ="Email";

    public static final String table_comic = "table_comic";
    public static final String row_comic_id = "comic_id";
    public static final String row_comic_name = "comic_name";
    public static final String row_comic_category = "comic_category";
    public static final String row_comic_image = "image";

    public static final String table_chapter = "table_chapter";
    public static final String row_chapter_id = "chapter_id";
    public static final String row_comic_chapter_id = "comic_id";
    public static final String row_chapter_title = "chapter_title";
    public static final String row_chapter_image = "image";



    private SQLiteDatabase db;


    public DBHelper(Context context) {
        super(context,  database_name, null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " + table_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + row_username + " TEXT, " + row_password + " TEXT, " + row_email + " TEXT) ";
        String query_comic = "CREATE TABLE " + table_comic + "(" + row_comic_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + row_comic_name + " TEXT, " + row_comic_category + " TEXT, " + row_comic_image + " BLOB) ";
        String query_chapter = "CREATE TABLE " + table_chapter + "(" + row_chapter_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + row_comic_chapter_id + " TEXT , " +
                row_chapter_title + " TEXT, "+ row_comic_image + " BLOB) ";
        db.execSQL(query);
        db.execSQL(query_comic);
        db.execSQL(query_chapter);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        db.execSQL("DROP TABLE IF EXISTS " + table_comic);
        db.execSQL("DROP TABLE IF EXISTS " + table_chapter);
    }

//    REGISTER
    public void insertData(ContentValues values){
        db.insert(table_name, null, values);
    }

//    ADD COMIC
    void addComic(ContentValues contentValues){
        db.insert(table_comic, null, contentValues);
    }

    //    ADD Chapter
    void addChapter(ContentValues contentValues){
        db.insert(table_chapter, null, contentValues);
    }


//    untuk LOGIN
    public boolean checkUser(String username, String password){
        String[] columns = {row_id};
        SQLiteDatabase db = getReadableDatabase();
        String selection = row_username + "=?" + " AND " + row_password + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean upgradeSession(String sessionValues, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", sessionValues);
        long update = db.update("session", contentValues, "id" +id, null);
        if (update == -1){
            return false;
        }else{
            return true;
        }
    }

    Cursor readAllData(Integer category){
        String query = " SELECT * FROM " + table_comic +" WHERE " + row_comic_id +" = " + category;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllData2(String category){

        String query = " SELECT * FROM " + table_comic +" WHERE " + row_comic_category +" LIKE '%" + category + "%'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    Cursor readAllComic(Integer id){
        String query = " SELECT * FROM " + table_chapter +" WHERE " + row_comic_chapter_id +" = " + id ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllComic2(String title){
        String query = " SELECT * FROM " + table_chapter +" WHERE " + row_comic_chapter_id +" LIKE '%" + title + "%'" ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readChapter(String title){
        String query = " SELECT * FROM " + table_chapter +" WHERE " + row_chapter_title +" LIKE '%" + title + "%'" ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


}
