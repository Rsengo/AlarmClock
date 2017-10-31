package Library.Signals.SignalBuilders;

import Library.Signals.ISignal;

/**
 * Created by ytgv8b on 21.10.2017.
 */

public class SignalDirector {
    SignalBuilder builder;

    public SignalDirector(SignalBuilder builder)
    {
        this.builder = builder;
    }

    public ISignal construct() {
        builder.createSignal();
        builder.setId();
        builder.setRepeatSignalInterval();
        builder.setVibrating();
        builder.setMelody();
        builder.setMelodyVolume();
        builder.setTurnOffTime();
        builder.setDescription();
        builder.setOnState();
        if (builder instanceof RingBuilder)
        {
            ((RingBuilder) builder).setTurnOffMethod();
            ((RingBuilder) builder).setPuzzle();
            ((RingBuilder) builder).setSms();
            ((RingBuilder) builder).setRepeatDays();
        }
        if (builder instanceof  NotificationBuilder)
        {
            ((NotificationBuilder) builder).setDate();
            ((NotificationBuilder) builder).setPriority();
            ((NotificationBuilder) builder).setGeneralPeriodicity();
            ((NotificationBuilder) builder).setSpecificPeriodicity();
        }
        return builder.getSignal();
    }
}
