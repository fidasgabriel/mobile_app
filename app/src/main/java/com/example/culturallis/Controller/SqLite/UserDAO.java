package com.example.culturallis.Controller.SqLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.culturallis.Model.Entity.LoginUserEntity;

public class UserDAO {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public UserDAO(Context c){
        context = c;
    }

    public void open(){
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        database.close();
        dbHelper.close();
    }

    public LoginUserEntity salvar(LoginUserEntity user){
        open();
        ContentValues info = new ContentValues();
        info.put("email", user.getEmail());
        info.put("senha", user.getPassword());
        database.insert("login", null, info);
        close();

        return user;
    }

    public boolean validateLogin(String email, String senha){
        open();
        Cursor cursor = database.rawQuery("SELECT email, senha FROM login WHERE email = '" + email + "' AND senha = '" + senha + "'", null);
        boolean isNotEmpty = cursor.moveToFirst();
        close();
        return isNotEmpty;
    }

    public String getCurrentEmail(){
        open();
        Cursor cursor = database.rawQuery("SELECT email FROM currentUser WHERE posit = 1", null);
        cursor.moveToFirst();
        String currentEmail = cursor.getString(0);
        close();
        return currentEmail;
    }

    public void setCurrentUser(String email){
        ContentValues info = new ContentValues();
        info.put("email", email);
        open();
        database.update("currentUser", info, "posit=?", new String[]{"1"});
        close();
    }

    public void changePassword(String email, String password){
        ContentValues info = new ContentValues();
        info.put("password", password);
        open();
        database.update("login", info, "email=?", new String[]{email});
        close();
    }
}
