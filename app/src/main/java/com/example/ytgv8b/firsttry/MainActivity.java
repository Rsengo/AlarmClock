package com.example.ytgv8b.firsttry;

import android.graphics.Picture;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Library.DataHelpers.DataSaver;
import Library.Enums.FontSize;
import Library.Enums.Language;
import Library.Messages.IMessage;
import Library.Messages.SMS;
import Library.Settings.UserInterface;
import Library.User.User;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataSaver.init(this);

        final Button save = (Button)findViewById(R.id.saveButton);
        final Button load = (Button)findViewById(R.id.loadButton);
        final TextView textView = (TextView)findViewById(R.id.Text);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = User.getInstance();
                user.setEmail("User@gmail.com");
                user.setName("User");
                user.setMoneyQuantity(1);
                UserInterface userInterface = (UserInterface) user.getUserInterface();
                userInterface.setFontSize(FontSize.NORMAL);
                userInterface.setLanguage(Language.RUSSIAN);
                DataSaver.writePreference();
                textView.setText("Done");
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = User.getInstance();
                textView.setText(user.getEmail());
            }
        });

    }
}
