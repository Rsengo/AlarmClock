package Library.Signals.SignalBuilders;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;

import Library.Signals.ISignal;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 21.10.2017.
 */

public abstract class SignalFactory {

    protected abstract SignalFactory setId();
    public abstract SignalFactory setSignalTime(Date signalTime);
    public abstract SignalFactory setRepeatSignalInterval(long repeatSignalInterval);
    public abstract SignalFactory setDescription(String description);
    protected abstract SignalFactory setUserEmail();
    public abstract ISignal build();
}