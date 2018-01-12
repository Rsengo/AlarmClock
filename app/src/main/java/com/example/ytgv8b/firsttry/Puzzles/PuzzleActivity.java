package com.example.ytgv8b.firsttry.Puzzles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.PreferenceHelper;
import Library.Signals.IRing;
import Threads.MusicThread;
import Threads.VibrationThread;
import Timer.*;

/**
 * Created by ytgv8b on 12.01.2018.
 */

public abstract class PuzzleActivity extends AppCompatActivity {
    public PuzzleActivity() {
        super();
    }

    protected String _alarmName = "";
    protected IRing ring;
    protected MusicThread musicThread;
    protected VibrationThread vibrationThread;
    protected TimerTask timerTask;
    protected Timer timer;
    private  DataBaseHelper dataBaseHelper;

    @Override
    protected void onStart() {
        super.onStart();

        DataBaseHelper.init(this);
        PreferenceHelper.init(this);

        dataBaseHelper = DataBaseHelper.getInstance();

        Intent starterIntent = getIntent();
        int ringId = starterIntent.getIntExtra("id", -1);
        ring = dataBaseHelper.getRing(ringId);

        _alarmName = ring.getDescription();

        musicThread = new MusicThread(this, ring);
        vibrationThread = new VibrationThread(this);

        musicThread.start();
        vibrationThread.start();

        timer = new Timer();
        timerTask = new AutoTurnOffTask(this);

        timer.schedule(timerTask, ring.getTurnOffTime());
    }

    @Override
    protected void onStop() {
        super.onStop();

        musicThread.interrupt();
        vibrationThread.interrupt();

        if (ring.isDeleteAfterUsing()) {
            dataBaseHelper.deleteData(ring);
        }
    }
}
