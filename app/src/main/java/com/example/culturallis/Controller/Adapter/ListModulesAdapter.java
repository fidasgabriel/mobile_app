package com.example.culturallis.Controller.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatButton;

import com.example.culturallis.R;
import com.example.culturallis.View.Skeletons.SkeletonSuccessModuleComplete;

import java.util.List;

public class ListModulesAdapter extends ArrayAdapter<String> {
    private Context context;
    private int resource;

    public ListModulesAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        AppCompatButton button = convertView.findViewById(R.id.moduleButton);

        button.setText("Módulo " + (position + 1));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getItem(position);

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                getContext().startActivity(i);
            }
        });


        return convertView;
    }
}
