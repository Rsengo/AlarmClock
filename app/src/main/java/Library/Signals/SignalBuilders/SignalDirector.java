package Library.Signals.SignalBuilders;

import Library.Signals.Signal;

/**
 * Created by ytgv8b on 21.10.2017.
 */

public class SignalDirector {

    public Signal createSignal(SignalBuilder builder) {
        builder.createSignal();
        builder.setId();
        builder.setRepeatSignalInterval();
        builder.setVibrating();
        builder.setMelody();
        builder.setMelodyVolume();
        builder.setTurnOffTime();
        builder.setDescription();
        builder.setOnState();
        /***Методы наследников***/
        return builder.getSignal();
    }
}
