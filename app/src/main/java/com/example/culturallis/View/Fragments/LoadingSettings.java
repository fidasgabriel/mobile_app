package com.example.culturallis.View.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.annotation.LayoutRes;
import com.example.culturallis.R;

public class LoadingSettings extends Dialog {

    public LoadingSettings(Context context) {
        super(context);

        // Set the custom layout for the dialog

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_loading);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        ImageView rotatingImageView = findViewById(R.id.rotatingImageView);
        Animation rotation = AnimationUtils.loadAnimation(context, R.anim.rotate);
        rotatingImageView.startAnimation(rotation);
    }
}
