package com.example.culturallis.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.culturallis.R;

public class BlankPerfilRecycle extends Fragment {

    private String text;

    public BlankPerfilRecycle() {
        // Required empty public constructor
    }

    public static BlankPerfilRecycle newInstance(String text) {
        BlankPerfilRecycle fragment = new BlankPerfilRecycle();
        Bundle args = new Bundle();
        args.putString("text", text);
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
        View view = inflater.inflate(R.layout.fragment_blank_perfil_recycle, container, false);
        String msgText = getArguments().getString("text");
        ((TextView) view.findViewById(R.id.textRvBlank)).setText(msgText);
        return view;
    }
}