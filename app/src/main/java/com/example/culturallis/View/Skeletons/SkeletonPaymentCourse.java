package com.example.culturallis.View.Skeletons;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;

import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.PaymentScreens.PaymentCourse;
import com.example.culturallis.View.Navbar.NavbarCulturallis;
import com.example.culturallis.View.Navbar.TopNavbarNoSettings;

public class SkeletonPaymentCourse extends ModelAppScreens {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeleton_payment_course);

        TopNavbarNoSettings topNavbar = new TopNavbarNoSettings();
        PaymentCourse paymentCourse = new PaymentCourse();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.navbar, topNavbar);
        transaction.replace(R.id.content, paymentCourse);
        transaction.commit();
    }

    public void changeBackToCourseDetail(View v){
        startActivity(new Intent(this, SkeletonSelectedItem.class));
        back(v);
    }

    public void changeCoursesHome(View v){
        startActivity(new Intent(this, NavbarCulturallis.class));
        back(v);
    }

    public void adquireCourse(View v){
        startActivity(new Intent(this, SkeletonCourseDetails.class));
        back(v);
    }
}