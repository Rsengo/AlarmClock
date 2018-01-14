package com.example.ytgv8b.firsttry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Library.DataHelpers.PreferenceHelper;
import Library.User.User;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button button = (Button)findViewById(R.id.button2);
        EditText editText1 = (EditText)findViewById(R.id.editText);
        EditText editText2 = (EditText)findViewById(R.id.editText2);
        PreferenceHelper helper = PreferenceHelper.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText2.getText().equals("Введите имя")&&editText2.getText().equals(""))
                {

                }
                else if(!TextUtils.isEmpty(editText1.getText()) &&
                        android.util.Patterns.EMAIL_ADDRESS.matcher(editText1.getText()).matches())
                {
                    User user = User.getInstance();
                    user.setName(editText1.getText().toString());
                    user.setEmail(editText2.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    helper.changeStartTime();
                    helper.writePreference();
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
