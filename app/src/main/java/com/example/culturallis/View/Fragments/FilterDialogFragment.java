
package com.example.culturallis.View.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.culturallis.Model.Entity.CourseCard;
import com.example.culturallis.R;
import com.example.culturallis.View.Navbar.PerfilScreen;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FilterDialogFragment extends DialogFragment {
    private Integer type;
    private String[] listItems;

    public FilterDialogFragment(Integer type){
        this.type = type;
        if (type == 1 || type == 2) {
            listItems = new String[]{"mais antigo", "mais recente"};
        }else if(type == 3){
            listItems = new String[]{"post salvos", "cursos salvos"};
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Filtrar Por:")
                .setItems(listItems, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((PerfilScreen) getActivity()).addFilterRecycleView(type, which);
                    }
                });
        return builder.create();
    }
}
