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
        info.put("posit", 1);
        info.put("email", user.getEmail());
        info.put("senha", user.getPassword());
        database.insert("login", null, info);
        close();

        return user;
    }

    public LoginUserEntity getLogin(){
        open();
        LoginUserEntity login = null;
        Cursor cursor = database.rawQuery("SELECT email, senha FROM login WHERE posit = 1", null);
        if (cursor.moveToFirst()) {
            login = new LoginUserEntity(
                    cursor.getString(0),
                    cursor.getString(1));
        }
        close();
        return login;
    }

    public void deletar(){
        open();
        database.delete("login", "posit=?", new String[]{"1"});
        close();
    }

    public void changeEmail(String email){
        ContentValues info = new ContentValues();
        info.put("email", email);
        open();
        database.update("login", info, "posit=?", new String[]{"1"});
        close();
    }

    public void changePassword(String password){
        ContentValues info = new ContentValues();
        info.put("password", password);
        open();
        database.update("login", info, "posit=?", new String[]{"1"});
        close();
    }
}
