package com.example.ytgv8b.firsttry;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Nonnull;

import Library.Signals.INotification;

/**
 * Created by User on 15.01.2018.
 */

public class NotificationAdapter extends ArrayAdapter<INotification> {

    public NotificationAdapter(@NonNull Context context, @NonNull List<INotification> objects) {
        super(context, android.R.layout.simple_list_item_2, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        INotification notification = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_2, null);

        SimpleDateFormat format = new SimpleDateFormat("dd:MM:yy HH:mm");
        String time =format.format(notification.getSignalTime());

        TextView text1 = convertView.findViewById(android.R.id.text1);
        text1.setTextSize(36);
        text1.setTextColor(getContext()
                .getResources()
                .getColor(android.R.color.darker_gray)
        );
        TextView text2 = convertView.findViewById(android.R.id.text2);
        text2.setTextSize(14);
        text1.setTextColor(getContext()
                .getResources()
                .getColor(R.color.progress_gray_dark)
        );

        text1.setText(notification.getName());
        text2.setText(time);

        return convertView;
    }
}
