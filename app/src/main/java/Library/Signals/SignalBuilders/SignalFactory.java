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

    public abstract void createSignal();
    protected abstract void setId();
    public abstract void setSignalTime(Date signalTime);
    public abstract void setRepeatSignalInterval(long repeatSignalInterval);
    public abstract void setDescription(String description);
    public abstract void setDeleteAfterUsing(boolean deleteAfterUsing);
    protected abstract void setUserEmail();
    public abstract ISignal build();
}