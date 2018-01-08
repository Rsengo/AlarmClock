package com.example.ytgv8b.firsttry.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;

import com.example.ytgv8b.firsttry.R;

import Library.DataHelpers.DataBaseHelper;
import Library.Signals.Ring;
import Library.User.User;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RingService extends Service {
    public RingService() {
    }

    private Thread musicThread;
    private Thread vibrationThread;
    /*private DataBaseHelper dataBaseHelper;
    private User user;*/
    private long[] vibrationDuration = {500, 300};
    private Vibrator vibrator;
    private int melody;
    private boolean isVibrating;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //user = User.getInstance();
        DataBaseHelper.init(this);
        //dataBaseHelper = DataBaseHelper.getInstance();
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        

        isVibrating = intent.getBooleanExtra("isVibrating", false);
        melody = intent.getIntExtra("melody", 0);
        // TODO: 08.01.2018 volume

        musicThread = new Thread(() ->
                MediaPlayer.create(this, R.raw.music).start(),
                "MusicThread");
        musicThread.start();

        if (isVibrating)
        {
            vibrationThread = new Thread(() ->
                    vibrator.vibrate(vibrationDuration, 0),
                    "VibrationThread");
            vibrationThread.start();
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicThread.interrupt();
        vibrationThread.interrupt();

        /*if (ring.isDeleteAfterUsing())
            dataBaseHelper.deleteRecursive(ring);*/
    }
}
