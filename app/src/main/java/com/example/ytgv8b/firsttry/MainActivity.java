package com.example.ytgv8b.firsttry;

import android.graphics.Picture;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Library.Messages.IMessage;
import Library.Messages.SMS;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        IMessage sms = realm.createObject(SMS.class);
        ((SMS)sms).setText("Azazazaza");
        realm.commitTransaction();
        realm.close();

        final TextView txt = (TextView)findViewById(R.id.Text);
        Button go = (Button)findViewById(R.id.Yebash);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                RealmQuery<SMS> query = realm.where(SMS.class);
                IMessage results = query.findFirst();
                txt.setText(((SMS)results).getText());
            }
        });
    }
}
