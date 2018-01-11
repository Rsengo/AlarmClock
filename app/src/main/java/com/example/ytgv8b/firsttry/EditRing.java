package com.example.ytgv8b.firsttry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EditRing extends AppCompatActivity {

    private int idRing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ring);
        Intent intent = getIntent();
        idRing =intent.getIntExtra("id",0);

    }
}
