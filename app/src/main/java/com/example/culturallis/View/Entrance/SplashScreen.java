package com.example.culturallis.View.Entrance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.Security;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Handler handler = new Handler();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {show_screen();}
        }, 2000);
    }

    public void show_screen(){
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
        finish();
    }
}