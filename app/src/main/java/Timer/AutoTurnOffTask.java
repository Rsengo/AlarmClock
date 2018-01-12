package Timer;

import android.support.v7.app.AppCompatActivity;

import java.util.TimerTask;

/**
 * Created by ytgv8b on 13.01.2018.
 */

public class AutoTurnOffTask extends TimerTask {
    private AppCompatActivity activity;

    public AutoTurnOffTask(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        activity.finish();
    }
}
