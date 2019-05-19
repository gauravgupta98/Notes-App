package com.example.gjg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String databaseName = "Notes.db";
    private static final String tableName = "notesTable";
    private static final String columnId = "id";
    private static final String columnNotes = "notesData";

    public Database(Context context){
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName+" (id INTEGER PRIMARY KEY AUTOINCREMENT, notesData VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
        onCreate(db);
    }

    public void addNote(NotesFields notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnNotes, notes.getNotesData());
        db.insert(tableName, null, contentValues);
        db.close();
    }

    public void deleteNote(String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, columnNotes + "=?", new String[]{data});
    }

    public List<String> fetchAll(){
        List<String> stringList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+tableName, null);
        while (cursor.moveToNext()){
            stringList.add(cursor.getString(1));
        }
        cursor.close();
        db.close();
        return stringList;
    }


}
