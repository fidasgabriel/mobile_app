package com.example.culturallis.View.Navbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;

import com.example.culturallis.Controller.Adapter.CourseAdapter;
import com.example.culturallis.Controller.Queries.GetCoursesHome;
import com.example.culturallis.Controller.Queries.GetPostsRandomly;
import com.example.culturallis.Controller.SqLite.UserDAO;
import com.example.culturallis.Model.CoursesHome.CoursesHome;
import com.example.culturallis.Model.Entity.CourseCard;
import com.example.culturallis.Model.Entity.LoginUserEntity;
import com.example.culturallis.Model.Entity.PostCard;
import com.example.culturallis.Model.PostsHome.PostsHome;
import com.example.culturallis.Model.Usuario.Usuario;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Skeletons.SkeletonBlank;
import com.example.culturallis.View.Skeletons.SkeletonSelectedItem;

import java.util.ArrayList;
import java.util.List;

public class CourseScreen extends AppCompatActivity {
    RecyclerView rv;

    List<CourseCard> listCourseC;

    CourseAdapter courseAdapter;

    LoadingSettings loadingDialog;

    private UserDAO userDAO = new UserDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_screen);

        SpannableString underline = new SpannableString("Carregar mais");
        UnderlineSpan underlineSpan = new UnderlineSpan();
        underline.setSpan(underlineSpan, 0, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView linkLogon = findViewById(R.id.linkCarregar);
        linkLogon.setText(underline);

        linkLogon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Colocar aqui a lógica para carregar mais 5 cards
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

        courseAdapter.setData(listCourseC, true);
        rv.setAdapter(courseAdapter);

        try {
            loadingDialog = new LoadingSettings(this);
            loadingDialog.show();
            LoginUserEntity user = userDAO.getLogin();
            new CourseScreen.GetCoursesRandonly().execute(user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }


//        rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                startActivity(new Intent(CourseScreen.this, SkeletonSelectedItem.class));
//            }
//        });
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
                    listCourseC.add(new CourseCard(crhm.getPk_id(), crhm.getPostsOwnerFoto(), crhm.getUrl_midia(), crhm.getTitulo(), crhm.getPostsOwnerName(), crhm.getNumCursados(), crhm.getCurtido(), crhm.getAdquiriu()));
                    courseAdapter.notifyDataSetChanged();
                }
            }else{
                startActivity(new Intent(CourseScreen.this, SkeletonBlank.class));
                Toast.makeText(CourseScreen.this, "Ocorreu um erro ao pegar os cursos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}