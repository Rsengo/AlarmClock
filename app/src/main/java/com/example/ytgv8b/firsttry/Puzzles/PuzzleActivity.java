package com.example.ytgv8b.firsttry.Puzzles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.PreferenceHelper;
import Library.Signals.IRing;

/**
 * Created by ytgv8b on 12.01.2018.
 */

public abstract class PuzzleActivity extends AppCompatActivity {
    public PuzzleActivity() {
        super();
    }

    protected String _alarmName = "";
    protected IRing ring;

    @Override
    protected void onStart() {
        super.onStart();

        DataBaseHelper.init(this);
        PreferenceHelper.init(this);

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();

        Intent starterIntent = getIntent();
        int ringId = starterIntent.getIntExtra("id", 0);
        ring = dataBaseHelper.getRing(ringId);

        _alarmName = ring.getDescription();

        // TODO: 12.01.2018 запуск потоков
    }

    @Override
    protected void onStop() {
        super.onStop();

        // TODO: 12.01.2018 закрыть потоки
    }
}
