package com.example.ytgv8b.firsttry.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.example.ytgv8b.firsttry.MainActivity;
import com.example.ytgv8b.firsttry.R;

import Library.DataHelpers.DataBaseHelper;
import Library.Signals.INotification;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        DataBaseHelper.init(context);

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();

        int id = intent.getIntExtra("id", -1);

        INotification notification = dataBaseHelper.getNotification(id);

        // TODO: 12.01.2018 MainMenu
        Intent notificationActivity = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notification.getId(),
                notificationActivity, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Resources res = context.getResources();

        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.ikonka_notif);

        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ikonka)
                .setContentTitle(notification.getName())
                .setContentText(notification.getDescription())
                .setLargeIcon(bitmap)
                .setTicker(notification.getName())
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setPriority(notification.getPriority());

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notification.getId(), builder.build());

        notification.recountSignalTime(context);
        dataBaseHelper.saveRecursive(notification);
    }
}
