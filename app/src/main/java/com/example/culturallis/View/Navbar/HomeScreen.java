package com.example.culturallis.View.Navbar;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.culturallis.Controller.Adapter.PostAdapter;
import com.example.culturallis.Controller.Queries.GetPostsRandomly;
import com.example.culturallis.Model.Entity.PostCard;
import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.Model.PostsHome.PostsHome;
import com.example.culturallis.Model.Usuario.Usuario;
import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.PerfilEdit;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Post.PostPublication;
import com.example.culturallis.View.Skeletons.SkeletonBlank;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeScreen extends ModelAppScreens {
    RecyclerView rv;
    LoadingSettings loadingDialog;
    Usuario currentUser;
    protected FloatingActionButton floatingActionButton;
    List<PostCard> listPostC;
    PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        DownNavbar downNav = new DownNavbar().newInstance(1);
        TopNavbar topNav = new TopNavbar();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.topNav, topNav);
        transaction.replace(R.id.downNav, downNav);
        transaction.commit();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, PostPublication.class));
            }
        });

        try {
            loadingDialog = new LoadingSettings(this);
            loadingDialog.show();
            currentUser = new Usuario();
            currentUser.setEmail("ana.damasceno@gmail.com");
            new GetPostsHomeScreen().execute(currentUser.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }

        rv = findViewById(R.id.recycleView);
        listPostC = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(64);
        rv.addItemDecoration(itemDecorator);
        rv.setLayoutManager(linearLayoutManager);

        postAdapter = new PostAdapter(this);

        postAdapter.setData(listPostC, true);
        rv.setAdapter(postAdapter);
    }

    private class GetPostsHomeScreen extends AsyncTask<String, Void, List<PostsHome>> {
        @Override
        protected List<PostsHome> doInBackground(String... params) {
            if (params.length == 1) {

                String email = params[0];

                try {
                    return new GetPostsRandomly().getPostsRandomly(email);
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
                startActivity(new Intent(HomeScreen.this, SkeletonBlank.class));
                Toast.makeText(HomeScreen.this, "Ocorreu um erro ao pegar os posts", Toast.LENGTH_SHORT).show();
            }
        }
    }
}