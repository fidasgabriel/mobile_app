package com.example.culturallis.Controller.SqLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "conta.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE login (" +
                "email text, " +
                "senha text)");
        db.execSQL("CREATE TABLE currentUser (" +
                "posit integer, " +
                "email text)");
        db.execSQL("INSERT INTO currentUser VALUES(" +
                "1, '_BLANK_EMAIL_')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE login (" +
                "email text, " +
                "senha text)");
        db.execSQL("CREATE TABLE currentUser (" +
                "posit integer, " +
                "email text)");
        db.execSQL("INSERT INTO currentUser VALUES(" +
                "1, blank_email)");
    }
}
