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

    }

    @Override
    public void setRepeatSignalInterval() {

    }

    @Override
    public void setVibrating() {
    }

    @Override
    public void setMelody() {

    }

    @Override
    public void setMelodyVolume() {

    }

    @Override
    public void setTurnOffTime() {

    }

    @Override
    public void setDescription() {

    }

    @Override
    public void setOnState() {

    }

    @Override
    public void setUserEmail() {
        String userEmail = User.getInstance().getEmail();
        ((Ring)signal).setUserEmail(userEmail);
    }

    public  void setTurnOffMethod() {

    }

    public  void setPuzzle() {

    }

    public  void setMessage() {
        if (messageFactory != null) {
            MessageFactories factories = new MessageFactories();
            MessageFactory signalFactory = factories.getFactory(messageFactory);
            IMessage message = signalFactory.create();
            ((Ring) signal).setMessage(message);
        } else {
            ((Ring) signal).setMessage(null);
        }
    }

    public  void setRepeatDays() {

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
        setTurnOffTime();
        setDescription();
        setOnState();
        setTurnOffTime();
        setPuzzle();
        setRepeatDays();
        return signal;
    }
}
