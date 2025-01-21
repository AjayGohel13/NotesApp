package com.example.sqliteexample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context){
        super(context,"AttendanceManager.db",null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table note(id INTEGER PRIMARY KEY AUTOINCREMENT, title Text,note Text, date TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists note");
        onCreate(sqLiteDatabase);
    }

    public Cursor getNoteData(){
        SQLiteDatabase mydb = this.getReadableDatabase();
        return mydb.query("note",null,null,null,null,null,null);
    }
    public void deleteNoteById(String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("note", "note=?", new String[]{String.valueOf(note)});
    }
    public boolean setNoteData(String note, String date, String title){
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("note",note);
        contentValues.put("date",date);
        contentValues.put("title",title);
        long result = mydb.insert("note",null, contentValues);
        return result != -1;
    }
}
