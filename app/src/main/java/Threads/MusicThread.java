package Threads;

import android.content.Context;

import Library.Signals.IRing;

/**
 * Created by ytgv8b on 12.01.2018.
 */

public class MusicThread extends Thread {
    IRing signal;
    Context context;

    public MusicThread(Context context, IRing signal) {
        super("Music thread");
        this.signal = signal;
        this.context = context;
    }

    @Override
    public void run() {
        super.run();

        int melody = signal.getMelody();
        float volume = signal.getMelodyVolume();


    }

    @Override
    public void interrupt() {
        super.interrupt();
    }
}
