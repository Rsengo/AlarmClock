package com.example.ytgv8b.firsttry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.PreferenceHelper;
import Library.Messages.SMS;
import Library.PuzzlesThings.PuzzleFactory;
import Library.Settings.UserInterface;
import Library.Signals.IRing;
import Library.Signals.Notification;
import Library.Signals.Ring;
import Library.Signals.SignalBuilders.NotificationBuilder;
import Library.Signals.SignalBuilders.RingBuilder;
import Library.Signals.SignalBuilders.SignalFactory;
import Library.User.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceHelper.init(this);
        DataBaseHelper.init(this);

        PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();

        TextView textView = (TextView)findViewById(R.id.textView);

        Button button1 = (Button)findViewById(R.id.button);

        button1.setOnClickListener(view -> {
            User user = User.getInstance();
            user.setName("user");
            user.setEmail("email");
            user.setMoneyQuantity(100);
            UserInterface settings = (UserInterface)user.getUserInterface();
            settings.setLanguage(UserInterface.LANGUAGE_RUSSIAN);
            settings.setColorSchemeId(0);
            settings.setFontSize(UserInterface.FONT_SIZE_DEFAULT);
            preferenceHelper.writePreference();
        });

        Button button2 = (Button)findViewById(R.id.button2);

        button2.setOnClickListener(view -> {
            User user = User.getInstance();

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MILLISECOND, 10000);

            RingBuilder builder = Ring.builder();
            builder.setSignalTime(calendar.getTime())
                    .setPuzzle(PuzzleFactory.CONNECT)
                    .setOnState(true, getApplicationContext())
                    .setMessage("SMS", "Hi",
                            "Gl", "+79502790929");

            Ring ring = (Ring)builder.build();
            user.addRing(ring);

            /*NotificationBuilder builder = new NotificationBuilder();
            builder.setSignalTime(calendar.getTime())
                    .setDescription("xhdfjgkuhlij;ok")
                    .setName("Notif")
                    .setPriority(Notification.PRIORITY_DEFAULT);

            Notification notification = (Notification) builder.build();
            user.addNotification(notification);
            notification.turnOn(getApplicationContext());
            String s = String.valueOf(notification.getCloseDate().getHours())
                    + "; "
                    + String.valueOf(notification.getCloseDate().getMinutes())
                    + "; "
                    + String.valueOf(notification.getCloseDate().getSeconds());
            textView.setText(s);*/
        });
    }
}
