package com.example.culturallis.Controller;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.culturallis.R;

import java.util.List;

public class GlobalUtilization {

    public GlobalUtilization() {
    }
    public static void coloringTexts(TextView textView, String text, List<Integer> colorRes){
        SpannableString spannableString = new SpannableString(text);
        for(int i = 0; i < text.length(); i++){
            int color = textView.getResources().getColor(colorRes.get(i));
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
            spannableString.setSpan(colorSpan, i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(spannableString);
    }
}
