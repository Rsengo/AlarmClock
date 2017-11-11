package Library.Signals.SignalBuilders;

import Library.Signals.ISignal;

/**
 * Created by ytgv8b on 21.10.2017.
 */

abstract class SignalBuilder {
    protected ISignal signal;

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

    public ISignal getSignal() {
        return signal;
    }
}
