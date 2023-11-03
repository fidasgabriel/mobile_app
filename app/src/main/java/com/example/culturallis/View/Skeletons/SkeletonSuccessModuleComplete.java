package com.example.culturallis.View.Skeletons;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.SuccessScreens.CompleteModuleSuccess;
import com.example.culturallis.View.Navbar.HomeScreen;
import com.example.culturallis.View.Navbar.TopNavbarNoSettings;

public class SkeletonSuccessModuleComplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeleton_success_module_complete);

        TopNavbarNoSettings topNavbar = new TopNavbarNoSettings();
        CompleteModuleSuccess paymentCourse = new CompleteModuleSuccess();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.navbar, topNavbar);
        transaction.replace(R.id.content, paymentCourse);
        transaction.commit();
    }

    public void changeCoursesHome(View view){
        startActivity(new Intent(this, HomeScreen.class));
        finish();
    }

    public void nextModule(View view){
        startActivity(new Intent(this, SkeletonCourseDetails.class));
        finish();
    }
}