package com.example.ytgv8b.firsttry;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import Library.Signals.IRing;

/**
 * Created by ytgv8b on 15.01.2018.
 */

public class VibrationAndAutodeleteAdapter extends ArrayAdapter<String> {


    public VibrationAndAutodeleteAdapter(@NonNull Context context, @NonNull String[] objects) {
        super(context, android.R.layout.simple_list_item_multiple_choice, objects);
        boxes[0]=false;
        boxes[1]=false;
    }

    boolean[] boxes = new boolean[2];

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_multiple_choice, null);

        CheckedTextView view = convertView.
                findViewById(android.R.id.text1);

        view.setText(getItem(position));
        view.setChecked(false);

        view.setOnClickListener(view1 -> {
            boxes[position] = view.isChecked();
        });

        return convertView;
    }

    public boolean getBox(int i) {
        return boxes[i];
    }
}
