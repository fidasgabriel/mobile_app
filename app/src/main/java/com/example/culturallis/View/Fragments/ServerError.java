package com.example.culturallis.View.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.culturallis.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServerError#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServerError extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ServerError() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment server_error.
     */
    // TODO: Rename and change types and number of parameters
    public static ServerError newInstance(String param1, String param2) {
        ServerError fragment = new ServerError();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_server_error, container, false);
//    }
//
//
//    @SuppressLint("ClickableViewAccessibility")
//    public void onCreateColor(Bundle serverError) {
//        super.onCreate(serverError);

        String text = "Ocorreu um erro no servidor interno";
        String oops = "Oops...";
        String correct = "Estamos trabalhando na correção!";

        SpannableString ss = new SpannableString(oops);
        @SuppressLint("ResourceAsColor")
        ForegroundColorSpan colorRed = new ForegroundColorSpan(R.color.base_red);
        @SuppressLint("ResourceAsColor")
        ForegroundColorSpan colorOrange = new ForegroundColorSpan(R.color.base_orange);
        @SuppressLint("ResourceAsColor")
        ForegroundColorSpan colorYellow = new ForegroundColorSpan(R.color.base_yellow);
        @SuppressLint("ResourceAsColor")
        ForegroundColorSpan colorViolet = new ForegroundColorSpan(R.color.base_violet);
        @SuppressLint("ResourceAsColor")
        ForegroundColorSpan colorPurple = new ForegroundColorSpan(R.color.base_purple);
        @SuppressLint("ResourceAsColor")
        ForegroundColorSpan colorPurple2 = new ForegroundColorSpan(R.color.purple_menu);
        @SuppressLint("ResourceAsColor")
        ForegroundColorSpan colorBlue = new ForegroundColorSpan(R.color.base_blue);

        ss.setSpan(colorRed,0, 0, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(colorOrange,1, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(colorYellow,2, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(colorViolet,3, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(colorPurple,4, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(colorPurple2,5, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(colorBlue,6, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView errorText = findViewById(R.id.errorText);
        TextView opsText = findViewById(R.id.error_oops);
        TextView correctedText = findBy


    }


}