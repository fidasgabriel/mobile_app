package com.example.culturallis.View.Configuration;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.culturallis.Controller.Mutations.LogonUser;
import com.example.culturallis.Controller.Mutations.UpdateUser;
import com.example.culturallis.Controller.Queries.GetUserInfo;
import com.example.culturallis.Controller.Queries.LoginUser;
import com.example.culturallis.Controller.SqLite.UserDAO;
import com.example.culturallis.Model.Entity.LoginUserEntity;
import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.Model.Usuario.Usuario;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Fragments.NotConnected;
import com.example.culturallis.View.Skeletons.SkeletonBlank;
import com.squareup.picasso.Picasso;
import okhttp3.Response;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PerfilEdit extends ModelAppScreens {
    private EditText edtTxtUserName;
    private EditText edtTxtBirthdayDay;
    private EditText editTextBio;
    private Button btnSave;
    private ImageView imgUser;
    LoadingSettings loadingDialog;
    private UserDAO userDAO = new UserDAO(this);
    Usuario currentUser;
    Response responseUpdate;
    private static final int GALLERY_REQUEST_CODE = 1;
    private String selectedImagePath;
    private TextView txtChangePhotoProfile;
    String base64Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_edit);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.left_arrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView titleTextView = findViewById(R.id.tbTitle);
        titleTextView.setText("Editar Perfil");

        edtTxtUserName = findViewById(R.id.chgUser);
        edtTxtBirthdayDay = findViewById(R.id.chgBirthday);
        editTextBio = findViewById(R.id.chgBio);
        btnSave = findViewById(R.id.btnSave);
        imgUser = findViewById(R.id.chgPerfil);
        txtChangePhotoProfile = findViewById(R.id.chgPerfilButton);

        Picasso.with(this).load("https://cdn.pixabay.com/photo/2012/04/26/19/43/profile-42914_1280.png").into(imgUser);

//        LoginUserEntity user = userDAO.getLogin();
//        try {
//            loadingDialog = new LoadingSettings(this);
//            loadingDialog.show();
//            currentUser = new Usuario();
//            currentUser.setEmail(user.getEmail());
//            new GetUserProfileTask().execute(currentUser.getEmail());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });

        isConnected();

        edtTxtBirthdayDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();

                if (text.length() == 8 && !text.contains("/")) {
                    text = text.substring(0, 2) + "/" + text.substring(2,4) + "/" + text.substring(4,8);
                    edtTxtBirthdayDay.setText(text);
                    edtTxtBirthdayDay.setSelection(8);
                }
                else if (text.length() == 9) {
                    text = text.replace("/", "");
                    edtTxtBirthdayDay.setText(text);
                    edtTxtBirthdayDay.setSelection(text.length());
                }
                else if (text.length() > 10) {
                    text = text.replace("/", "");
                    text = text.substring(0, 2) + "/" + text.substring(2,4) + "/" + text.substring(4,8);
                    edtTxtBirthdayDay.setText(text);
                    edtTxtBirthdayDay.setSelection(10);
                }
            }
        });

        txtChangePhotoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
            }
        });
        addTextWatchers();
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();

            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(imageUri, projection, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    selectedImagePath = cursor.getString(columnIndex);
                }
                cursor.close();
            }

            if (selectedImagePath != null) {
                Glide.with(this)
                        .load(selectedImagePath)
                        .into(imgUser);
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

        edtTxtUserName.addTextChangedListener(textWatcher);
        edtTxtBirthdayDay.addTextChangedListener(textWatcher);
        editTextBio.addTextChangedListener(textWatcher);
    }

    private void verifyFields() {
        String userName = edtTxtUserName.getText().toString().trim();
        String birthday = edtTxtBirthdayDay.getText().toString().trim();
        String bio = editTextBio.getText().toString().trim();


        if (!userName.isEmpty() || birthday.length() == 10 || !bio.isEmpty()) {
            btnSave.setBackground(getDrawable(R.drawable.default_button_background));
        } else {
            btnSave.setBackground(getDrawable(R.drawable.disabled_button_background));
        }
    }
    public void finishErrorScreen(View view){
        finish();
        startActivity(new Intent(this, PerfilEdit.class));
        isConnected();
    }

    public void isConnected(){
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (PerfilEdit.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {

        }
        else{
            NotConnected fragment = new NotConnected();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layout, fragment);
            transaction.commit();
        }

    }

    public void updateUser(View view) {
        if (edtTxtUserName.getText().toString().trim().length() > 0 && (editTextBio.getText().toString().trim().length() > 0 ||  edtTxtBirthdayDay.getText().toString().trim().length() > 0)) {
            loadingDialog = new LoadingSettings(this);
            loadingDialog.show();
            if (selectedImagePath != null && !selectedImagePath.isEmpty()) {
                base64Image = encodeImage(selectedImagePath);
            }else{
                base64Image = currentUser.getUrl_foto();
            }
            new UpdateUserInfoBasic().execute(
                    base64Image,
                    edtTxtUserName.getText().toString(),
                    edtTxtBirthdayDay.getText().toString(),
                    editTextBio.getText().toString());
        }else{
            Toast.makeText(PerfilEdit.this, "Preencha ao menos o nome de usuário", Toast.LENGTH_SHORT).show();
        }
    }

    private class GetUserProfileTask extends AsyncTask<String, Void, Usuario> {
        @Override
        protected Usuario doInBackground(String... params) {
            if (params.length == 1) {
                String email = params[0];
                try {
                    return new GetUserInfo().getInfoUser(email);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Usuario user) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }

            if (user != null) {
                if(user.getNome_usuario() != null){
                    edtTxtUserName.setText(user.getNome_usuario().toString());

                }else{
                    edtTxtUserName.setText("");
                }
                if(user.getUrl_foto()!= null && !user.getUrl_foto().isEmpty()){
                    byte[] decodedImage = Base64.decode(user.getUrl_foto(), Base64.DEFAULT);

                    Bitmap imageBitmap = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.length);

                    Glide.with(PerfilEdit.this)
                            .load(imageBitmap)
                            .into(imgUser);
                }else{
                    Picasso.with(PerfilEdit.this).load("https://cdn.pixabay.com/photo/2012/04/26/19/43/profile-42914_1280.png").into(imgUser);
                }
                if(user.getBio() != null){
                    editTextBio.setText(user.getBio().toString());
                }else{
                    editTextBio.setText("");
                }
                if (user.getData_nascimento() != null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(user.getData_nascimento());
                    calendar.add(Calendar.HOUR, 4);
                    Date dateWith3Hours = calendar.getTime();
                    edtTxtBirthdayDay.setText(simpleDateFormat.format(dateWith3Hours));
                } else {
                    edtTxtBirthdayDay.setText("");
                }


                currentUser = user;
            }else{
                startActivity(new Intent(PerfilEdit.this, SkeletonBlank.class));
                Toast.makeText(PerfilEdit.this, "Ocorreu um erro ao pegar seus dados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class UpdateUserInfoBasic extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            if (params.length != 4) {
                return false;
            }

            String urlPhoto = params[0];
            String username = params[1];
            String birthDateStr = params[2];
            String bio = params[3];

            try {
                Usuario usuarioAtt = new Usuario();
                usuarioAtt.setBio(bio);
                usuarioAtt.setNome_usuario(username);
                usuarioAtt.setUrl_foto(urlPhoto);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date birthDate = dateFormat.parse(birthDateStr);

                usuarioAtt.setData_nascimento(birthDate);


                responseUpdate = new UpdateUser().updateUsuario(currentUser.getEmail(), usuarioAtt);
                if (responseUpdate != null) {
                    if (responseUpdate.isSuccessful()) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
            if (success) {
                Toast.makeText(PerfilEdit.this, "Informações alteradas!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(PerfilEdit.this, "Ocorreu um erro ao alterar suas informações", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String encodeImage(String imagePath) {
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

            int maxWidth = 200;
            int maxHeight = 200;
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


}