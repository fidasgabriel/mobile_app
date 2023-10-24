package com.example.culturallis.View.Post;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.culturallis.R;

public class PostCourse extends AppCompatActivity {
    private int lastModel = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_course);

        Button addButton = ((Button) findViewById(R.id.modulo6));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastModel++;
                addModelButton("Modelo " + lastModel);
            }
        });
    }

    private void addModelButton(String text) {
        ViewGroup layout = findViewById(R.id.layout);

        Button modelButton = new Button(this);
        modelButton.setText(text);
        layout.addView(modelButton);
    }
}