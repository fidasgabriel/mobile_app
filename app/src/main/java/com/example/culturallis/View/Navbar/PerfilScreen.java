package com.example.culturallis.View.Navbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.culturallis.Controller.Adapter.CourseAdapter;
import com.example.culturallis.Controller.Adapter.PostAdapter;
import com.example.culturallis.Model.Entity.CourseCard;
import com.example.culturallis.Model.Entity.CourseList;
import com.example.culturallis.Model.Entity.PostCard;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.CoursesScroll;
import com.example.culturallis.View.Fragments.FilterPerfil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PerfilScreen extends AppCompatActivity{
    private ImageView perfilHome;
    private ImageView perfilCourse;
    private ImageView perfilSaved;
    private RecyclerView rv;
    private FragmentTransaction transaction;
    public List<CourseCard> listCourseC;
    private boolean isListCourseCReversed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_screen);

        Random rand = new Random();

        DownNavbar downNav = new DownNavbar().newInstance(3);
        TopNavbar topNav = new TopNavbar();
        FragmentManager fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.topNav, topNav);
        transaction.replace(R.id.downNav, downNav);
        transaction.commit();

        ImageView left_arrow = findViewById(R.id.leftArrow);
        left_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rv = findViewById(R.id.chgContent);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(64);
        rv.addItemDecoration(itemDecorator);
        rv.setLayoutManager(linearLayoutManager);
        rv.setFocusable(false);
        rv.setNestedScrollingEnabled(false);

        int cianColor = ContextCompat.getColor(getApplicationContext(), R.color.cian_perfil);
        int blackColor = ContextCompat.getColor(getApplicationContext(), R.color.light_black);

        perfilHome = findViewById(R.id.perfilHome);
        perfilHome.setColorFilter(cianColor, PorterDuff.Mode.SRC_IN);
        perfilCourse = findViewById(R.id.perfilCourse);
        perfilSaved = findViewById(R.id.perfilSaved);

        perfilHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfilHome.setBackgroundResource(R.drawable.perfil_option_selected);
                perfilCourse.setBackgroundResource(0);
                perfilSaved.setBackgroundResource(0);

                perfilHome.setColorFilter(cianColor, PorterDuff.Mode.SRC_IN);
                perfilCourse.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);
                perfilSaved.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);

                Fragment fragment = fragmentManager.findFragmentById(R.id.filterLayout);

                List<PostCard> listPostC = new ArrayList<>();
                listPostC.add(new PostCard( R.drawable.culture_example,R.drawable.perfil_example,"Dr. Fidas",false, false,"Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula mattis tellus"));
                listPostC.add(new PostCard( R.drawable.culture_example,R.drawable.perfil_example,"Dr. Fidas2",false,true,"Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula mattis tellus"));
                listPostC.add(new PostCard( R.drawable.culture_example,R.drawable.perfil_example,"Dr. Fidas3",true, false,"Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula mattis tellus"));

                PostAdapter postAdapter = new PostAdapter(PerfilScreen.this);

                postAdapter.setData(listPostC, false);
                rv.setAdapter(postAdapter);


                if(fragment != null) {
                    transaction = fragmentManager.beginTransaction();
                    transaction.remove(fragment);
                    transaction.commit();
                }
            }
        });

        perfilCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfilHome.setBackgroundResource(0);
                perfilCourse.setBackgroundResource(R.drawable.perfil_option_selected);
                perfilSaved.setBackgroundResource(0);

                perfilHome.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);
                perfilCourse.setColorFilter(cianColor, PorterDuff.Mode.SRC_IN);
                perfilSaved.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);

                listCourseC = new ArrayList<>();
                listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título chamativo","Dr. Fidas",340, false));
                listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título super chamativo","Dr. Fidas2",1234, false));
                listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título HIPER MEGA ULTRA chamativo","Dr. Fidas3",5678910, true));

                CourseAdapter courseAdapter = new CourseAdapter(PerfilScreen.this);

                courseAdapter.setData(listCourseC, false);
                rv.setAdapter(courseAdapter);

                transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.filterLayout, new FilterPerfil().newInstance(1));
                transaction.commit();

//                FragmentTransaction perfilCourseTransaction = getSupportFragmentManager().beginTransaction();
//                perfilCourseTransaction.replace(R.id.chgContent, coursesScroll);
//                perfilCourseTransaction.commit();
            }
        });

        perfilSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfilHome.setBackgroundResource(0);
                perfilCourse.setBackgroundResource(0);
                perfilSaved.setBackgroundResource(R.drawable.perfil_option_selected);

                perfilHome.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);
                perfilCourse.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);
                perfilSaved.setColorFilter(cianColor, PorterDuff.Mode.SRC_IN);

                Fragment fragment = fragmentManager.findFragmentById(R.id.filterLayout);

                if(fragment != null) {
                    transaction = fragmentManager.beginTransaction();
                    transaction.remove(fragment);
                    transaction.commit();
                }
            }
        });

        ImageView header =  findViewById(R.id.header);
        Integer type = rand.nextInt(4);
        Integer color = 0;
        switch (type){
            case 1: color = R.color.blue_perfil; break;
            case 2: color = R.color.violet_perfil; break;
            case 3: color = R.color.yellow_perfil; break;
        }
        if(color != 0) header.setImageResource(color);
    }

    public void addFilterRecycleView(int type, int option){
        switch (type){
            case 1: //Para cursos assitidos
                if (option == 0 && !isListCourseCReversed){
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    linearLayoutManager.setReverseLayout(true);
                    linearLayoutManager.setStackFromEnd(true);
                    rv.setLayoutManager(linearLayoutManager);
                    isListCourseCReversed = true;

                }else if(option == 1 && isListCourseCReversed){
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    linearLayoutManager.setReverseLayout(false);
                    linearLayoutManager.setStackFromEnd(false);
                    rv.setLayoutManager(linearLayoutManager);
                    isListCourseCReversed = false;
                }
                break;
            case 2: // para cursos criados

                break;

            case 3: //para conteúdo salvo

                break;
        }
    }
}