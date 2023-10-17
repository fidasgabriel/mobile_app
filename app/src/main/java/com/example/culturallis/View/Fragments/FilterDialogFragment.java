package com.example.culturallis.View.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.example.culturallis.R;

import java.util.ArrayList;

public class FilterDialogFragment extends DialogFragment {
    private Integer type;
    private String[] listItems;

    public FilterDialogFragment(Integer type){
        this.type = type;
        switch (type){
            case 1:
                listItems = new String[]{"mais antigo", "mais recente"};
                break;
            case 3:
                listItems = new String[]{"post salvos", "cursos salvos", "posts e cursos salvos"};
                break;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Filtrar Por:")
                .setItems(listItems, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (type){
                            case 1: //Para cursos assitidos
                                if (which == 0){

                                }else if(which == 1){

                                }else{

                                }
                                break;

                            case 2: // para cursos criados

                                break;

                            case 3: //para conteúdo salvo

                                break;
                        }
                    }
                });
        return builder.create();
    }
}
