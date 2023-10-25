package com.example.culturallis.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.culturallis.R;
public class FilterPerfil extends Fragment {
    private FragmentManager transaction;

    public FilterPerfil() {}

    public static FilterPerfil newInstance(Integer type) {
        FilterPerfil fragment = new FilterPerfil();
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
        transaction = requireActivity().getSupportFragmentManager();
        View view = inflater.inflate(R.layout.fragment_filter_perfil, container, false);
        ImageView filterIcon = view.findViewById(R.id.filterIcon);
        Integer type = getArguments().getInt("type");  // Substitua pelos itens desejados

        filterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterDialogFragment dialogFragment = new FilterDialogFragment(type);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                dialogFragment.show(fragmentManager, "FilterDialogFragment");
            }
        });
        return view;
    }
}