package Library.Signals.SignalBuilders;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;

import com.example.ytgv8b.firsttry.R;

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

    private Ring signal;

    @Override
    public void createSignal() {
        signal = new Ring();
    }

    @Override
    protected void setId() {
        signal.setId(Ring.getNextId());
    }

    @Override
    public void setSignalTime(Date signalTime) {
        signal.setSignalTime(signalTime);
    }

    @Override
    public void setRepeatSignalInterval(long repeatSignalInterval) {
        signal.setRepeatSignalInterval(repeatSignalInterval);
    }

    @Override
    public void setDescription(String description) {
        signal.setDescription(description);
    }

    @Override
    public void setDeleteAfterUsing(boolean deleteAfterUsing) {
        signal.setDeleteAfterUsing(deleteAfterUsing);
    }

    @Override
    protected void setUserEmail() {
        String userEmail = User.getInstance().getEmail();
        signal.setUserEmail(userEmail);
    }


    public  void setMessage(String messageFactory) {
        if (messageFactory != null) {
            MessageFactory signalFactory = MessageFactories.getFactory(messageFactory, signal);
            IMessage message = signalFactory.create();
            signal.setMessage(message);
        } else {
            signal.setMessage(null);
        }
    }

    public void setPuzzle(byte puzzle) {
        signal.setPuzzle(puzzle);
    }

    public void setVibrating(boolean vibration) {
        signal.setVibration(vibration);
    }

    public void setMelody(int melody) {
        signal.setMelody(melody);
    }

    public void setMelodyVolume(float melodyVolume) {
        signal.setMelodyVolume(melodyVolume);
    }

    public void setTurnOffTime(long turnOffTime) {
        signal.setTurnOffTime(turnOffTime);
    }


    public void setRepeatDays(byte[] repeatDays) {
        signal.setRepeatDays(repeatDays);
    }

    public void setOnState(boolean onState, Context context) {
        if (onState)
            signal.turnOn(context);
        else signal.setOnState(false);
    }

    @Override
    public ISignal build() {
        createSignal();
        setId();
        setUserEmail();
        return signal;
    }
}