package Library.Signals.SignalBuilders;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;



import java.util.Date;

import Library.Messages.IMessage;
import Library.Messages.MessageFactory.MessageFactories;
import Library.Messages.MessageFactory.MessageFactory;
import Library.Signals.ISignal;
import Library.Signals.Notification;
import Library.Signals.Ring;
import Library.User.User;

/**
 * Created by ytgv8b on 21.10.2017.
 */

public class RingBuilder extends SignalFactory {

    private Ring signal = new Ring();

    @Override
    protected RingBuilder setId() {
        signal.setId(Ring.getNextId());
        return this;
    }

    @Override
    public RingBuilder setSignalTime(Date signalTime) {
        signal.setSignalTime(signalTime);
        return this;
    }

    @Override
    public RingBuilder setRepeatSignalInterval(long repeatSignalInterval) {
        signal.setRepeatSignalInterval(repeatSignalInterval);
        return this;
    }

    @Override
    public RingBuilder setDescription(String description) {
        signal.setDescription(description);
        return this;
    }

    @Override
    public RingBuilder setDeleteAfterUsing(boolean deleteAfterUsing) {
        signal.setDeleteAfterUsing(deleteAfterUsing);
        return this;
    }

    @Override
    protected RingBuilder setUserEmail() {
        String userEmail = User.getInstance().getEmail();
        signal.setUserEmail(userEmail);
        return this;
    }


    public  RingBuilder setMessage(String messageFactory) {
        if (messageFactory != null) {
            MessageFactory signalFactory = MessageFactories.getFactory(messageFactory, signal);
            IMessage message = signalFactory.create();
            signal.setMessage(message);
        } else {
            signal.setMessage(null);
        }

        return this;
    }

    public RingBuilder setPuzzle(byte puzzle) {
        signal.setPuzzle(puzzle);
        return this;
    }

    public RingBuilder setVibrating(boolean vibration) {
        signal.setVibration(vibration);
        return this;
    }

    public RingBuilder setMelody(int melody) {
        signal.setMelody(melody);
        return this;
    }

    public RingBuilder setTurnOffTime(long turnOffTime) {
        signal.setTurnOffTime(turnOffTime);
        return this;
    }


    public RingBuilder setRepeatDays(byte[] repeatDays) {
        signal.setRepeatDays(repeatDays);
        return this;
    }

    public RingBuilder setOnState(boolean onState, Context context) {
        if (onState)
            signal.turnOn(context);
        else signal.setOnState(false);
        return this;
    }

    @Override
    public ISignal build() {
        setId();
        setUserEmail();
        return signal;
    }
}