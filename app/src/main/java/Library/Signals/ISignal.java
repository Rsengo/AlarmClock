package Library.Signals;

import android.content.Context;

import java.util.Date;

import Library.IRealmModelWithID;
import Library.Signals.SignalBuilders.SignalFactory;
import io.realm.RealmModel;

/**
 * Created by ytgv8b on 28.10.2017.
 */

public interface ISignal extends IRealmModelWithID {
    void turnOn(Context context);
    void turnOff(Context context);
    void recountSignalTime(Context context);
    String getDescription();
    long remainingTimeInMillis();
    Date getSignalTime();
}
