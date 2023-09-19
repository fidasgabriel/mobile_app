package com.example.culturallis.View.Configuration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.culturallis.R;


public class PerfilEdit extends AppCompatActivity {
    private EditText edtTxtUserName;
    private EditText edtTxtBirthdayDay;
    private EditText editTextBio;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_edit);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.left_arrow);

        TextView titleTextView = findViewById(R.id.tbTitle);
        titleTextView.setText("Editar Perfil");

        edtTxtUserName = findViewById(R.id.chgUser);
        edtTxtBirthdayDay = findViewById(R.id.chgBirthday);
        editTextBio = findViewById(R.id.chgBio);
        btnSave = findViewById(R.id.btnSave);

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
}