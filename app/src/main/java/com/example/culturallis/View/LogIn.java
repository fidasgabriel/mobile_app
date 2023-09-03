package com.example.culturallis.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.culturallis.R;

public class LogIn extends AppCompatActivity {

    private EditText edtTxtPassword;
    private EditText edtTxtEmail;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtTxtPassword = findViewById(R.id.pswEdt);
        edtTxtEmail = findViewById(R.id.emailEdt);
        btnLogin = findViewById(R.id.btnLogin);
        setupPasswordVisibilityToggle(edtTxtPassword);

        addTextWatchers();
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

        System.out.println("s" + fieldsNotEmpty);

        if (fieldsNotEmpty) {
            btnLogin.setBackground(getDrawable(R.drawable.default_button_background));
        } else {
            btnLogin.setBackground(getDrawable(R.drawable.disabled_button_background));
        }
    }

    public void changeToLogon(View view){
        startActivity(new Intent(this, LogOn.class));
    }
}