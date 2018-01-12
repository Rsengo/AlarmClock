package Library.Signals.SignalBuilders;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;

import com.example.ytgv8b.firsttry.R;

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

public class RingFactory extends SignalFactory {

    private Ring signal;
    private String messageFactory;

    public RingFactory(String messageFactory, AppCompatActivity context) {
        super(context);
        this.messageFactory = messageFactory;
    }

    @Override
    public void createSignal() {
        signal = new Ring();
    }

    @Override
    public void setId() {
        signal.setId(Ring.getNextId());
    }

    @Override
    public void setSignalTime() {

    }

    @Override
    public void setRepeatSignalInterval() {

    }

    @Override
    public void setDescription() {

    }

    @Override
    public void setDeleteAfterUsing() {

    }

    @Override
    public void setUserEmail() {
        String userEmail = User.getInstance().getEmail();
        signal.setUserEmail(userEmail);
    }


    public  void setMessage() {
        if (messageFactory != null) {
            MessageFactory signalFactory = MessageFactories.getFactory(messageFactory, signal);
            IMessage message = signalFactory.create();
            signal.setMessage(message);
        } else {
            signal.setMessage(null);
        }
    }

    public void setPuzzle() {

    }

    public void setVibrating() {

    }

    public void setMelody() {

    }

    public void setMelodyVolume() {

    }

    public void setTurnOffTime() {

    }

    public void setOnState() {

    }

    public void setRepeatDays() {

    }

    @Override
    public ISignal create() {
        createSignal();
        setId();
        setRepeatSignalInterval();
        setVibrating();
        setMelody();
        setMelodyVolume();
        setTurnOffTime();
        setDescription();
        setOnState();
        setPuzzle();
        setRepeatDays();
        setDeleteAfterUsing();
        setUserEmail();
        setMessage();
        return signal;
    }
}