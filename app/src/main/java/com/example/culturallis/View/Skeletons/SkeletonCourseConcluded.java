package com.example.culturallis.View.Skeletons;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.example.culturallis.Controller.GlobalUtilization;
import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.MainSettingsScreen;
import com.example.culturallis.View.Configuration.TermsOfService;
import com.example.culturallis.View.Fragments.SuccessScreens.CompleteCourseSuccess;
import com.example.culturallis.View.Fragments.SuccessScreens.CompleteModuleSuccess;
import com.example.culturallis.View.Navbar.NavbarCulturallis;
import com.example.culturallis.View.Navbar.TopNavbar;
import com.example.culturallis.View.Navbar.TopNavbarNoSettings;

import java.util.ArrayList;
import java.util.List;

public class SkeletonCourseConcluded extends AppCompatActivity {

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

    public void changeCoursesHome(View view){
        startActivity(new Intent(this, NavbarCulturallis.class));
        finish();
    }

    public void finishCourse(View view){
        startActivity(new Intent(this, NavbarCulturallis.class));
        finish();
    }
}