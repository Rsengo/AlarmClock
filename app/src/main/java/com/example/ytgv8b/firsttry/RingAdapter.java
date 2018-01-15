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

        TextView timeView = convertView.findViewById(R.id.textbox_name);
        timeView.setText(time);

        Switch switcher = convertView.findViewById(R.id.switch2);
        switcher.setChecked(ring.isOnState());

        switcher.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked)
                    ring.turnOn(getContext());
                else
                    ring.turnOff(getContext());
        });

        TextView informationView = convertView.findViewById(R.id.textbox_description);

        String days = "";

        if (ring.getRepeatDays() == null) {
            days = "Без повтора";
        } else {
            for (int i = 0; i < 7; i++)
                if (ring.getRepeatDays()[i] == 1)
                    days += daysOfWeek.get(i) +", ";
            days = days.substring(0, days.length()-2);
        }

        String information = ring.getDescription();

        if (information.length() > 20)
            information = information.substring(0, 17) +"...";

        String description = information +"\r\n" + days;

        informationView.setText(description);

        return convertView;
    }
}
