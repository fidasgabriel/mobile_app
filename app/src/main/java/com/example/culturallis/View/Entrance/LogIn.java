package com.example.culturallis.View.Entrance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.ServerError;
public class LogIn extends AppCompatActivity {

    private EditText edtTxtPassword;
    private EditText edtTxtEmail;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.id123, new ServerError());

        ft.commit();

//        String linkToLogin = "CADASTRAR-SE";
//        edtTxtPassword = findViewById(R.id.pswEdt);
//        SpannableString underline = new SpannableString(linkToLogin);
//        edtTxtEmail = findViewById(R.id.emailEdt);
//        btnLogin = findViewById(R.id.btnLogin);
//        setupPasswordVisibilityToggle(edtTxtPassword);
//        UnderlineSpan underlineSpan = new UnderlineSpan();
//        underline.setSpan(underlineSpan, 0, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        addTextWatchers();
//
//        TextView linkLogon = findViewById(R.id.linkLogon);
//
//        linkLogon.setText(underline);
//    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    private void setupPasswordVisibilityToggle(final EditText editText) {
//        editText.setTransformationMethod(new PasswordTransformationMethod()); // Oculta o texto por padrão
//
//        editText.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    int drawableRight = 2;
//                    int iconWidth = editText.getCompoundDrawables()[drawableRight].getBounds().width();
//
//                    if (event.getRawX() >= (editText.getRight() - iconWidth)) {
//                        togglePasswordVisibility(editText);
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });
//    }
//
//    private void togglePasswordVisibility(EditText editText) {
//        boolean passwordVisible = editText.getTransformationMethod() == null;
//        int drawableId = !passwordVisible ? R.drawable.baseline_visibility_off_24 : R.drawable.eye_open;
//        editText.setTransformationMethod(passwordVisible ? new PasswordTransformationMethod() : null);
//        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawableId, 0);
//        editText.setSelection(editText.getText().length());
//    }
//
//    private void addTextWatchers() {
//        TextWatcher textWatcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                verifyFields();
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {}
//        };
//
//        edtTxtPassword.addTextChangedListener(textWatcher);
//        edtTxtEmail.addTextChangedListener(textWatcher);
//    }
//
//    private void verifyFields() {
//        String password = edtTxtPassword.getText().toString().trim();
//        String email = edtTxtEmail.getText().toString().trim();
//
//        boolean fieldsNotEmpty = !password.isEmpty() && !email.isEmpty();
//
//        if (fieldsNotEmpty) {
//            btnLogin.setBackground(getDrawable(R.drawable.default_button_background));
//        } else {
//            btnLogin.setBackground(getDrawable(R.drawable.disabled_button_background));
//        }
//    }
//
//    public void changeToLogon(View view){
//        startActivity(new Intent(this, TermsOfService.class));
//    }
}}