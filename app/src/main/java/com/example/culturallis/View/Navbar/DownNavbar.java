package com.example.culturallis.View.Navbar;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.culturallis.R;

public class DownNavbar extends Fragment {
    public DownNavbar() {
        // Required empty public constructor
    }

    public static DownNavbar newInstance(Integer type) {
        DownNavbar fragment = new DownNavbar();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int type = getArguments().getInt("type");
        View view = inflater.inflate(R.layout.fragment_complete_navbar, container, false);


        AppCompatButton postsHomeBtn = view.findViewById(R.id.btnHomePosts);
        AppCompatButton coursesHomeBtn = view.findViewById(R.id.btnHomeCourses);
        AppCompatButton profileHomeButton = view.findViewById(R.id.btnHomeProfile);

        switch (type){
            case 1:
                postsHomeBtn.setBackgroundResource(R.drawable.selected_tab);
                postsHomeBtn.setTextColor(getResources().getColor(R.color.black));
                break;
            case 2:
                coursesHomeBtn.setBackgroundResource(R.drawable.selected_tab);
                coursesHomeBtn.setTextColor(getResources().getColor(R.color.black));
                break;
            case 3:
                profileHomeButton.setBackgroundResource(R.drawable.selected_tab);
                profileHomeButton.setTextColor(getResources().getColor(R.color.black));
                break;
        }

        postsHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomeScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        coursesHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CourseScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        profileHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PerfilCourseCreatorScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        return view;
    }
}