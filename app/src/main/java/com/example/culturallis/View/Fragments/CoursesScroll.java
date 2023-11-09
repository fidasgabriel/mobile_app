    package com.example.culturallis.View.Fragments;

    import android.content.Intent;
    import android.os.Bundle;

    import android.widget.AdapterView;
    import androidx.fragment.app.Fragment;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ListView;

    import com.example.culturallis.Controller.Adapter.CourseAdapter;
    import com.example.culturallis.Model.Entity.CourseCard;
    import com.example.culturallis.Model.Entity.CourseList;
    import com.example.culturallis.R;
    import com.example.culturallis.View.Fragments.DetailsScreen.CourseDetailsScreenNotAdquired;
    import com.example.culturallis.View.Skeletons.SkeletonCourseDetails;
    import com.example.culturallis.View.Skeletons.SkeletonSelectedItem;

    import java.util.ArrayList;
    import java.util.List;
    public class CoursesScroll extends Fragment {

        public CoursesScroll() {}

        public static CoursesScroll newInstance(CourseList lista) {
            CoursesScroll fragment = new CoursesScroll();
            Bundle args = new Bundle();
            args.putSerializable("lista", lista);
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
            View view = inflater.inflate(R.layout.fragment_courses_scroll, container, false);
            ListView courseList = view.findViewById(R.id.courseList);
            if (getArguments() != null) {
                CourseList myList = (CourseList) getArguments().getSerializable("lista");
                if (myList != null) {
                    List<CourseCard> listCourseC = myList.getLista();

//                    CourseAdapter courseAdapter = new CourseAdapter(requireContext(),listCourseC);
//                    courseList.setAdapter(courseAdapter);
//
//                    courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            startActivity(new Intent(getActivity(), SkeletonSelectedItem.class));
//                        }
//                    });
                }
            }
            return view;
        }
    }