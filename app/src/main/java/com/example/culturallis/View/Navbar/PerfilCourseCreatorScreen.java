package com.example.culturallis.View.Navbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.culturallis.Controller.Adapter.CourseAdapter;
import com.example.culturallis.Controller.Adapter.PostAdapter;
import com.example.culturallis.Model.Entity.CourseCard;
import com.example.culturallis.Model.Entity.PostCard;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.FilterPerfil;

import com.example.culturallis.Controller.Queries.GetOwnPostsRamdonly;
import com.example.culturallis.Controller.Queries.GetPostsRandomly;
import com.example.culturallis.Controller.Queries.GetUserInfo;
import com.example.culturallis.Model.Entity.CourseCard;
import com.example.culturallis.Model.Entity.PostCard;
import com.example.culturallis.Model.PostsHome.PostsHome;
import com.example.culturallis.Model.Usuario.Usuario;
import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.PerfilEdit;
import com.example.culturallis.View.Fragments.FilterPerfil;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Skeletons.SkeletonBlank;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class PerfilCourseCreatorScreen extends AppCompatActivity {
    private ImageView perfilHome;
    private ImageView perfilCourse;
    private ImageView perfilCourseCreated;
    private ImageView perfilSaved;
    private RecyclerView rv;
    private FragmentTransaction transaction;
    public List<CourseCard> listCourseC;
    public List<CourseCard> listCreatedCourseC;
    private boolean isListCourseCReversed = false;
    private boolean savedIsPost = true;

    LoadingSettings loadingDialog;
    Usuario currentUser;
    List<PostCard> listPostC;
    PostAdapter postAdapter;

    TextView txtUsername;

    TextView txtUserBio;

    ImageView imgUserPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_course_creator_screen);


        Random rand = new Random();

        DownNavbar downNav = new DownNavbar().newInstance(3);
        TopNavbar topNav = new TopNavbar();
        FragmentManager fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.topNav, topNav);
        transaction.replace(R.id.downNav, downNav);
        transaction.commit();

        txtUserBio = findViewById(R.id.textView13);
        txtUsername = findViewById(R.id.userName);
        imgUserPhoto = findViewById(R.id.perfilImage);
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
        perfilCourseCreated = findViewById(R.id.perfilCreated);
        perfilSaved = findViewById(R.id.perfilSaved);

        listPostC = new ArrayList<>();

        Picasso.with(imgUserPhoto.getContext()).load("https://cdn.pixabay.com/photo/2012/04/26/19/43/profile-42914_1280.png").into(imgUserPhoto);

        try {
            loadingDialog = new LoadingSettings(PerfilCourseCreatorScreen.this);
            loadingDialog.show();
            currentUser = new Usuario();
            currentUser.setEmail("ana.damasceno@gmail.com");
            new PerfilCourseCreatorScreen.GetUserProfileTask().execute(currentUser.getEmail());
            new PerfilCourseCreatorScreen.GetOwnPostsHomeScreen().execute(currentUser.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }

        postAdapter = new PostAdapter(PerfilCourseCreatorScreen.this);

        postAdapter.setData(listPostC, false);
        rv.setAdapter(postAdapter);


        perfilHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfilHome.setBackgroundResource(R.drawable.perfil_option_selected);
                perfilCourse.setBackgroundResource(0);
                perfilCourseCreated.setBackgroundResource(0);
                perfilSaved.setBackgroundResource(0);

                perfilHome.setColorFilter(cianColor, PorterDuff.Mode.SRC_IN);
                perfilCourse.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);
                perfilCourseCreated.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);
                perfilSaved.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);

                Fragment fragment = fragmentManager.findFragmentById(R.id.filterLayout);

                 listPostC = new ArrayList<>();

                try {
                    loadingDialog = new LoadingSettings(PerfilCourseCreatorScreen.this);
                    loadingDialog.show();
                    currentUser = new Usuario();
                    currentUser.setEmail("ana.damasceno@gmail.com");
                    new PerfilCourseCreatorScreen.GetOwnPostsHomeScreen().execute(currentUser.getEmail());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                 postAdapter = new PostAdapter(PerfilCourseCreatorScreen.this);

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
                perfilCourseCreated.setBackgroundResource(0);
                perfilSaved.setBackgroundResource(0);

                perfilHome.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);
                perfilCourse.setColorFilter(cianColor, PorterDuff.Mode.SRC_IN);
                perfilCourseCreated.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);
                perfilSaved.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);

                listCourseC = new ArrayList<>();
//                listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título chamativo","Dr. Fidas",340, false));
//                listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título super chamativo","Dr. Fidas2",1234, false));
//                listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título HIPER MEGA ULTRA chamativo","Dr. Fidas3",5678910, true));

                CourseAdapter courseAdapter = new CourseAdapter(PerfilCourseCreatorScreen.this);

                courseAdapter.setData(listCourseC, false);
                rv.setAdapter(courseAdapter);

                Fragment fragment = fragmentManager.findFragmentById(R.id.filterLayout);

                if (fragment == null) {
                    transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.filterLayout, new FilterPerfil().newInstance(1));
                    transaction.commit();
                }
//                FragmentTransaction perfilCourseTransaction = getSupportFragmentManager().beginTransaction();
//                perfilCourseTransaction.replace(R.id.chgContent, coursesScroll);
//                perfilCourseTransaction.commit();
            }
        });

        perfilCourseCreated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfilHome.setBackgroundResource(0);
                perfilCourse.setBackgroundResource(0);
                perfilCourseCreated.setBackgroundResource(R.drawable.perfil_option_selected);
                perfilSaved.setBackgroundResource(0);

                perfilHome.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);
                perfilCourse.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);
                perfilCourseCreated.setColorFilter(cianColor, PorterDuff.Mode.SRC_IN);
                perfilSaved.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);

                listCreatedCourseC = new ArrayList<>();


                CourseAdapter courseAdapter = new CourseAdapter(PerfilCourseCreatorScreen.this);

                courseAdapter.setData(listCreatedCourseC, false);
                rv.setAdapter(courseAdapter);

                Fragment fragment = fragmentManager.findFragmentById(R.id.filterLayout);

                if (fragment == null) {
                    transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.filterLayout, new FilterPerfil().newInstance(2));
                    transaction.commit();
                }

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
                perfilCourseCreated.setBackgroundResource(0);
                perfilSaved.setBackgroundResource(R.drawable.perfil_option_selected);

                perfilHome.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);
                perfilCourse.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);
                perfilCourseCreated.setColorFilter(blackColor, PorterDuff.Mode.SRC_IN);
                perfilSaved.setColorFilter(cianColor, PorterDuff.Mode.SRC_IN);

                if(savedIsPost){
                    List<PostCard> listPostC = new ArrayList<>();
//                    listPostC.add(new PostCard( R.drawable.culture_example,R.drawable.perfil_example,"Dr. Fidas",false, false,"Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula mattis tellus"));
//                    listPostC.add(new PostCard( R.drawable.culture_example,R.drawable.perfil_example,"Dr. Fidas2",false,true,"Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula mattis tellus"));
//                    listPostC.add(new PostCard( R.drawable.culture_example,R.drawable.perfil_example,"Dr. Fidas3",true, false,"Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula mattis tellus"));

                    PostAdapter postAdapter = new PostAdapter(PerfilCourseCreatorScreen.this);

                    postAdapter.setData(listPostC, true);
                    rv.setAdapter(postAdapter);
                }else{
                    listCourseC = new ArrayList<>();
//                    listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título chamativo","Dr. Fidas",340, false));
//                    listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título super chamativo","Dr. Fidas2",1234, false));
//                    listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título HIPER MEGA ULTRA chamativo","Dr. Fidas3",5678910, true));

                    CourseAdapter courseAdapter = new CourseAdapter(PerfilCourseCreatorScreen.this);

                    courseAdapter.setData(listCourseC, false);
                    rv.setAdapter(courseAdapter);
                }

                Fragment fragment = fragmentManager.findFragmentById(R.id.filterLayout);

                if (fragment == null) {
                    transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.filterLayout, new FilterPerfil().newInstance(3));
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
        if(type <= 2) {
            if (option == 0 && !isListCourseCReversed) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);
                rv.setLayoutManager(linearLayoutManager);
                isListCourseCReversed = true;

            } else if (option == 1 && isListCourseCReversed) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setReverseLayout(false);
                linearLayoutManager.setStackFromEnd(false);
                rv.setLayoutManager(linearLayoutManager);
                isListCourseCReversed = false;
            }
        }else if (type == 3){ // Para conteúdo Salvo
            if (option == 0 && !savedIsPost){
                List<PostCard> listPostC = new ArrayList<>();
//                listPostC.add(new PostCard( R.drawable.culture_example,R.drawable.perfil_example,"Dr. Fidas",false, false,"Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula mattis tellus"));
//                listPostC.add(new PostCard( R.drawable.culture_example,R.drawable.perfil_example,"Dr. Fidas2",false,true,"Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula mattis tellus"));
//                listPostC.add(new PostCard( R.drawable.culture_example,R.drawable.perfil_example,"Dr. Fidas3",true, false,"Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula mattis tellus"));

                PostAdapter postAdapter = new PostAdapter(PerfilCourseCreatorScreen.this);

                postAdapter.setData(listPostC, true);
                rv.setAdapter(postAdapter);
                savedIsPost = true;
            }else if(option == 1 && savedIsPost){
                listCourseC = new ArrayList<>();
//                listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título chamativo","Dr. Fidas",340, false));
//                listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título super chamativo","Dr. Fidas2",1234, false));
//                listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título HIPER MEGA ULTRA chamativo","Dr. Fidas3",5678910, true));

                CourseAdapter courseAdapter = new CourseAdapter(PerfilCourseCreatorScreen.this);

                courseAdapter.setData(listCourseC, false);
                rv.setAdapter(courseAdapter);
                savedIsPost = false;
            }
        }
    }

    private class GetOwnPostsHomeScreen extends AsyncTask<String, Void, List<PostsHome>> {
        @Override
        protected List<PostsHome> doInBackground(String... params) {
            if (params.length == 1) {

                String email = params[0];

                try {
                    return new GetOwnPostsRamdonly().getOwnPostsRandomly(email);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<PostsHome> postsHome) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }

            if (postsHome != null) {
                for(PostsHome pthm : postsHome){
                    listPostC.add(new PostCard(pthm.getPk_id(), pthm.getUrl_midia(), pthm.getPostsOwnerFoto(), pthm.getPostsOwnerName(), pthm.getCurtido(), pthm.getCurtido(), pthm.getDescricao()));
                    postAdapter.notifyDataSetChanged();
                }
            }else{
                startActivity(new Intent(PerfilCourseCreatorScreen.this, SkeletonBlank.class));
                Toast.makeText(PerfilCourseCreatorScreen.this, "Ocorreu um erro ao pegar seus próprios posts", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class GetUserProfileTask extends AsyncTask<String, Void, Usuario> {
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
        protected void onPostExecute(Usuario user) {
            if (user != null) {
                if(user.getNome_usuario() != null){
                    txtUsername.setText("@"+user.getNome_usuario().toString());

                }else{
                    txtUsername.setText("");
                }
                if(user.getUrl_foto()!= null && !user.getUrl_foto().isEmpty()){
                    byte[] decodedImage = Base64.decode(user.getUrl_foto(), Base64.DEFAULT);

                    Bitmap imageBitmap = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.length);

                    Glide.with(PerfilCourseCreatorScreen.this)
                            .load(imageBitmap)
                            .into(imgUserPhoto);
                }else{
                    Picasso.with(PerfilCourseCreatorScreen.this).load("https://cdn.pixabay.com/photo/2012/04/26/19/43/profile-42914_1280.png").into(imgUserPhoto);
                }
                if(user.getBio() != null){
                    txtUserBio.setText(user.getBio().toString());
                }else{
                    txtUserBio.setText("");
                }

                currentUser = user;
            }else{
                startActivity(new Intent(PerfilCourseCreatorScreen.this, SkeletonBlank.class));
                Toast.makeText(PerfilCourseCreatorScreen.this, "Ocorreu um erro ao pegar seus dados", Toast.LENGTH_SHORT).show();
            }
        }
    }
}