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

abstract class SignalFactory {
    protected AppCompatActivity context;

    public SignalFactory(AppCompatActivity context)
    {
        this.context = context;
    }

    public abstract void createSignal();
    public abstract void setId();
    public abstract void setSignalTime();
    public abstract void setRepeatSignalInterval();
    public abstract void setDescription();
    public abstract void setDeleteAfterUsing();
    public abstract void setUserEmail();
    public abstract ISignal create();
}