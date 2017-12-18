package Library.Signals.SignalBuilders;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import Library.Signals.ISignal;

/**
 * Created by ytgv8b on 21.10.2017.
 */

abstract class SignalFactory {
    protected AppCompatActivity context;

    public SignalFactory(AppCompatActivity context)
    {
        this.context = context;
    }

    /****public static long id;****/ /**Пока хз как реализуется**/
    public abstract void createSignal();
    public abstract void setId(); /****мб и не понадобится****/
    public abstract void setRepeatSignalInterval();
    public abstract void setVibrating();
    public abstract void setMelody();
    public abstract void setMelodyVolume();
    public abstract void setTurnOffTime();
    public abstract void setDescription();
    public abstract void setOnState();
    public abstract void setUserEmail();
    public abstract ISignal create();
}
