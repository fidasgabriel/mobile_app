package com.example.culturallis.View.Configuration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.NotConnected;
import com.example.culturallis.View.Navbar.NavbarCulturallis;


public class PerfilEdit extends ModelAppScreens {
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
}