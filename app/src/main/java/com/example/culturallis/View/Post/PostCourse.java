package com.example.culturallis.View.Post;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostCourse extends ModelAppScreens {
    private int lastModule = 0;
    private LinearLayout moduleContainer;
    private Button addButton;
    private int maxModules = 7;
    private final int GALERIA_IMAGENS = 1;
    private ImageView icon;
    private ImageView photo;
    private View layout;
    private ImageView img;
    private Button postButton;
    private static final int REQUEST_CODE = 1;
    private EditText category;
    private EditText title;
    private Button post;
    private EditText desc;
    private boolean isPhotoLoaded = false;

    private List<String> moduleTextsList = new ArrayList<>();
    private EditText[] moduleEditTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_course);

        moduleContainer = findViewById(R.id.moduleContainer);
        addButton = findViewById(R.id.addButton);
        icon = findViewById(R.id.icon);
        img = findViewById(R.id.img);
        photo = findViewById(R.id.photo);
        layout = findViewById(R.id.layout);
        postButton = findViewById(R.id.btnPostPublication);
        category = findViewById(R.id.cate);
        title = findViewById(R.id.titleEditText);
        desc = findViewById(R.id.desc);
        post = findViewById(R.id.btnPostPublication);

        moduleEditTexts = new EditText[maxModules];

        Toast.makeText(this, "*Só é possível inserir 7 módulos por curso*", Toast.LENGTH_LONG).show();
        addTextWatchers();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastModule <= maxModules) {
                    lastModule++;
                    addModelButton("Módulo " + lastModule, lastModule - 1);
                    verifyFields();
                } else {
                    addButton.setEnabled(false);
                    addButton.setBackground(getDrawable(R.drawable.disabled_button_background));
                }
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALERIA_IMAGENS);
            }
        });
    }

    private void addTextWatchers() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                verifyFields();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };

        category.addTextChangedListener(textWatcher);
        title.addTextChangedListener(textWatcher);
        desc.addTextChangedListener(textWatcher);
    }

    private void verifyFields() {
        String categ = category.getText().toString().trim();
        String title2 = title.getText().toString().trim();
        String desc2 = desc.getText().toString().trim();
        boolean fieldsNotEmpty = !categ.isEmpty() && !title2.isEmpty() && !desc2.isEmpty();

        boolean atLeastOneModuleAdded = lastModule > 0;

        if (fieldsNotEmpty && isPhotoLoaded && atLeastOneModuleAdded) {
            post.setBackground(getDrawable(R.drawable.tag_category_blue));
        } else {
            post.setBackground(getDrawable(R.drawable.disabled_button_background));
        }
    }

    private void addModelButton(String text, Integer index) {
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
        modelButton.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        modelButton.setPadding(30, 20, 366, 20);
        modelButton.setTypeface(ResourcesCompat.getFont(this, R.font.unbounded_regular));
        modelButton.setAllCaps(false);
        Drawable[] drawables = modelButton.getCompoundDrawables();
        modelButton.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3]);

        modelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModalButton(view, index);
            }
        });

        moduleContainer.addView(modelButton, moduleContainer.indexOfChild(addButton));

        if (lastModule >= maxModules) {
            addButton.setEnabled(false);
            addButton.setBackground(getDrawable(R.drawable.disabled_button_background));
        }

        moduleEditTexts[lastModule - 1] = new EditText(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALERIA_IMAGENS && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            photo.setImageURI(selectedImage);
            icon.setAlpha(0.0f);
            isPhotoLoaded = true;
            verifyFields();
        }
    }

    public void ModalButton(View view, Integer index) {
        final Context context = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.modal_course, null);
        builder.setView(dialogView);

        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        Button btnConfirm = dialogView.findViewById(R.id.btn_confirm);
        EditText editTextInModal = dialogView.findViewById(R.id.editTextURL);

        if (lastModule > 0 && lastModule <= maxModules) {
            editTextInModal.setText(moduleEditTexts[index].getText().toString());
        }

        final AlertDialog dialog = builder.create();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editTextInModal.getText().toString();

                if (lastModule > 0 && lastModule <= maxModules) {
                    moduleEditTexts[index].setText(text);
//                    Toast.makeText(context, "Texto salvo: " + text, Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
                Toast.makeText(context, "" + index, Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}
