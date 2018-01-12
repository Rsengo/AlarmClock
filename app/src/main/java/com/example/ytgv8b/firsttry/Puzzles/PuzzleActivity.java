package com.example.ytgv8b.firsttry.Puzzles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.PreferenceHelper;
import Library.Signals.IRing;
import Threads.MusicThread;
import Threads.VibrationThread;

/**
 * Created by ytgv8b on 12.01.2018.
 */

public abstract class PuzzleActivity extends AppCompatActivity {
    // TODO: 26.12.2017 время автовыкл.
    // TODO: 12.01.2018 message send after postpound
    public PuzzleActivity() {
        super();
    }

    protected String _alarmName = "";
    protected IRing ring;
    MusicThread musicThread;
    VibrationThread vibrationThread;

    @Override
    protected void onStart() {
        super.onStart();

        DataBaseHelper.init(this);
        PreferenceHelper.init(this);

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();

        Intent starterIntent = getIntent();
        int ringId = starterIntent.getIntExtra("id", -1);
        ring = dataBaseHelper.getRing(ringId);

        _alarmName = ring.getDescription();

        musicThread = new MusicThread(this, ring);
        vibrationThread = new VibrationThread(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        musicThread.interrupt();
        vibrationThread.interrupt();
    }
}
