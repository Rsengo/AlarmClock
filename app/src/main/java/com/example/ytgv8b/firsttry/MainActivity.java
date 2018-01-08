package com.example.ytgv8b.firsttry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.ytgv8b.firsttry.Services.RingService;

import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.PreferenceHelper;
import Library.Enums.FontSize;
import Library.Enums.Language;
import Library.Messages.SMS;
import Library.Settings.UserInterface;
import Library.Signals.Ring;
import Library.User.User;

public class MainActivity extends AppCompatActivity {

    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, RingService.class);
        startService(intent);

        PreferenceHelper.init(this);
        DataBaseHelper.init(this);

        PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();

        User user = User.getInstance();
        UserInterface userInterface = (UserInterface) user.getUserInterface();

        final Button button1 = (Button)findViewById(R.id.button);
        final Button button2 = (Button)findViewById(R.id.button2);
        final Button button3 = (Button)findViewById(R.id.button3);
        final TextView textView = (TextView)findViewById(R.id.textView);

        final SMS sms = new SMS();
        final long smsID = sms.getId();

        button1.setOnClickListener(view -> {
            user.setName("new user");
            user.setEmail("email");
            user.setMoneyQuantity(324);
            userInterface.setFontSize(FontSize.NORMAL);
            userInterface.setLanguage(Language.RUSSIAN);
            preferenceHelper.writePreference();
            sms.setText("my new message");
//            dataBaseHelper.saveData(sms);
//            textView.setText("Done");
            Ring ring = new Ring();
            //ring.setMessageID(sms.getId());
            ring.setUserEmail("email");
            ring.setDescription("desk");
            ring.setMessage(sms);
//            dataBaseHelper.saveData(ring);
//            dataBaseHelper.saveData(sms);
            dataBaseHelper.saveRecursive(ring);
          //  id = ring.getMessageID();
            textView.setText("Done");
        });

        button2.setOnClickListener(view -> {
            try {
               // dataBaseHelper.loadAllData();
                Ring ring = (Ring) user.getRings().get(0);
                SMS sms1 = (SMS) ring.getMessage();
                textView.setText(sms1.getText());
            }
            catch (NullPointerException ex) {
                textView.setText("Not found");
            }
        });

        button3.setOnClickListener(view -> {
            textView.setText(user.getEmail());
        });
    }
}
