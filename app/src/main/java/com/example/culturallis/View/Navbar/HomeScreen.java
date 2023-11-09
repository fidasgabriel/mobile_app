package com.example.culturallis.View.Navbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.culturallis.Controller.Adapter.PostAdapter;
import com.example.culturallis.Controller.Queries.GetPostsRandomly;
import com.example.culturallis.Controller.SqLite.UserDAO;
import com.example.culturallis.Model.Entity.PostCard;
import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.Model.PostsHome.PostsHome;
import com.example.culturallis.Model.Usuario.Usuario;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Fragments.NotConnected;
import com.example.culturallis.View.Post.PostPublication;
import com.example.culturallis.View.Skeletons.SkeletonBlank;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends ModelAppScreens {
    RecyclerView rv;
    LoadingSettings loadingDialog;
    Usuario currentUser;
    protected FloatingActionButton floatingActionButton;
    private UserDAO userDAO = new UserDAO(this);
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

        String currentEmail = userDAO.getCurrentEmail();
        try {
            loadingDialog = new LoadingSettings(this);
            loadingDialog.show();
            currentUser = new Usuario();
            currentUser.setEmail(currentEmail);
            new GetPostsHomeScreen().execute(currentEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        rv = findViewById(R.id.recycleView);
        listPostC = new ArrayList<>();
        SpannableString underline = new SpannableString("Carregar mais");
        UnderlineSpan underlineSpan = new UnderlineSpan();
        underline.setSpan(underlineSpan, 0, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(64);
        rv.addItemDecoration(itemDecorator);
        rv.setLayoutManager(linearLayoutManager);

        postAdapter = new PostAdapter(this);
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
                for (PostsHome pthm : postsHome) {
                    listPostC.add(new PostCard(pthm.getPk_id(), pthm.getUrl_midia(), pthm.getPostsOwnerFoto(),
                            pthm.getPostsOwnerName(), pthm.getCurtido(), pthm.getSalvo(), pthm.getDescricao()));
                    postAdapter.notifyDataSetChanged();
                }
                postAdapter.setData(listPostC, true);
                rv.setAdapter(postAdapter);
            } else {
                startActivity(new Intent(HomeScreen.this, SkeletonBlank.class));
                Toast.makeText(HomeScreen.this, "Ocorreu um erro ao pegar os posts", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void resetRV(){
        finish();
        startActivity(getIntent());
    }
}