package com.example.culturallis.View.Navbar;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentTransaction;

import com.example.culturallis.Controller.GlobalUtilization;
import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.MainSettingsScreen;
import com.example.culturallis.View.Fragments.Loading;
import com.example.culturallis.View.Fragments.ServerError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NavbarCulturallis extends AppCompatActivity {

    private AppCompatButton postsHomeBtn;
    private AppCompatButton coursesHomeBtn;
    private AppCompatButton profileHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar_culturallis);

        postsHomeBtn = findViewById(R.id.btnHomePosts);
        coursesHomeBtn = findViewById(R.id.btnHomeCourses);
        profileHomeButton = findViewById(R.id.btnHomeProfile);
        postsHomeBtn.setBackgroundResource(R.drawable.selected_tab);

        ServerError fragment = new ServerError();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentRender, fragment);
        transaction.commit();
    }

    public void handleClickPostsHome(View view){
        postsHomeBtn.setBackgroundResource(R.drawable.selected_tab);
        coursesHomeBtn.setBackgroundResource(R.drawable.unselected_tab);
        profileHomeButton.setBackgroundResource(R.drawable.unselected_tab);
        postsHomeBtn.setTextColor(getResources().getColor(R.color.black));
        coursesHomeBtn.setTextColor(getResources().getColor(R.color.gray_typography));
        profileHomeButton.setTextColor(getResources().getColor(R.color.gray_typography));
        Loading fragment = new Loading();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentRender, fragment);
        transaction.commit();
    }

    public void handleClickCoursesHome(View view){
        postsHomeBtn.setBackgroundResource(R.drawable.unselected_tab);
        coursesHomeBtn.setBackgroundResource(R.drawable.selected_tab);
        profileHomeButton.setBackgroundResource(R.drawable.unselected_tab);
        postsHomeBtn.setTextColor(getResources().getColor(R.color.gray_typography));
        coursesHomeBtn.setTextColor(getResources().getColor(R.color.black));
        profileHomeButton.setTextColor(getResources().getColor(R.color.gray_typography));
        Loading fragment = new Loading();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentRender, fragment);
        transaction.commit();
    }

    public void handleClickProfileHome(View view){
        postsHomeBtn.setBackgroundResource(R.color.white_base);
        coursesHomeBtn.setBackgroundResource(R.color.white_base);
        profileHomeButton.setBackgroundResource(R.color.base_gray);
        postsHomeBtn.setTextColor(getResources().getColor(R.color.gray_typography));
        coursesHomeBtn.setTextColor(getResources().getColor(R.color.gray_typography));
        profileHomeButton.setTextColor(getResources().getColor(R.color.black));
        Loading fragment = new Loading();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentRender, fragment);
        transaction.commit();
    }

    public void handleClickSettings(View view){
        startActivity(new Intent(this, MainSettingsScreen.class));
    }
}