package Library.Signals;

import android.content.Context;

import Library.Messages.IMessage;

/**
 * Created by ytgv8b on 16.11.2017.
 */

public interface IRing extends ISignal {
    void postpound(Context context);
    void sendMessage();
    String getMelody();
    long getTurnOffTime();
    boolean isDeleteAfterUsing();
    IMessage getMessage();
}
