package com.example.culturallis.View.Navbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.example.culturallis.Controller.Adapter.CourseAdapter;
import com.example.culturallis.Controller.Adapter.PostAdapter;
import com.example.culturallis.Model.Entity.CourseCard;
import com.example.culturallis.Model.Entity.PostCard;
import com.example.culturallis.R;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity {
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        DownNavbar downNav = new DownNavbar().newInstance(1);
        TopNavbar topNav = new TopNavbar();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.topNav, topNav);
        transaction.replace(R.id.downNav, downNav);
        transaction.commit();

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


        rv = findViewById(R.id.recycleView);
        List<PostCard> listPostC = new ArrayList<>();
        listPostC.add(new PostCard( R.drawable.culture_example,R.drawable.perfil_example,"Dr. Fidas",false, false,"Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula mattis tellus"));
        listPostC.add(new PostCard( R.drawable.culture_example,R.drawable.perfil_example,"Dr. Fidas2",false,true,"Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula mattis tellus"));
        listPostC.add(new PostCard( R.drawable.culture_example,R.drawable.perfil_example,"Dr. Fidas3",true, false,"Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula mattis tellus"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(64);
        rv.addItemDecoration(itemDecorator);
        rv.setLayoutManager(linearLayoutManager);
        rv.setFocusable(false);
        rv.setNestedScrollingEnabled(false);

        PostAdapter postAdapter = new PostAdapter(this);

        postAdapter.setData(listPostC, true);
        rv.setAdapter(postAdapter);
    }
}