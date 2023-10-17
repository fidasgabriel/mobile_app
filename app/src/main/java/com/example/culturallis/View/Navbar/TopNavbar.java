package com.example.culturallis.View.Navbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.Security;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopNavbar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopNavbar extends Fragment {

    public TopNavbar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopNavbar.
     */
    // TODO: Rename and change types and number of parameters
    public static TopNavbar newInstance(String param1, String param2) {
        TopNavbar fragment = new TopNavbar();
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
                startActivity(new Intent(getContext(), Security.MainSettingsScreen.class));
            }
        });
        return view;
    }
}