package com.example.culturallis.View.Fragments.SuccessScreens;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.culturallis.Controller.GlobalUtilization;
import com.example.culturallis.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompleteCourseSuccess#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompleteCourseSuccess extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CompleteCourseSuccess() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompleteCourseSuccess.
     */
    // TODO: Rename and change types and number of parameters
    public static CompleteCourseSuccess newInstance(String param1, String param2) {
        CompleteCourseSuccess fragment = new CompleteCourseSuccess();
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
        List<Integer> colors = new ArrayList<>();
        colors.add(R.color.base_green);
        colors.add(R.color.base_green);
        colors.add(R.color.base_green);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        colors.add(R.color.black);
        View view = inflater.inflate(R.layout.fragment_complete_course_success, container, false);
        TextView textView = view.findViewById(R.id.textConcluceCourse);
        GlobalUtilization.coloringTexts(textView, "+ 1 " + textView.getText(), colors);
        return view;
    }
}