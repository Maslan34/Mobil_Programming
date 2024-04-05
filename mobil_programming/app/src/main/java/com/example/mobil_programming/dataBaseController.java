package com.example.mobil_programming;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dataBaseController extends SQLiteOpenHelper {


    public final static String DATABASE_NAME="USER.DB";
    public final static String TABLE_NAME="contactcs";
    public final static String COL_PHONE="phone";
    public final static String COL_FULL_NAME="fullName";



    public dataBaseController(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME+" (id INTEGER PRIMARY KEY, "+COL_FULL_NAME+" TEXT, "+COL_PHONE+" Text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void newContact(String fName, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // TABLO ISIMLERI ILE ESLESTIR

        values.put(COL_FULL_NAME, fName);
        values.put(COL_PHONE, phone);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int updateOne(String fName, String phone,long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FULL_NAME, fName);
        values.put(COL_PHONE, phone);
        return db.update(TABLE_NAME, values, "id = ?", new String[] { String.valueOf(id) });
    }

    public Cursor getContacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

    public Cursor getContact(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = ?", new String[] {String.valueOf(userId)});
    }

    public void deleteOne(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[] { String.valueOf(id) });
        db.close();
    }

}
