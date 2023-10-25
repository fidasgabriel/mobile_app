package com.example.culturallis.View.Post;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.culturallis.Controller.Mutations.CreatePost;
import com.example.culturallis.Controller.Mutations.LogonUser;
import com.example.culturallis.Controller.Mutations.UpdateUser;
import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.Model.Usuario.Usuario;
import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.PerfilEdit;
import com.example.culturallis.View.Entrance.LogIn;
import com.example.culturallis.View.Entrance.LogOn;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Navbar.HomeScreen;
import com.example.culturallis.View.Skeletons.SkeletonBlank;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Response;

public class PostPublication extends ModelAppScreens {

    private ImageView photo;
    private final int GALERIA_IMAGENS = 1;
    private static final int PERMISSAO_REQUEST = 1;
    private static final int REQUEST_CODE = 1;
    private ImageView icon;
    LoadingSettings loadingDialog;
    private EditText txtDescription;
    private View layout;
    private ImageView img;

    private String selectedImagePath;
    private AppCompatButton btn;
    String base64Image;
    Usuario currentUser;

    Response responseCreatePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_publication);
        photo = findViewById(R.id.photo);
        layout = findViewById(R.id.layout);
        icon = findViewById(R.id.icon);
        img = findViewById(R.id.img);
        txtDescription = findViewById(R.id.desc);
        btn = findViewById(R.id.btnPostPublication);
        currentUser = new Usuario();

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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtDescription.getText().toString().trim().length() <= 0){
                    Toast.makeText(PostPublication.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    String txtDesc = txtDescription.getText().toString().trim();
                    loadingDialog = new LoadingSettings(PostPublication.this);
                    loadingDialog.show();
                    if (selectedImagePath != null && !selectedImagePath.isEmpty()) {
                        base64Image = encodeImage(selectedImagePath);
                        if (base64Image.length() > 30000){
                            Toast.makeText(PostPublication.this, "Foto muito grande", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        base64Image = currentUser.getUrl_foto();
                    }
                    currentUser.setEmail("ana.damasceno@gmail.com");
                    new CreatePostUser().execute(
                            base64Image,
                            "7",
                            txtDesc
                    );
                }
            }
        });


        addTextWatchers();

        if (ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE}, PERMISSAO_REQUEST);
            }
        }
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
    }

    private void addTextWatchers() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                verifyFields();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        };

        txtDescription.addTextChangedListener(textWatcher);
    }

    private void verifyFields() {
        String description = txtDescription.getText().toString().trim();

        if (!description.isEmpty()) {
            btn.setBackground(getDrawable(R.drawable.modules_button_blue));
        } else {
            btn.setBackground(getDrawable(R.drawable.disabled_button_background));
        }
    }

    private String encodeImage(String imagePath) {
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

            int maxWidth = 400;
            int maxHeight = 400;
            if (bitmap.getWidth() > maxWidth || bitmap.getHeight() > maxHeight) {
                float scale = Math.min(((float) maxWidth / bitmap.getWidth()), ((float) maxHeight / bitmap.getHeight()));
                Matrix matrix = new Matrix();
                matrix.postScale(scale, scale);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);

            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Foto muito grande", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private class CreatePostUser extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            if (params.length != 3) {
                return false;
            }

            String email = params[0];
            String urlPhoto= params[1];
            String desc = params[2];

            try {
                CreatePost mutations = new CreatePost();
                Response response = mutations.createPost(urlPhoto, email, desc);
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
                Toast.makeText(PostPublication.this, "Post criado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PostPublication.this, HomeScreen.class));
                finish();
            } else {
                startActivity(new Intent(PostPublication.this, SkeletonBlank.class));
                Toast.makeText(PostPublication.this, "Ocorreu um erro ao criar seu post", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
