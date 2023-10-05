package com.example.culturallis.Controller;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.List;

public class GlobalUtilization {

    public GlobalUtilization(){}

    public static void coloringTexts(TextView textView, String text, List<Integer> colorRes){
        SpannableString spannableString = new SpannableString(text);
        if(colorRes.size() == 1){
            for(int i = 0; i < text.length(); i++){
                int color = textView.getResources().getColor(colorRes.get(i));
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
                spannableString.setSpan(colorSpan, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            textView.setText(spannableString);
        }else {
            for (int i = 0; i < text.length(); i++) {
                int color = textView.getResources().getColor(colorRes.get(i));
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
                spannableString.setSpan(colorSpan, i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            textView.setText(spannableString);
        }
    }

    public static void coloringTextsOptimized(TextView textView, String text, List<Integer> colorRes) {
        SpannableString spannableString = new SpannableString(text);

        int totalColors = colorRes.size();
        if (totalColors > 0) {
            int textLength = text.length();
            int colorSpanSize = textLength / totalColors;

            for (int i = 0; i < totalColors; i++) {
                int start = i * colorSpanSize;
                int end = (i + 1) * colorSpanSize;
                if (i == totalColors - 1) {
                    end = textLength; // Ensure the last span covers the remaining text
                }

                int color = textView.getResources().getColor(colorRes.get(i));
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
                spannableString.setSpan(colorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            textView.setText(spannableString);
        }
    }

}
