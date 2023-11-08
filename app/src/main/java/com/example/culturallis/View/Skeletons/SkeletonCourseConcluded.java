package com.example.culturallis.View.Skeletons;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;

import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.SuccessScreens.CompleteCourseSuccess;
import com.example.culturallis.View.Navbar.HomeScreen;
import com.example.culturallis.View.Navbar.NavbarCulturallis;
import com.example.culturallis.View.Navbar.TopNavbarNoSettings;

public class SkeletonCourseConcluded extends ModelAppScreens {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeleton_course_concluded);

        TopNavbarNoSettings topNavbar = new TopNavbarNoSettings();
        CompleteCourseSuccess paymentCourse = new CompleteCourseSuccess();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.navbar, topNavbar);
        transaction.replace(R.id.content, paymentCourse);
        transaction.commit();
    }

    public void changeCoursesHome(View v){
        startActivity(new Intent(this, HomeScreen.class));
        back(v);
    }

    public void finishCourse(View v){
        back(v);
    }
}