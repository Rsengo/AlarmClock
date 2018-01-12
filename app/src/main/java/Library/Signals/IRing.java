package Library.Signals;

import android.content.Context;

/**
 * Created by ytgv8b on 16.11.2017.
 */

public interface IRing extends ISignal {
    void postpound(Context context);
    void sendMessage();
    int getMelody();
    long getTurnOffTime();
}
