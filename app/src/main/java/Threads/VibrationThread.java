package Threads;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by ytgv8b on 12.01.2018.
 */

public class VibrationThread extends Thread {
    private Vibrator vibrator;
    private long[] vibrationDuration = {500, 300};

    public VibrationThread(Context context) {
        super("Vibration thread");
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void run() {
        vibrator.vibrate(vibrationDuration, 0);
    }
}
