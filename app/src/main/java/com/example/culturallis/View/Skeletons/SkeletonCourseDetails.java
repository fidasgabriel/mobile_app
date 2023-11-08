package com.example.culturallis.View.Skeletons;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.example.culturallis.Controller.Adapter.CourseAdapter;
import com.example.culturallis.Controller.Queries.GetCourseInfo;
import com.example.culturallis.Controller.Queries.GetCoursesAdquired;
import com.example.culturallis.Controller.SqLite.UserDAO;
import com.example.culturallis.Model.CourseDetails.CourseDetails;
import com.example.culturallis.Model.CoursesHome.CoursesHome;
import com.example.culturallis.Model.Entity.CourseCard;
import com.example.culturallis.Model.Entity.LoginUserEntity;
import com.example.culturallis.Model.Usuario.Usuario;
import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.MainSettingsScreen;
import com.example.culturallis.View.Configuration.Security;
import com.example.culturallis.View.Fragments.DetailsScreen.CourseDetailsScreenAdquired;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Navbar.NavbarCulturallis;
import com.example.culturallis.View.Navbar.TopNavbar;
import com.google.gson.Gson;

import java.util.List;

public class SkeletonCourseDetails extends AppCompatActivity {

    CourseDetailsScreenAdquired paymentCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeleton_course_details);

        TopNavbar topNavbar = new TopNavbar();
        paymentCourse = new CourseDetailsScreenAdquired();

        Bundle bundle = new Bundle();
        Bundle b = getIntent().getExtras();

        bundle.putString("idCourse", b.getString("idCourse"));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.navbar, topNavbar);
        paymentCourse.setArguments(bundle);
        transaction.replace(R.id.content, paymentCourse);
        transaction.commit();


    }

    public void changeCoursesHome(View view){
        finish();
    }

    public void changeMainSettings(View view){
        startActivity(new Intent(this, MainSettingsScreen.class));
        finish();
    }

    public void concludeModule(View view) {
        if ("lastModule".equals(view.getContentDescription().toString())) {
            startActivity(new Intent(this, SkeletonCourseConcluded.class));
        } else {
            startActivity(new Intent(this, SkeletonSuccessModuleComplete.class));
        }
    }




}