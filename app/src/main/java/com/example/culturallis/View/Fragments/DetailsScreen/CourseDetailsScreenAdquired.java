package com.example.culturallis.View.Fragments.DetailsScreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.culturallis.R;
import com.example.culturallis.View.Skeletons.SkeletonCourseConcluded;
import com.example.culturallis.View.Skeletons.SkeletonCourseDetails;
import com.example.culturallis.View.Skeletons.SkeletonSuccessModuleComplete;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseDetailsScreenAdquired#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseDetailsScreenAdquired extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CourseDetailsScreenAdquired() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseDetailsScreenAdquired.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseDetailsScreenAdquired newInstance(String param1, String param2) {
        CourseDetailsScreenAdquired fragment = new CourseDetailsScreenAdquired();
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
        View view = inflater.inflate(R.layout.fragment_course_details_screen_adquired, container, false);

        TextView textView = view.findViewById(R.id.titleCourse);
        ImageView img = view.findViewById(R.id.courseMainImage);
        TextView txtUser = view.findViewById(R.id.useNameTxt);
        CardView cardView = view.findViewById(R.id.cardProf);
        AppCompatButton txtModule1Title = view.findViewById(R.id.modulo1);
        AppCompatButton txtModule2Title = view.findViewById(R.id.modulo2);
        AppCompatButton txtModule3Title = view.findViewById(R.id.modulo3);
        AppCompatButton txtModule4Title = view.findViewById(R.id.modulo4);
        AppCompatButton txtModule5Title = view.findViewById(R.id.modulo5);
        AppCompatButton txtModule6Title = view.findViewById(R.id.modulo6);

        int[] colorBorderSelected = {
                R.drawable.border_image_blue,
                R.drawable.border_image_orange,
                R.drawable.border_image_purple,
        };

        int[] colorAvatarBorder = {
                R.color.blue_course,
                R.color.orange_course,
                R.color.purple_course,
        };

        int[] colorTagCategory = {
                R.drawable.modules_button_blue,
                R.drawable.modules_button_orange,
                R.drawable.modules_button_purple,
        };


        Random random = new Random();
        int randomIdx;
        randomIdx = random.nextInt(colorBorderSelected.length);


        int randomBorderSelected = colorBorderSelected[randomIdx];
        int randomAvatarBorder = colorAvatarBorder[randomIdx];
        int randomTagColor = colorTagCategory[randomIdx];


        img.setBackground(getResources().getDrawable(randomBorderSelected));
        cardView.setCardBackgroundColor(getResources().getColor(randomAvatarBorder));
        txtModule1Title.setBackground(getResources().getDrawable(randomTagColor));
        txtModule2Title.setBackground(getResources().getDrawable(randomTagColor));
        txtModule3Title.setBackground(getResources().getDrawable(randomTagColor));
        txtModule4Title.setBackground(getResources().getDrawable(randomTagColor));
        txtModule5Title.setBackground(getResources().getDrawable(randomTagColor));
        txtModule6Title.setBackground(getResources().getDrawable(randomTagColor));

        txtUser.setText("Culturallis");
        img.setImageResource(R.drawable.logo);
        textView.setText("Título Chamativo");

        return view;
    }
}