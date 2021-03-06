package Library.Signals;

import android.content.Context;

/**
 * Created by ytgv8b on 16.11.2017.
 */

public interface IRing extends ISignal {
    void postpound(Context context);
    void sendMessage();
    String getMelody();
    long getTurnOffTime();
    boolean isDeleteAfterUsing();
    boolean isOnState();
    boolean isVibrating();
    byte[] getRepeatDays();
}
