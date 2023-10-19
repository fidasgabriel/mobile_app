package com.example.culturallis.View.Configuration;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.culturallis.Controller.Mutations.LogonUser;
import com.example.culturallis.Controller.Mutations.UpdateUser;
import com.example.culturallis.Controller.Queries.GetUserInfo;
import com.example.culturallis.Controller.Queries.LoginUser;
import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.Model.Usuario.Usuario;
import com.example.culturallis.R;
import com.example.culturallis.View.Entrance.LogIn;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Fragments.NotConnected;
import com.example.culturallis.View.Navbar.NavbarCulturallis;
import com.example.culturallis.View.Skeletons.SkeletonBlank;
import com.squareup.picasso.Picasso;
import okhttp3.Response;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PerfilEdit extends ModelAppScreens {
    private EditText edtTxtUserName;
    private EditText edtTxtBirthdayDay;
    private EditText editTextBio;
    private Button btnSave;
    private ImageView imgUser;
    LoadingSettings loadingDialog;

    Usuario currentUser;

    Response responseUpdate;

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
                startActivity(new Intent(PerfilEdit.this, MainSettingsScreen.class));
            }
        });

        TextView titleTextView = findViewById(R.id.tbTitle);
        titleTextView.setText("Editar Perfil");

        edtTxtUserName = findViewById(R.id.chgUser);
        edtTxtBirthdayDay = findViewById(R.id.chgBirthday);
        editTextBio = findViewById(R.id.chgBio);
        btnSave = findViewById(R.id.btnSave);
        imgUser = findViewById(R.id.chgPerfil);

        Picasso.with(this).load("https://cdn.pixabay.com/photo/2012/04/26/19/43/profile-42914_1280.png").into(imgUser);

        try {
            loadingDialog = new LoadingSettings(this);
            loadingDialog.show();
            currentUser = new Usuario();
            currentUser.setEmail("ana.damasceno@gmail.com");
            new GetUserProfileTask().execute(currentUser.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        addTextWatchers();
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
            new UpdateUserInfoBasic().execute(
                    imgUser.getDrawable().toString(),
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
                currentUser.setUrl_foto(user.getUrl_foto());
                if(user.getNome_usuario() != null){
                    edtTxtUserName.setText(user.getNome_usuario().toString());

                }else{
                    edtTxtUserName.setText("");
                }
                if(user.getUrl_foto().toString() != null){
                    Picasso.with(PerfilEdit.this).load(user.getUrl_foto()).into(imgUser);
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
                currentUser.setBio(bio);
                currentUser.setNome_usuario(username);
                currentUser.setUrl_foto(currentUser.getUrl_foto());

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date birthDate = dateFormat.parse(birthDateStr);

                currentUser.setData_nascimento(birthDate);


                responseUpdate = new UpdateUser().updateUsuario(currentUser.getEmail(), currentUser);
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



}