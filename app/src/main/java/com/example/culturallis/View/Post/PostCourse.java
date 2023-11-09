package com.example.culturallis.View.Post;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
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

import com.example.culturallis.Controller.Mutations.CreateCourse;
import com.example.culturallis.Controller.Mutations.CreatePost;
import com.example.culturallis.Controller.SqLite.UserDAO;
import com.example.culturallis.Model.Entity.LoginUserEntity;
import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Navbar.HomeScreen;
import com.example.culturallis.View.Skeletons.SkeletonBlank;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import okhttp3.Response;

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
    String base64Image;
    private boolean isPhotoLoaded = false;

    private List<String> moduleTextsList = new ArrayList<>();
    private EditText[] moduleEditTexts;

    LoadingSettings loadingDialog;

    private String selectedImagePath;

    private UserDAO userDAO = new UserDAO(this);

    private LoginUserEntity user;

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

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().toString().trim().length() == 0 || desc.getText().toString().trim().length() == 0 || category.getText().toString().trim().length() == 0){
                    Toast.makeText(PostCourse.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        loadingDialog = new LoadingSettings(PostCourse.this);
                        loadingDialog.show();
                        if (selectedImagePath != null && !selectedImagePath.isEmpty()) {
                            base64Image = encodeImage(selectedImagePath);
                            if (base64Image.length() > 100000){
                                Toast.makeText(PostCourse.this, "Foto muito grande", Toast.LENGTH_SHORT).show();
                            }
                        }
                        new CreateCourseUser().execute(user.getEmail(),
                                title.getText().toString().trim(),
                                base64Image,
                                desc.getText().toString().trim(),
                                category.getText().toString().trim(),
                                "0");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();

            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, projection, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    selectedImagePath = cursor.getString(columnIndex);
                }
                cursor.close();
            }

            if (selectedImagePath != null) {
                icon.setAlpha(0.0f);
                photo.setImageURI(selectedImage);
            }
        }
        verifyFields();
        isPhotoLoaded = true;

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

                for(int i = 0; i < maxModules; i++){
                    moduleTextsList.add(null);
                }

                if (lastModule > 0 && lastModule <= maxModules) {
                    moduleEditTexts[index].setText(text);
                    if(moduleTextsList.get(index) == null){
                        moduleTextsList.add(text);
                    }else {
                        moduleTextsList.set(index, text);
                    }
                }
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private String encodeImage(String imagePath) {
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

            int maxWidth = 480;
            int maxHeight = 500;
            if (bitmap.getWidth() > maxWidth || bitmap.getHeight() > maxHeight) {
                float scale = Math.min(((float) maxWidth / bitmap.getWidth()), ((float) maxHeight / bitmap.getHeight()));
                Matrix matrix = new Matrix();
                matrix.postScale(scale, scale);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);

            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String base64Image = Base64.encodeToString(byteArray, Base64.NO_WRAP);

            return base64Image;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error encoding image", Toast.LENGTH_SHORT).show();
            return null;
        }
    }


    private class CreateCourseUser extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            if (params.length != 6) {
                return false;
            }

            String email = params[0];
            String nome = params[1];
            String fotoPost = params[2];
            String descricao = params[3];
            String categoria = params[4];
            Double preco = Double.valueOf(params[5]);

            try {
                CreateCourse mutations = new CreateCourse();
                Response response = mutations.createCourse(email, nome, fotoPost, descricao, categoria, preco, moduleTextsList);
                return response.isSuccessful();
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }

            if (success) {
                Toast.makeText(PostCourse.this, "Curso criado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PostCourse.this, HomeScreen.class));
                finish();
            } else {
                startActivity(new Intent(PostCourse.this, SkeletonBlank.class));
                Toast.makeText(PostCourse.this, "Ocorreu um erro ao criar seu curso", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
