package com.example.culturallis.View.Configuration;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.R;
import android.net.Uri;
import com.example.culturallis.View.Entrance.LogIn;
import com.example.culturallis.View.Navbar.NavbarCulturallis;

public class MainSettingsScreen extends ModelAppScreens {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_settings);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.left_arrow);

        TextView titleTextView = findViewById(R.id.tbTitle);
        titleTextView.setText("Configurações");





        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainSettingsScreen.this, NavbarCulturallis.class));
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
            });



    }

    public void exitButton(View view){
        final Context context = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.modal_exit, null);
        builder.setView(dialogView);

        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        Button btnConfirm = dialogView.findViewById(R.id.btn_confirm);

        final AlertDialog dialog = builder.create();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(context, LogIn.class));
            }
        });

        dialog.show();
    }

    public void changeToTermsOfService(View view){
        startActivity(new Intent(this, TermsOfService.class));
    }
    public void changeToSecurity(View view){
        startActivity(new Intent(this, Security.class));
    }
    public void changeToPerfilEdit(View view){
        startActivity(new Intent(this, PerfilEdit.class));
    }
    public void changeToHelpCenter(View view){
        Toast.makeText(this, "Central de ajuda aqui!!!", Toast.LENGTH_SHORT).show();
    }
    public void changeToLanding(View view){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://culturallis.onrender.com/")));
    }
}