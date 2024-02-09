package com.example.pract271;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "USERS";
    public static final String TABLE_LOGIN = "login_page";
    public static final String EMAIL = "email";
    public static final String PASS = "password";

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_LOGIN + "(" +
                EMAIL + " TEXT," +
                PASS + " TEXT" +
                ")";
        db.execSQL(sql);
    }

    public boolean insertData(String email, String pass) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL, email);
        values.put(PASS, pass);
        long res=db.insert(TABLE_LOGIN, null, values);
        db.close();
        if(res==-1){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        onCreate(db);
    }

    @SuppressLint("Range")
    public String CheckEmail(String e) {
        String email = null;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_LOGIN + " WHERE " + EMAIL + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{e});
        if (cursor.moveToFirst()) {
            email = cursor.getString(cursor.getColumnIndex(EMAIL));

        }
        cursor.close();
        return email;
    }

    @SuppressLint("Range")
    public String CheckPass(String e, String p) {
        String pass = null;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_LOGIN + " WHERE " + EMAIL + "=? AND " + PASS + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{e, p});
        if (cursor.moveToFirst()) {
            pass = cursor.getString(cursor.getColumnIndex(PASS));
        }
        cursor.close();
        return pass;
    }
}
