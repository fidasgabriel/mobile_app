package com.example.culturallis.View.Navbar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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


        ImageView postsHomeBtn = view.findViewById(R.id.btnHomePosts);
        ImageView coursesHomeBtn = view.findViewById(R.id.btnHomeCourses);
        ImageView profileHomeButton = view.findViewById(R.id.btnHomeProfile);

        postsHomeBtn.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));


        switch (type){
            case 1:
                postsHomeBtn.setBackgroundResource(R.color.base_gray);
                postsHomeBtn.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
                coursesHomeBtn.setBackgroundColor(0);
                coursesHomeBtn.setColorFilter(ContextCompat.getColor(getContext(), R.color.icon_home));
                profileHomeButton.setBackgroundColor(0);
                profileHomeButton.setColorFilter(ContextCompat.getColor(getContext(), R.color.icon_home));
                break;
            case 2:
                coursesHomeBtn.setBackgroundResource(R.color.base_gray);
                coursesHomeBtn.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
                postsHomeBtn.setBackgroundColor(0);
                postsHomeBtn.setColorFilter(ContextCompat.getColor(getContext(), R.color.icon_home));
                profileHomeButton.setBackgroundColor(0);
                profileHomeButton.setColorFilter(ContextCompat.getColor(getContext(), R.color.icon_home));
                break;
            case 3:
                profileHomeButton.setBackgroundResource(R.color.base_gray);
                profileHomeButton.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
                coursesHomeBtn.setBackgroundColor(0);
                coursesHomeBtn.setColorFilter(ContextCompat.getColor(getContext(), R.color.icon_home));
                postsHomeBtn.setBackgroundColor(0);
                postsHomeBtn.setColorFilter(ContextCompat.getColor(getContext(), R.color.icon_home));
                break;
        }

        postsHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type != 1){
                    Intent intent = new Intent(getContext(), HomeScreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }else{
                    ((HomeScreen) getActivity()).resetRV();
                }
            }
        });

        coursesHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type != 2) {
                    Intent intent = new Intent(getContext(), CourseScreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }else{
                    ((CourseScreen) getActivity()).resetRV();
                }
            }
        });

        profileHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type != 3) {
                    Intent intent = new Intent(getContext(), PerfilCourseCreatorScreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }else{
                    ((PerfilCourseCreatorScreen) getActivity()).resetRV();
                }
            }
        });

        return view;
    }
}