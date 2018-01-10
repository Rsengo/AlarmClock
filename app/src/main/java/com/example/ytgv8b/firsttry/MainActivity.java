package com.example.ytgv8b.firsttry;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.ytgv8b.firsttry.Services.RingService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.PreferenceHelper;
import Library.Enums.FontSize;
import Library.Enums.Language;
import Library.Messages.IMessage;
import Library.Messages.SMS;
import Library.Settings.UserInterface;
import Library.Signals.IRing;
import Library.Signals.Ring;
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
            settings.setLanguage(Language.RUSSIAN);
            settings.setColorSchemeId(0);
            settings.setFontSize(FontSize.NORMAL);
            preferenceHelper.writePreference();
        });

        Button button3 = (Button)findViewById(R.id.button5);

        button3.setOnClickListener(view -> {
            User user = User.getInstance();
            textView.setText(user.getEmail());
        });

        Button button2 = (Button)findViewById(R.id.button2);

        button2.setOnClickListener(view -> {
            User user = User.getInstance();
            Ring ring = new Ring();
            ring.setId(Ring.getNextId());
            ring.setUserEmail(user.getEmail());
            ring.setDescription("dgdfgdgdgfd "+ring.getId());
            textView.setText(ring.getUserEmail());
            SMS message = new SMS();
            message.setId(ring.getId());
            message.setText("my message");
            ring.setMessage(message);
            user.addRing(ring);

        });

        Button button7 = (Button)findViewById(R.id.button7);

        button7.setOnClickListener(view -> {
            User user = User.getInstance();
            //user.setRings(dataBaseHelper.loadRings());
            //IRing ring = user.getRings()
                //    .get(0);
//            textView.setText(((Ring)ring).getDescription());
            textView.setText(String.valueOf(user.getRings().size()));
        });

        Button button6 = (Button)findViewById(R.id.button6);

        button6.setOnClickListener(view -> {
            User user = User.getInstance();
            user.removeRing(0);
        });
    }
}
