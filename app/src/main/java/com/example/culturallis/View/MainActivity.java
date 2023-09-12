package com.example.culturallis.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.culturallis.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Handler handler = new Handler();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {show_screen();}
        }, 4000);
    }

    public void show_screen(){
        Intent intent = new Intent(this, MainSettingsScreen.class);
        startActivity(intent);
        finish();
    }
}