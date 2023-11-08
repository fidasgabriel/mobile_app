package com.example.culturallis.View.Skeletons;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.MainSettingsScreen;
import com.example.culturallis.View.Configuration.Security;
import com.example.culturallis.View.Fragments.DetailsScreen.CourseDetailsScreenNotAdquired;
import com.example.culturallis.View.Navbar.NavbarCulturallis;
import com.example.culturallis.View.Navbar.TopNavbar;

import java.util.Random;

public class SkeletonSelectedItem extends ModelAppScreens {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeleton_selected_item);


        CourseDetailsScreenNotAdquired fragment = new CourseDetailsScreenNotAdquired();
        TopNavbar topNavbar = new TopNavbar();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameRender, fragment);
        transaction.replace(R.id.navbarBase, topNavbar);

        transaction.commit();

        TextView txtCategory = findViewById(R.id.textCategoryMain);
        ImageView imgView = findViewById(R.id.courseImage);
        TextView txtTitleCourse = findViewById(R.id.titleCourse);
        TextView txtPriceCourse = findViewById(R.id.priceCourse);
        TextView txtUserNameCourseOwner = findViewById(R.id.useNameTxt);
        TextView txtDescriptionCourse = findViewById(R.id.descriptionCourse);
        CardView cardView = findViewById(R.id.cardProf);
        TextView avaliation = findViewById(R.id.avaliation);

        imgView.setImageResource(R.drawable.logo);

        int[] colorBorderSelected = {
                R.drawable.border_image_blue,
                R.drawable.border_image_orange,
                R.drawable.border_image_purple,
        };

        int[] colorAvatarBorder = {
                R.color.blue_course,
                R.color.orange_course,
                R.color.purple_course,
        };

        int[] colorTagCategory = {
                R.drawable.tag_category_blue,
                R.drawable.tag_category_orange,
                R.drawable.tag_category_purple,
        };


        Random random = new Random();
        int randomIdx;
        randomIdx = random.nextInt(colorBorderSelected.length);


        int randomBorderSelected = colorBorderSelected[randomIdx];
        int randomAvatarBorder = colorAvatarBorder[randomIdx];
        int randomTagColor = colorTagCategory[randomIdx];


        imgView.setBackground(getResources().getDrawable(randomBorderSelected));
        cardView.setCardBackgroundColor(getResources().getColor(randomAvatarBorder));
        txtDescriptionCourse.setBackground(getResources().getDrawable(randomTagColor));
        txtCategory.setBackground(getResources().getDrawable(randomTagColor));


        txtCategory.setTextColor(getResources().getColor(R.color.white));


        txtCategory.setText("Python");
        txtTitleCourse.setText("Título Chamativo");
        txtPriceCourse.setText("R$89,99");
        txtCategory.setClickable(false);
        txtUserNameCourseOwner.setText("Culturallis");
        avaliation.setText(String.valueOf(4.3));
        txtDescriptionCourse.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.");
    }

    public void changeMainSettings(View v){
        startActivity(new Intent(this, MainSettingsScreen.class));
        back(v);
    }

    public void changeCoursesHome(View v){
        startActivity(new Intent(this, NavbarCulturallis.class));
        back(v);
    }

    public void adquireCourse(View v){
        startActivity(new Intent(this, SkeletonPaymentCourse.class));
        back(v);
    }
}