package com.example.ytgv8b.firsttry.Puzzles;

import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.PreferenceHelper;
import Library.Signals.IRing;
import Library.User.User;
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

        this.setVolumeControlStream(AudioManager.STREAM_RING);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        DataBaseHelper.init(this);
        PreferenceHelper.init(this);

        dataBaseHelper = DataBaseHelper.getInstance();

        Intent starterIntent = getIntent();
        int ringId = starterIntent.getIntExtra("id", -1);
        ring = dataBaseHelper.getRing(ringId);

        _alarmName = ring.getDescription();

        musicThread = new MusicThread(this, ring);
        musicThread.start();

        if (ring.isVibrating()) {
            vibrationThread = new VibrationThread(this);
            vibrationThread.start();
        }

        timer = new Timer();
        timerTask = new AutoTurnOffTask(this);

        timer.schedule(timerTask, ring.getTurnOffTime());


    }

    @Override
    protected void onStop() {
        super.onStop();

        musicThread.interrupt();

        if (ring.isVibrating())
            vibrationThread.interrupt();
        //ring.postpound(this);

        if (ring.isDeleteAfterUsing()) {
            ring.turnOff(getApplicationContext());
            User.removeRingById(ring.getId());
        } else {
            ring.recountSignalTime(this);
            dataBaseHelper.saveRecursive(ring);
        }
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onUserLeaveHint() {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {}
        return true;
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {}
        return true;
    }
}
