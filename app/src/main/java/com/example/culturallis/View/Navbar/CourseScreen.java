package com.example.culturallis.View.Navbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.culturallis.Controller.Adapter.CourseAdapter;
import com.example.culturallis.Controller.Queries.GetCoursesHome;
import com.example.culturallis.Controller.Queries.GetUserInfo;
import com.example.culturallis.Controller.SqLite.UserDAO;
import com.example.culturallis.Model.CoursesHome.CoursesHome;
import com.example.culturallis.Model.Entity.CourseCard;
import com.example.culturallis.Model.Usuario.Usuario;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Post.PostCourse;
import com.example.culturallis.View.Skeletons.SkeletonBlank;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CourseScreen extends AppCompatActivity {
    RecyclerView rv;

    List<CourseCard> listCourseC;

    CourseAdapter courseAdapter;
    LoadingSettings loadingDialog;
    UserDAO userDAO = new UserDAO(this);

    Boolean hasCpf = false;

    protected FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_screen);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        UserDAO userDAO = new UserDAO(this);

        try {
            String currentEmail = userDAO.getCurrentEmail();
            new CourseScreen.GetUserCpf().execute(currentEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasCpf != null ){
                    if(hasCpf){
                        startActivity(new Intent(CourseScreen.this, PostCourse.class));
                    }else {
                        Toast.makeText(CourseScreen.this, "Para criar um curso é necessário cadastrar um CPF/CNPJ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        DownNavbar downNav = new DownNavbar().newInstance(2);
        TopNavbar topNav = new TopNavbar();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.downNav, downNav);
        transaction.replace(R.id.topNav, topNav);
        transaction.commit();

        rv = findViewById(R.id.recycleView);
        listCourseC = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(64);
        rv.addItemDecoration(itemDecorator);
        rv.setLayoutManager(linearLayoutManager);

        courseAdapter = new CourseAdapter(this);

        try {
            loadingDialog = new LoadingSettings(this);
            loadingDialog.show();
            String currentEmail = userDAO.getCurrentEmail();
            new CourseScreen.GetCoursesRandonly().execute(currentEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class GetCoursesRandonly extends AsyncTask<String, Void, List<CoursesHome>> {
        @Override
        protected List<CoursesHome> doInBackground(String... params) {
            if (params.length == 1) {
                String email = params[0];

                try {
                    return new GetCoursesHome().getCoursesRandonly(email);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<CoursesHome> courseCards) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }

            if (courseCards != null) {
                for(CoursesHome crhm : courseCards){
                    listCourseC.add(new CourseCard(crhm.getPk_id(), crhm.getPostsOwnerFoto(), crhm.getUrl_midia(), crhm.getTitulo(), crhm.getPostsOwnerName(), crhm.getNumCursados(), crhm.getCurtido(), crhm.isAdquiriu()));
                }

                courseAdapter.setData(listCourseC, true);
                rv.setAdapter(courseAdapter);
            }else{
                startActivity(new Intent(CourseScreen.this, SkeletonBlank.class));
                Toast.makeText(CourseScreen.this, "Ocorreu um erro ao pegar os cursos", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void resetRV(){
        finish();
        startActivity(getIntent());
    }
    private class GetUserCpf extends AsyncTask<String, Void, Usuario> {
        @Override
        protected Usuario doInBackground(String... params) {
            if (params.length == 1) {
                String email = params[0];
                try {
                    return new GetUserInfo().getInfoUser(email);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Usuario usuarios) {
            if (usuarios != null) {
                if(usuarios.getCpf() == null  || usuarios.getCpf() == "" || usuarios.getCpf() == " " || usuarios.getCpf() == "null"){
                    hasCpf = false;
                }else{
                    hasCpf = true;
                }
            }else{
                hasCpf = false;
            }
        }
    }

}