package com.example.culturallis.Controller.SqLite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.example.culturallis.View.Entrance.LogIn;
import com.example.culturallis.View.Navbar.PerfilScreen;

import java.util.Objects;

public class SaveLoginDialog extends DialogFragment {

    private String[] listItems = new String[]{"post salvos", "cursos salvos"};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Deseja habilitar seu Login Automático?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((LogIn) Objects.requireNonNull(getActivity())).saveAplication(1);
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((LogIn) Objects.requireNonNull(getActivity())).saveAplication(2);
                    }
                });
        builder.setOnCancelListener(
                new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        ((LogIn) Objects.requireNonNull(getActivity())).enterToApplcation();
                    }
                }
        );
        return builder.create();
    }
}
