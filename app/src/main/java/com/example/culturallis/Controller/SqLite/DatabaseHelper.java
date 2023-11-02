package com.example.culturallis.Controller.SqLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
//    private static final String DATABASE_NAME = "YourDatabaseName.db";
//    private static final int DATABASE_VERSION = 1;
//    public static final String TABLE_NAME = "CurrentUser";
//    public static final String COLUMN_ID = "id";
//    public static final String COLUMN_EMAIL = "email";
//    public static final String COLUMN_PASSWORD = "password";
//
//    private static final String TABLE_CREATE =
//            "CREATE TABLE " + TABLE_NAME + " (" +
//                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    COLUMN_EMAIL + " TEXT, " +
//                    COLUMN_PASSWORD + " TEXT);";
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(TABLE_CREATE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }


    public DatabaseHelper(@Nullable Context context) {
        super(context, "conta.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE login (" +
                "posit integer, " +
                "email text, " +
                "senha text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE login (" +
                "posit integer, " +
                "email text, " +
                "senha text)");
    }
}
