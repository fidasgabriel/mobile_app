package com.example.culturallis.View.Configuration;

<<<<<<< HEAD
import androidx.appcompat.app.AlertDialog;
=======
import android.os.AsyncTask;
import android.util.Log;
import android.widget.*;
>>>>>>> f69c8867cf338df29f320155186b24e2575761eb
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
<<<<<<< HEAD
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
=======
>>>>>>> f69c8867cf338df29f320155186b24e2575761eb

import com.example.culturallis.Controller.Mutations.UpdateUser;
import com.example.culturallis.Controller.Mutations.UpdateUserSensibility;
import com.example.culturallis.Controller.Queries.GetInfoUserSensibility;
import com.example.culturallis.Controller.Queries.GetUserInfo;
import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.Model.Usuario.Usuario;
import com.example.culturallis.R;
import com.example.culturallis.View.Entrance.LogIn;
import com.example.culturallis.View.Navbar.NavbarCulturallis;
import com.example.culturallis.View.Fragments.Loading;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Fragments.NotConnected;
import com.example.culturallis.View.Skeletons.SkeletonBlank;
import com.squareup.picasso.Picasso;
import okhttp3.Response;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Security extends ModelAppScreens {
    private EditText edtTxtEmail;
    private EditText edtTxtTel;
    private EditText edtTxtCPF;
    private EditText edtLastPassword;

    private EditText edtTxtPassword;
    private EditText edtTxtConfirmPassword;
    private Button btnSave;
    Usuario currentUser;
    Response responseUpdate;
    LoadingSettings loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.left_arrow);

        TextView titleTextView = findViewById(R.id.tbTitle);
        titleTextView.setText("Segurança");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edtTxtEmail = findViewById(R.id.chgEmail);
        edtTxtTel = findViewById(R.id.chgTel);
        edtTxtCPF = findViewById(R.id.chgCPF);
        edtLastPassword = findViewById(R.id.lstPsw);
        edtTxtPassword = findViewById(R.id.newPsw);
        edtTxtConfirmPassword = findViewById(R.id.confirmPsw);
        btnSave = findViewById(R.id.btnSave);
        addTextWatchers();

        isConnected();
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

        TextWatcher verificationPsw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                verifyPasswords();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        edtTxtEmail.addTextChangedListener(textWatcher);
        edtTxtTel.addTextChangedListener(textWatcher);
        edtTxtCPF.addTextChangedListener(textWatcher);
        edtLastPassword.addTextChangedListener(textWatcher);
        edtTxtPassword.addTextChangedListener(textWatcher);
        edtTxtConfirmPassword.addTextChangedListener(textWatcher);
        edtTxtConfirmPassword.addTextChangedListener(verificationPsw);
        edtTxtPassword.addTextChangedListener(verificationPsw);

        try {
            loadingDialog = new LoadingSettings(this);
            loadingDialog.show();
            currentUser = new Usuario();
            currentUser.setEmail("ana.damasceno@gmail.com");
            new Security.GetUserProfileTask().execute(currentUser.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void verifyPasswords() {
        String password = edtTxtPassword.getText().toString();
        String passwordConfirmation = edtTxtConfirmPassword.getText().toString();
        TextView confirmationMatch = findViewById(R.id.confirmMatch);

        if ((password.equals(passwordConfirmation) && password.trim().length() >= 8 && password.trim().length() <= 20) || (password.isEmpty() && passwordConfirmation.isEmpty())) {
            confirmationMatch.setText(null);
        } else if(!password.equals(passwordConfirmation)){
            confirmationMatch.setText("as senhas não batem");
        } else{
            confirmationMatch.setText("a senha deve ter de 8 até 20 caracteres");
        }
    }

    private void verifyFields() {
        String email = edtTxtEmail.getText().toString().trim();
        String tel = edtTxtTel.getText().toString().trim();
        String cpf = edtTxtCPF.getText().toString().trim();
        String lastPassword = edtLastPassword.getText().toString().trim();
        String password = edtTxtPassword.getText().toString().trim();
        String confirmPassword = edtTxtConfirmPassword.getText().toString().trim();

        boolean passwordsValid = (password.equals(confirmPassword) && password.trim().length() >= 8 && password.trim().length() <= 20);
        boolean fieldsNotEmpty = !lastPassword.isEmpty() && ((!password.isEmpty() && !confirmPassword.isEmpty()) || !email.isEmpty() || !tel.isEmpty() || !cpf.isEmpty());
        boolean passwordsMatch = password.equals(confirmPassword);

        if (!email.isEmpty() && (!tel.isEmpty() || tel.equals(currentUser.getTelefone()) || !cpf.isEmpty() || cpf.equals(currentUser.getCpf())) ) {
            if(!password.isEmpty() || !lastPassword.isEmpty() || !confirmPassword.isEmpty()){
                if(!lastPassword.isEmpty() && passwordsValid){
                    btnSave.setBackground(getDrawable(R.drawable.default_button_background));

                }else{
                    btnSave.setBackground(getDrawable(R.drawable.disabled_button_background));
                }
            }else{
                btnSave.setBackground(getDrawable(R.drawable.default_button_background));
            }
        } else {
            btnSave.setBackground(getDrawable(R.drawable.disabled_button_background));
        }
    }

<<<<<<< HEAD
    // public static class MainSettingsScreen extends AppCompatActivity {

    //     @Override
    //     protected void onCreate(Bundle savedInstanceState) {
    //         super.onCreate(savedInstanceState);
    //         setContentView(R.layout.activity_main_settings);

    //         Toolbar toolbar = findViewById(R.id.mytoolbar);
    //         setSupportActionBar(toolbar);
    //         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    //         toolbar.setNavigationIcon(R.drawable.left_arrow);

    //         TextView titleTextView = findViewById(R.id.tbTitle);
    //         titleTextView.setText("Configurações");

    //         toolbar.setNavigationOnClickListener(new View.OnClickListener() {
    //             @Override
    //             public void onClick(View view) {
    //                 finish();
    //             }
    //         });
    //     }

    //     public void exitButton(View view){
    //         final Context context = this;
    //         AlertDialog.Builder builder = new AlertDialog.Builder(this);
    //         View dialogView = getLayoutInflater().inflate(R.layout.modal_exit, null);
    //         builder.setView(dialogView);

    //         Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
    //         Button btnConfirm = dialogView.findViewById(R.id.btn_confirm);


    //         final AlertDialog dialog = builder.create();

    //         btnCancel.setOnClickListener(new View.OnClickListener() {
    //             @Override
    //             public void onClick(View v) {
    //                 dialog.dismiss();
    //             }
    //         });

    //         btnConfirm.setOnClickListener(new View.OnClickListener() {
    //             @Override
    //             public void onClick(View v) {
    //                 dialog.dismiss();
    //                 startActivity(new Intent(context, LogIn.class));
    //             }
    //         });

    //         dialog.show();
    //     }

    //     public void handleClickEditPerfil(View view){
    //         startActivity(new Intent(this, PerfilEdit.class));
    //     }

    //     public void handleClickSecurity(View view){
    //         startActivity(new Intent(this, Security.class));
    //     }

    //     public void handleClickServiceTerms(View view){
    //         startActivity(new Intent(this, TermsOfService.class));
    //     }
=======
    public void updateInfoUserSensibility(View view){
        if (edtTxtEmail.getText().toString().trim().length() > 0 && (edtTxtCPF.getText().toString().trim().length() > 0 ||  edtTxtTel.getText().toString().trim().length() > 0)) {
            loadingDialog = new LoadingSettings(this);
            loadingDialog.show();
            String lastPassword = edtLastPassword.getText().toString().trim();
            String password = edtTxtPassword.getText().toString().trim();
            String confirmPassword = edtTxtConfirmPassword.getText().toString().trim();
            boolean passwordsValid = (password.equals(confirmPassword) && password.trim().length() >= 8 && password.trim().length() <= 20);
            if(!lastPassword.isEmpty() && passwordsValid){
                new Security.UpdateUserInfoSensibility().execute(
                        edtTxtEmail.getText().toString(),
                        edtTxtTel.getText().toString(),
                        edtTxtCPF.getText().toString().replace("-", "").replace("_", "").replace(".", ""),
                        confirmPassword);
                if(edtTxtCPF.getText().toString().replace("-", "").replace("_", "").replace(".", "").toString().length() > 11){
                    Toast.makeText(Security.this, "CPF/CNPJ ultrapassou o limite de caracteres", Toast.LENGTH_SHORT).show();
                }
            }else{
                new Security.UpdateUserInfoSensibility().execute(
                        edtTxtEmail.getText().toString(),
                        edtTxtTel.getText().toString(),
                        edtTxtCPF.getText().toString().replace("-", "").replace("_", "").replace(".", ""),
                        currentUser.getSenha());
                Toast.makeText(Security.this, "Não é possível aterar sua senha pois não atendeu aos requisitos.", Toast.LENGTH_SHORT).show();
                if(edtTxtCPF.getText().toString().replace("-", "").replace("_", "").replace(".", "").toString().length() > 11){
                    Toast.makeText(Security.this, "CPF/CNPJ ultrapassou o limite de caracteres", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(Security.this, "Preencha ao menos o e-mail", Toast.LENGTH_SHORT).show();
        }
    }

>>>>>>> f69c8867cf338df29f320155186b24e2575761eb
    public void finishErrorScreen(View view){
        finish();
        startActivity(new Intent(this, Security.class));
        isConnected();
    }

    public void isConnected(){
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Security.CONNECTIVITY_SERVICE);
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

    private class GetUserProfileTask extends AsyncTask<String, Void, Usuario> {
        @Override
        protected Usuario doInBackground(String... params) {
            if (params.length == 1) {
                String email = params[0];
                try {
                    return new GetInfoUserSensibility().getInfoUser(email);
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
                currentUser = user;
                if (currentUser.getEmail() != null) {
                    edtTxtEmail.setText(currentUser.getEmail());
                } else {
                    edtTxtEmail.setText("");
                }

                if (currentUser.getTelefone() != null && !currentUser.getTelefone().equals("null")) {
                    edtTxtTel.setText(currentUser.getTelefone());
                } else {
                    edtTxtTel.setText("");
                }

                if (currentUser.getCpf() != null && !currentUser.getCpf().equals("null")) {
                    edtTxtCPF.setText(currentUser.getCpf());
                } else {
                    edtTxtCPF.setText("");
                }


            }else{
                startActivity(new Intent(Security.this, SkeletonBlank.class));
                Toast.makeText(Security.this, "Ocorreu um erro ao pegar seus dados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class UpdateUserInfoSensibility extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            if (params.length != 4) {
                return false;
            }

            String email = params[0];
            String tel = params[1];
            String cpf = params[2];
            String password = params[3];

            try {
                Usuario userUpdated =  new Usuario();
                userUpdated.setEmail(email);
                userUpdated.setTelefone(tel);
                userUpdated.setCpf(cpf);

                if(currentUser.getSenha().equals(edtLastPassword.getText().toString())){
                    userUpdated.setSenha(password);
                }else{
                    userUpdated.setSenha(currentUser.getSenha());
                }

                responseUpdate = new UpdateUserSensibility().updateUserSensibility(currentUser.getEmail(), userUpdated);
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
                Toast.makeText(Security.this, "Informações alteradas!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(Security.this, "Ocorreu um erro ao alterar suas informações", Toast.LENGTH_SHORT).show();
            }
        }
    }
}