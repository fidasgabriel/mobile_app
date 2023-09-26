    package com.example.culturallis.View.Fragments;

    import android.os.Bundle;

    import androidx.fragment.app.Fragment;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ListView;

    import com.example.culturallis.Controller.Adapter.CourseAdapter;
    import com.example.culturallis.Model.Entity.CourseCard;
    import com.example.culturallis.R;

    import java.util.ArrayList;
    import java.util.List;

    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link CoursesScroll#newInstance} factory method to
     * create an instance of this fragment.
     */
    public class CoursesScroll extends Fragment {

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        public CoursesScroll() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CoursesScroll.
         */
        // TODO: Rename and change types and number of parameters
        public static CoursesScroll newInstance(String param1, String param2) {
            CoursesScroll fragment = new CoursesScroll();
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
            View view = inflater.inflate(R.layout.fragment_courses_scroll, container, false);
            ListView courseList = view.findViewById(R.id.courseList);
            List<CourseCard> listCourseC = new ArrayList<>();
            listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título chamativo","Dr. Fidas",340, false));
            listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título super chamativo","Dr. Fidas2",1234, false));
            listCourseC.add(new CourseCard( R.drawable.culture_example,R.drawable.perfil_example,"título HIPER MEGA ULTRA chamativo","Dr. Fidas3",5678910, true));
            CourseAdapter courseAdapter = new CourseAdapter(requireContext(),listCourseC);
            courseList.setAdapter(courseAdapter);

            return view;
        }
    }