package com.example.ytgv8b.firsttry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.PreferenceHelper;
import Library.Enums.FontSize;
import Library.Enums.Language;
import Library.Settings.UserInterface;
import Library.Signals.Ring;
import Library.User.User;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceHelper.init(this);


        final Button save = (Button)findViewById(R.id.saveButton);
        final Button load = (Button)findViewById(R.id.loadButton);
        final TextView textView = (TextView)findViewById(R.id.Text);
        final Button db = (Button)findViewById(R.id.dbButton);

        save.setOnClickListener(v ->
        {
            User user = (User) User.getInstance();
            user.setEmail("User@gmail.com");
            user.setName("User");
            user.setMoneyQuantity(1);
            UserInterface userInterface = (UserInterface) user.getUserInterface();
            userInterface.setFontSize(FontSize.NORMAL);
            userInterface.setLanguage(Language.RUSSIAN);
            PreferenceHelper.writePreference();

            Realm.init(this);
            RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();
            Realm.setDefaultConfiguration(config);
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();
            Ring ring1 = new Ring();
            ring1.setUserEmail("ghjghjgjgj");
            Ring ring = realm.copyToRealm(ring1);
            realm.commitTransaction();

            realm.close();

            textView.setText("Done");
        });

        load.setOnClickListener(v ->
        {
            User user = (User) User.getInstance();
            textView.setText(user.getEmail());
        });

        db.setOnClickListener(v ->
        {
            /***DataBaseHelper helper = new DataBaseHelper(this);
            User user = (User) User.getInstance();
            textView.setText(user.getRings().get(0).getUserEmail());***/
        });

    }
}
