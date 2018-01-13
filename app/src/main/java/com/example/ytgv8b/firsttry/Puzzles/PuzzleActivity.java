package com.example.ytgv8b.firsttry.Puzzles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

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

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        // TODO: 13.01.2018 locking
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
        //ring.postpound(this);

        if (ring.isDeleteAfterUsing()) {
            dataBaseHelper.deleteData(ring);
        } else {
            ring.recountSignalTime(this);
            dataBaseHelper.saveRecursive(ring);
        }
    }
}