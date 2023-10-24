package com.example.culturallis.View.Entrance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.culturallis.Controller.Mutations.LogonUser;
import com.example.culturallis.Controller.Queries.LoginUser;
import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.PerfilEdit;
<<<<<<< HEAD
import com.example.culturallis.View.Navbar.CourseScreen;
import com.example.culturallis.View.Navbar.HomeScreen;
=======
import com.example.culturallis.View.Fragments.LoadingSettings;
>>>>>>> f69c8867cf338df29f320155186b24e2575761eb
import com.example.culturallis.View.Navbar.NavbarCulturallis;
import com.example.culturallis.View.Skeletons.SkeletonBlank;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LogIn extends AppCompatActivity {

    private EditText edtTxtPassword;
    private EditText edtTxtEmail;
    private Button btnLogin;
    LoadingSettings loadingDialog;

    Response responseLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String linkToLogin = "CADASTRAR-SE";
        edtTxtPassword = findViewById(R.id.pswEdt);
        SpannableString underline = new SpannableString(linkToLogin);
        edtTxtEmail = findViewById(R.id.emailEdt);
        btnLogin = findViewById(R.id.btnLogin);
        setupPasswordVisibilityToggle(edtTxtPassword);
        UnderlineSpan underlineSpan = new UnderlineSpan();
        underline.setSpan(underlineSpan, 0, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        addTextWatchers();

        TextView linkLogon = findViewById(R.id.linkLogon);

        linkLogon.setText(underline);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupPasswordVisibilityToggle(final EditText editText) {
        editText.setTransformationMethod(new PasswordTransformationMethod()); // Oculta o texto por padrão

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int drawableRight = 2;
                    int iconWidth = editText.getCompoundDrawables()[drawableRight].getBounds().width();

                    if (event.getRawX() >= (editText.getRight() - iconWidth)) {
                        togglePasswordVisibility(editText);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void togglePasswordVisibility(EditText editText) {
        boolean passwordVisible = editText.getTransformationMethod() == null;
        int drawableId = !passwordVisible ? R.drawable.baseline_visibility_off_24 : R.drawable.eye_open;
        editText.setTransformationMethod(passwordVisible ? new PasswordTransformationMethod() : null);
        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawableId, 0);
        editText.setSelection(editText.getText().length());
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

        edtTxtPassword.addTextChangedListener(textWatcher);
        edtTxtEmail.addTextChangedListener(textWatcher);
    }

    private void verifyFields() {
        String password = edtTxtPassword.getText().toString().trim();
        String email = edtTxtEmail.getText().toString().trim();

        boolean fieldsNotEmpty = !password.isEmpty() && !email.isEmpty();

        if (fieldsNotEmpty) {
            btnLogin.setBackground(getDrawable(R.drawable.default_button_background));
        } else {
            btnLogin.setBackground(getDrawable(R.drawable.disabled_button_background));
        }
    }

        public void login(View view) {
            String password = edtTxtPassword.getText().toString().trim();
            String email = edtTxtEmail.getText().toString().trim();
            if (password.length() > 0 && email.length() > 0) {
                loadingDialog = new LoadingSettings(this);
                loadingDialog.show();
                new LoginUserGet().execute(email, password);
            }else{
                Toast.makeText(LogIn.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }

        }

        private class LoginUserGet extends AsyncTask<String, Void, Boolean> {
            @Override
            protected Boolean doInBackground(String... params) {
                if (params.length != 2) {
                    return false;
                }

                String email = params[0];
                String password = params[1];

                try {
                    LoginUser queries = new LoginUser();
                    responseLogin = queries.loginUser(email, password);
                    return responseLogin.isSuccessful();
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
                        Toast.makeText(LogIn.this, "Você está logado!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LogIn.this, NavbarCulturallis.class));
                        finish();
                } else {
                    edtTxtPassword.setText("");
                    Toast.makeText(LogIn.this, "Não foi possível logar sua conta, tente novamente.", Toast.LENGTH_SHORT).show();
                }
            }
        }

    public void changeToLogon(View view){
        startActivity(new Intent(this, LogOn.class));
    }
}