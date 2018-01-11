package com.example.ytgv8b.firsttry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Library.DataHelpers.PreferenceHelper;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button button = (Button)findViewById(R.id.button);
        EditText editText1 = (EditText)findViewById(R.id.editText);
        EditText editText2 = (EditText)findViewById(R.id.editText2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText2.getText().equals("Введите имя")&&editText2.getText().equals(""))
                {

                }
                else if(!TextUtils.isEmpty(editText1.getText()) && android.util.Patterns.EMAIL_ADDRESS.matcher(editText1.getText()).matches())
                {
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(intent);
                    PreferenceHelper helper = PreferenceHelper.getInstance();
                    helper.changeStartTime();
                }
            }
        });
    }
}
