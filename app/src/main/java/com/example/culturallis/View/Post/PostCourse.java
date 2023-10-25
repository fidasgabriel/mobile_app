package com.example.culturallis.View.Post;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.culturallis.R;

public class PostCourse extends AppCompatActivity {
    private int lastModule = 1;
    private LinearLayout moduleContainer;
    private Button addButton;
    private Button modulo1;
    private int maxModules = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_course);

        moduleContainer = findViewById(R.id.moduleContainer);
        addButton = findViewById(R.id.addButton);
        modulo1 = findViewById(R.id.modulo1);

        Toast.makeText(this, "*Só é possível inserir 7 módulos por curso*", Toast.LENGTH_LONG).show();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastModule < maxModules) {
                    lastModule++;
                    addModelButton("Módulo " + lastModule);
                } else {
                    addButton.setEnabled(false);
                }
            }
        });
    }

    private void addModelButton(String text) {
        AppCompatButton modelButton = new AppCompatButton(this);
        modelButton.setText(text);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(0, 0, 0, 7);
        modelButton.setLayoutParams(layoutParams);

        modelButton.setTextSize(18);
        modelButton.setTextColor(ContextCompat.getColor(this, R.color.white));
        modelButton.setBackground(ContextCompat.getDrawable(this, R.drawable.tag_category_blue));
        modelButton.setCompoundDrawablePadding(16);

        modelButton.setPadding(0, 20, 366, 20);
        modelButton.setTypeface(ResourcesCompat.getFont(this, R.font.unbounded_regular));
        modelButton.setAllCaps(false);
        Drawable[] drawables = modelButton.getCompoundDrawables();
        modelButton.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3]);
        modelButton.setTypeface(null, Typeface.BOLD);

        int position = moduleContainer.indexOfChild(modulo1);

        moduleContainer.addView(modelButton, position + lastModule - 1);

        if (lastModule >= maxModules) {
            addButton.setEnabled(false);
        }
    }
}
