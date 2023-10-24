package com.example.culturallis.View.Navbar;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.culturallis.Controller.GlobalUtilization;
import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.Security;
import com.example.culturallis.View.Configuration.MainSettingsScreen;
import com.example.culturallis.View.Fragments.AccessDenied;
import com.example.culturallis.View.Fragments.Loading;
import com.example.culturallis.View.Fragments.NotConnected;
import com.example.culturallis.View.Fragments.NotFound;
import com.example.culturallis.View.Fragments.ServerError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.culturallis.View.Skeletons.SkeletonBlank;
import com.example.culturallis.View.Skeletons.SkeletonSelectedItem;
import com.example.culturallis.View.Fragments.CoursesScroll;

public class NavbarCulturallis extends AppCompatActivity {

    protected AppCompatButton postsHomeBtn;
    protected AppCompatButton coursesHomeBtn;
    protected AppCompatButton profileHomeButton;

    Fragment fragment;

    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar_culturallis);

        postsHomeBtn = findViewById(R.id.btnHomePosts);
        coursesHomeBtn = findViewById(R.id.btnHomeCourses);
        profileHomeButton = findViewById(R.id.btnHomeProfile);
        postsHomeBtn.setBackgroundResource(R.drawable.selected_tab);

        fragment = new Fragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentRender, fragment);
        transaction.commit();

        isConnected();
    }

    public void handleClickPostsHome(View view){
        postsHomeBtn.setBackgroundResource(R.drawable.selected_tab);
        coursesHomeBtn.setBackgroundResource(R.drawable.unselected_tab);
        profileHomeButton.setBackgroundResource(R.drawable.unselected_tab);
        postsHomeBtn.setTextColor(getResources().getColor(R.color.black));
        coursesHomeBtn.setTextColor(getResources().getColor(R.color.gray_typography));
        profileHomeButton.setTextColor(getResources().getColor(R.color.gray_typography));

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentRender, fragment);
        transaction.commit();
        isConnected();

    }

    public void handleClickCoursesHome(View view){
        postsHomeBtn.setBackgroundResource(R.drawable.unselected_tab);
        coursesHomeBtn.setBackgroundResource(R.drawable.selected_tab);
        profileHomeButton.setBackgroundResource(R.drawable.unselected_tab);
        postsHomeBtn.setTextColor(getResources().getColor(R.color.gray_typography));
        coursesHomeBtn.setTextColor(getResources().getColor(R.color.black));
        profileHomeButton.setTextColor(getResources().getColor(R.color.gray_typography));

        CoursesScroll fragment = new CoursesScroll();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentRender, fragment);
        transaction.commit();
        isConnected();
    }

    public void handleClickProfileHome(View view){
        postsHomeBtn.setBackgroundResource(R.color.white_base);
        coursesHomeBtn.setBackgroundResource(R.color.white_base);
        profileHomeButton.setBackgroundResource(R.color.base_gray);
        postsHomeBtn.setTextColor(getResources().getColor(R.color.gray_typography));
        coursesHomeBtn.setTextColor(getResources().getColor(R.color.gray_typography));
        profileHomeButton.setTextColor(getResources().getColor(R.color.black));

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentRender, fragment);
        transaction.commit();
        isConnected();

    }

    public void handleClickSettings(View view){
        startActivity(new Intent(this, MainSettingsScreen.class));
        isConnected();
    }

    public void finishErrorScreen(View view){
        finish();
        startActivity(new Intent(this, NavbarCulturallis.class));
        isConnected();
    }

    public void isConnected(){
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (NavbarCulturallis.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
        }
        else{
            NotConnected fragment = new NotConnected();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentRender, fragment);
            transaction.commit();
        }

    }

}