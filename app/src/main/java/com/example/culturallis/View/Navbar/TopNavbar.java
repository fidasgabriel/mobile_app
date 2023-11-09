package com.example.culturallis.View.Navbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.MainSettingsScreen;
import com.example.culturallis.View.Configuration.Security;

public class TopNavbar extends Fragment {

    public TopNavbar() {}

    public static TopNavbar newInstance() {
        TopNavbar fragment = new TopNavbar();
        Bundle args = new Bundle();
        args.putBoolean("isHome", true);
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_navbar, container, false);
        ImageView settings = view.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainSettingsScreen.class));
            }
        });

        ImageView logo = view.findViewById(R.id.logoTypeNavbar);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments() == null) {
                    startActivity(new Intent(getContext(), HomeScreen.class));
                } else {
                    ((HomeScreen) getActivity()).resetRV();
                }
            }
        });
        return view;
    }
}