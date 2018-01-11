package com.example.ytgv8b.firsttry;

import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.PreferenceHelper;
import Library.Enums.FontSize;
import Library.Enums.Language;
import Library.Messages.IMessage;
import Library.Messages.SMS;
import Library.Settings.UserInterface;
import Library.Signals.Ring;
import Library.User.User;
import io.realm.Realm;
import io.realm.RealmQuery;

public class MainActivity extends AppCompatActivity {

    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceHelper.init(this);
        DataBaseHelper.init(this);

        PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();

        User user = User.getInstance();
        UserInterface userInterface = (UserInterface) user.getUserInterface();


        final SMS sms = new SMS();
        final long smsID = sms.getId();

    }
}
