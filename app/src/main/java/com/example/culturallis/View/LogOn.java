    package com.example.culturallis.View;

    import android.annotation.SuppressLint;
    import android.content.Intent;
    import android.content.res.ColorStateList;
    import android.graphics.Color;
    import android.graphics.Typeface;
    import android.text.Editable;
    import android.text.SpannableString;
    import android.text.Spanned;
    import android.text.TextWatcher;
    import android.text.method.PasswordTransformationMethod;
    import android.text.style.ForegroundColorSpan;
    import android.text.style.StyleSpan;
    import android.text.style.UnderlineSpan;
    import android.view.MotionEvent;
    import android.view.View;
    import android.widget.*;
    import androidx.appcompat.app.AppCompatActivity;
    import android.os.Bundle;

    import com.example.culturallis.R;

    public class LogOn extends AppCompatActivity {

        private EditText edtTxtPassword;
        private EditText edtTxtConfirmPassword;
        private EditText edtTxtEmail;
        private EditText edtTxtUserName;

        private CheckBox checkBox;

        private Button btnLogon;

        @SuppressLint("ClickableViewAccessibility")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_log_on);

            String text = "Ao assinalar a caixa você concorda com os Termos de uso";
            String linkToLogin = "ENTRE";
            SpannableString ss = new SpannableString(text);
            SpannableString underline = new SpannableString(linkToLogin);
            StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
            UnderlineSpan underlineSpan = new UnderlineSpan();
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.rgb(14, 14, 14));
            ss.setSpan(boldSpan, 42, 55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(colorSpan, 42, 55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            underline.setSpan(underlineSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            TextView txtTermsOfUse = findViewById(R.id.textTerms);
            TextView linkLogin = findViewById(R.id.linkLogin);
            btnLogon = findViewById(R.id.btnLogon);
            txtTermsOfUse.setText(ss);
            linkLogin.setText(underline);
            btnLogon.setText("Cadastrar-se");


            edtTxtPassword = findViewById(R.id.pswEdt);
            edtTxtConfirmPassword = findViewById(R.id.confirmPswEdt);
            edtTxtUserName = findViewById(R.id.userNameEdt);
            edtTxtEmail = findViewById(R.id.emailEdt);
            checkBox = findViewById(R.id.termsOfUtilization);

            setupPasswordVisibilityToggle(edtTxtPassword);
            setupPasswordVisibilityToggle(edtTxtConfirmPassword);

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

            edtTxtPassword.addTextChangedListener(textWatcher);
            edtTxtConfirmPassword.addTextChangedListener(textWatcher);
            edtTxtEmail.addTextChangedListener(textWatcher);
            edtTxtUserName.addTextChangedListener(textWatcher);
            edtTxtConfirmPassword.addTextChangedListener(verificationPsw);
            edtTxtPassword.addTextChangedListener(verificationPsw);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    verifyFields();
                }
            });
        }

        private void verifyFields() {
            String password = edtTxtPassword.getText().toString().trim();
            String confirmPassword = edtTxtConfirmPassword.getText().toString().trim();
            String email = edtTxtEmail.getText().toString().trim();
            String userName = edtTxtUserName.getText().toString().trim();

            boolean passwordsValid = password.length() >= 8 && password.length() <= 20 && password.equals(confirmPassword);
            boolean fieldsNotEmpty = !password.isEmpty() && !confirmPassword.isEmpty() && !email.isEmpty() && !userName.isEmpty();
            boolean passwordsMatch = password.equals(confirmPassword);

            if (passwordsValid && fieldsNotEmpty && passwordsMatch && checkBox.isChecked()) {
                btnLogon.setBackground(getDrawable(R.drawable.default_button_background));
            } else {
                btnLogon.setBackground(getDrawable(R.drawable.disabled_button_background));
            }
        }

        private void verifyPasswords() {
            String password = edtTxtPassword.getText().toString();
            String passwordConfirmation = edtTxtConfirmPassword.getText().toString();
            TextView confirmationMatch = findViewById(R.id.confirmMatch);

            if (password.equals(passwordConfirmation) && password.trim().length() >= 8 && password.trim().length() <= 20 ) {
                confirmationMatch.setText(null);
            } else if(!password.equals(passwordConfirmation)){
                confirmationMatch.setText("as senhas não batem");
            } else{
                confirmationMatch.setText("a senha deve ter de 8 até 20 caracteres");
            }
        }

        public void changeToLogin(View view){
            startActivity(new Intent(this, LogIn.class));
            finish();
        }
    }