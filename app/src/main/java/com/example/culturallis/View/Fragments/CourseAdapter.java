package com.example.culturallis.View.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.culturallis.R;

public class CourseAdapter extends BaseAdapter {
    private Context applicationContext;
    private int[] perfilImage;
    private int[] courseImage;
    private String[] courseTitle;
    private String[] courseAuthor;
    private int[] viewsCount;
    private LayoutInflater inflater;


    public CourseAdapter(Context applicationContext, int[] perfilImage, int[] courseImage, String[] courseTitle, String[] courseAuthor, int[] viewsCount) {
        this.applicationContext = applicationContext;
        this.perfilImage = perfilImage;
        this.courseImage = courseImage;
        this.courseTitle = courseTitle;
        this.courseAuthor = courseAuthor;
        this.viewsCount = viewsCount;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return courseTitle.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.course_list_view, null);
        TextView country = (TextView) view.findViewById(R.id.nomePais);
        ImageView icon = (ImageView) view.findViewById(R.id.bandeiraPais);

        country.setText(paises[i]);
        icon.setImageResource(bandeiras[i]);

        return view;
    }
}
