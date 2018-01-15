package com.example.ytgv8b.firsttry;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import Library.Signals.IRing;
import Library.Signals.Ring;

/**
 * Created by User on 15.01.2018.
 */

public class RingAdapter extends ArrayAdapter<IRing> {

    private int resource;
    private HashMap<Integer, String> daysOfWeek = new HashMap<>();

    public RingAdapter(@NonNull Context context, @NonNull List<IRing> objects) {
        super(context, R.layout.list_item, objects);
        this.resource = R.layout.list_item;
        daysOfWeek.put(0, "пн");
        daysOfWeek.put(1, "вт");
        daysOfWeek.put(2, "ср");
        daysOfWeek.put(3, "чт");
        daysOfWeek.put(4, "пт");
        daysOfWeek.put(5, "сб");
        daysOfWeek.put(6, "вс");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        IRing ring = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(resource, null);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time =format.format(ring.getSignalTime());

        TextView textView = convertView.findViewById(R.id.textbox_name);
        textView.setText(time);

        Switch switcher = convertView.findViewById(R.id.switch2);
        switcher.setChecked(ring.isOnState());

        switcher.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked)
                    ring.turnOn(getContext());
                else
                    ring.turnOff(getContext());
        });

        return convertView;
    }
}
