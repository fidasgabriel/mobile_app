package com.example.culturallis.View.Skeletons;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.PaymentScreens.PaymentCourse;
import com.example.culturallis.View.Navbar.NavbarCulturallis;
import com.example.culturallis.View.Navbar.TopNavbarNoSettings;

public class SkeletonPaymentCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeleton_payment_course);

        TopNavbarNoSettings topNavbar = new TopNavbarNoSettings();
        PaymentCourse paymentCourse = new PaymentCourse();

        Bundle bundle = new Bundle();
        Bundle b = getIntent().getExtras();

        bundle.putDouble("preco", b.getDouble("preco"));
        bundle.putInt("curso", b.getInt("curso"));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.navbar, topNavbar);
        paymentCourse.setArguments(bundle);
        transaction.replace(R.id.content, paymentCourse);
        transaction.commit();
    }

    public void changeBackToCourseDetail(View view){
        startActivity(new Intent(this, SkeletonSelectedItem.class));
        finish();
    }

    public void changeCoursesHome(View view){
        startActivity(new Intent(this, NavbarCulturallis.class));
        finish();
    }

    public void adquireCourse(View view){
        startActivity(new Intent(this, SkeletonCourseDetails.class));
        finish();
    }
}