package com.example.culturallis.View.Navbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.culturallis.Controller.Adapter.CourseAdapter;
import com.example.culturallis.Model.Entity.CourseCard;
import com.example.culturallis.R;
import com.example.culturallis.View.Skeletons.SkeletonSelectedItem;

import java.util.ArrayList;
import java.util.List;

public class CourseScreen extends AppCompatActivity {
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_screen);

        DownNavbar downNav = new DownNavbar().newInstance(2);
        TopNavbar topNav = new TopNavbar();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.downNav, downNav);
        transaction.replace(R.id.topNav, topNav);
        transaction.commit();

        rv = findViewById(R.id.recycleView);
        List<CourseCard> listCourseC = new ArrayList<>();
        listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título chamativo","Dr. Fidas",340, false));
        listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título super chamativo","Dr. Fidas2",1234, false));
        listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título HIPER MEGA ULTRA chamativo","Dr. Fidas3",5678910, true));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(64);
        rv.addItemDecoration(itemDecorator);
        rv.setLayoutManager(linearLayoutManager);

        CourseAdapter courseAdapter = new CourseAdapter();

        courseAdapter.setData(listCourseC);
        rv.setAdapter(courseAdapter);


//        rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                startActivity(new Intent(CourseScreen.this, SkeletonSelectedItem.class));
//            }
//        });
    }
}