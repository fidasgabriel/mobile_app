    package com.example.culturallis.View.Entrance;

    import android.annotation.SuppressLint;
    import android.content.Intent;
    import android.graphics.Color;
    import android.graphics.Typeface;
    import android.os.AsyncTask;
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

    import com.example.culturallis.Controller.Mutations.LogonUser;
    import com.example.culturallis.R;
    import com.example.culturallis.View.Configuration.TermsOfService;
    import com.example.culturallis.View.Fragments.LoadingSettings;
    import com.example.culturallis.View.Skeletons.SkeletonBlank;
    import okhttp3.Response;

    public class LogOn extends AppCompatActivity {

        private EditText edtTxtPassword;
        private EditText edtTxtConfirmPassword;
        private EditText edtTxtEmail;
        private EditText edtTxtUserName;

        private CheckBox checkBox;

        private Button btnLogon;

        LoadingSettings loadingDialog;


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
            ss.setSpan(underlineSpan, 42, 55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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
            if(edtTxtPassword.getText().toString().trim().length() >0) {
                boolean passwordVisible = editText.getTransformationMethod() == null;
                int drawableId = !passwordVisible ? R.drawable.baseline_visibility_off_24 : R.drawable.eye_open;
                editText.setTransformationMethod(passwordVisible ? new PasswordTransformationMethod() : null);
                editText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawableId, 0);
                editText.setSelection(editText.getText().length());
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

        public void logon(View view) {
            String password = edtTxtPassword.getText().toString().trim();
            String confirmPassword = edtTxtConfirmPassword.getText().toString().trim();
            String email = edtTxtEmail.getText().toString().trim();
            String userName = edtTxtUserName.getText().toString().trim();

            boolean passwordsValid = password.length() >= 8 && password.length() <= 20 && password.equals(confirmPassword);
            boolean fieldsNotEmpty = !password.isEmpty() && !confirmPassword.isEmpty() && !email.isEmpty() && !userName.isEmpty();
            boolean passwordsMatch = password.equals(confirmPassword);

            if (passwordsValid && fieldsNotEmpty && passwordsMatch && checkBox.isChecked()) {
                loadingDialog = new LoadingSettings(this);
                loadingDialog.show();
                new AddUsuarioPost().execute(userName, email, password);
            } else {
                Toast.makeText(this, "Preencha os campos corretamente", Toast.LENGTH_SHORT).show();
            }
        }

        private class AddUsuarioPost extends AsyncTask<String, Void, Boolean> {
            @Override
            protected Boolean doInBackground(String... params) {
                if (params.length != 3) {
                    return false;
                }

                String userName = params[0];
                String email = params[1];
                String password = params[2];

                try {
                    LogonUser mutations = new LogonUser();
                    Response response = mutations.addUsuario(userName, email, password);
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
                    Toast.makeText(LogOn.this, "Usuário inserido", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LogOn.this, LogIn.class));
                    finish();
                } else {
                    startActivity(new Intent(LogOn.this, SkeletonBlank.class));
                    Toast.makeText(LogOn.this, "Ocorreu um erro ao registrar o usuário", Toast.LENGTH_SHORT).show();
                }
            }
        }

        public void changeToTerms(View view){
            startActivity(new Intent(this, TermsOfService.class));
        }
    }