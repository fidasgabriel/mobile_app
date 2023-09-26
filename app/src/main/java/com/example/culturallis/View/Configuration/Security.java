package com.example.culturallis.View.Configuration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.culturallis.R;
import com.example.culturallis.View.Entrance.LogIn;
import com.example.culturallis.View.Fragments.Loading;

public class Security extends AppCompatActivity {
    private EditText edtTxtEmail;
    private EditText edtTxtTel;
    private EditText edtTxtCPF;
    private EditText edtLastPassword;

    private EditText edtTxtPassword;
    private EditText edtTxtConfirmPassword;
    private Button btnSave;


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
                startActivity(new Intent(Security.this, MainSettingsScreen.class));
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

        boolean passwordsValid = (password.equals(confirmPassword) && password.trim().length() >= 8 && password.trim().length() <= 20) || (password.isEmpty() && confirmPassword.isEmpty());
        boolean fieldsNotEmpty = !lastPassword.isEmpty() && ((!password.isEmpty() && !confirmPassword.isEmpty()) || !email.isEmpty() || !tel.isEmpty() || !cpf.isEmpty());
        boolean passwordsMatch = password.equals(confirmPassword);

        if (passwordsValid && fieldsNotEmpty && passwordsMatch) {
            btnSave.setBackground(getDrawable(R.drawable.default_button_background));
        } else {
            btnSave.setBackground(getDrawable(R.drawable.disabled_button_background));
        }
    }
}