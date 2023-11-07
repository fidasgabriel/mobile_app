package com.example.culturallis.View.Skeletons;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;

import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.Loading;
import com.example.culturallis.View.Fragments.ServerError;
import com.example.culturallis.View.Navbar.NavbarCulturallis;

public class SkeletonBlank extends ModelAppScreens {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selekont_blank);

        ServerError fragment = new ServerError();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    public void finishErrorScreen(View v){
        back(v);
    }

}