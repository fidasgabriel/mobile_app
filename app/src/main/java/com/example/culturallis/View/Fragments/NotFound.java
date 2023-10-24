package com.example.culturallis.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.culturallis.Controller.GlobalUtilization;
import com.example.culturallis.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotFound#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotFound extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotFound() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment not_found.
     */
    // TODO: Rename and change types and number of parameters
    public static NotFound newInstance(String param1, String param2) {
        NotFound fragment = new NotFound();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_server_error, container, false);
        TextView textView = view.findViewById(R.id.error_oops);
        List<Integer> listColors = new ArrayList<>();
        listColors.add(R.color.base_red);
        listColors.add(R.color.base_orange);
        listColors.add(R.color.base_yellow);
        listColors.add(R.color.base_violet);
        listColors.add(R.color.base_purple);
        listColors.add(R.color.purple_menu);
        listColors.add(R.color.base_blue);
        GlobalUtilization.coloringTexts(textView,"Oops...", listColors);

        return view;

    }
}