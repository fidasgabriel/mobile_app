package com.example.culturallis.View.Skeletons;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.MainSettingsScreen;
import com.example.culturallis.View.Configuration.Security;
import com.example.culturallis.View.Fragments.DetailsScreen.CourseDetailsScreenAdquired;
import com.example.culturallis.View.Navbar.NavbarCulturallis;
import com.example.culturallis.View.Navbar.TopNavbar;

public class SkeletonCourseDetails extends ModelAppScreens {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeleton_course_details);

        TopNavbar topNavbar = new TopNavbar();
        CourseDetailsScreenAdquired paymentCourse = new CourseDetailsScreenAdquired();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.navbar, topNavbar);
        transaction.replace(R.id.content, paymentCourse);
        transaction.commit();
    }

    public void changeCoursesHome(View view){
        finish();
    }

    public void changeMainSettings(View v){
        startActivity(new Intent(this, MainSettingsScreen.class));
        back(v);
    }

    public void concludeModule(View view) {
        if ("lastModule".equals(view.getContentDescription().toString())) {
            startActivity(new Intent(this, SkeletonCourseConcluded.class));
        } else {
            startActivity(new Intent(this, SkeletonSuccessModuleComplete.class));
        }
    }


}