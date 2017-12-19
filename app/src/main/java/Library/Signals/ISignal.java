package Library.Signals;

import android.content.Context;

import io.realm.RealmModel;

/**
 * Created by ytgv8b on 28.10.2017.
 */

public interface ISignal extends RealmModel {
    void start(Context context);
    void stop();
    void turnOn();
    void turnOff();
}
