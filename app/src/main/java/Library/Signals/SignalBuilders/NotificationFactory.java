package Library.Signals.SignalBuilders;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import Library.Signals.ISignal;
import Library.Signals.Notification;
import Library.Signals.Ring;
import Library.User.User;

/**
 * Created by ytgv8b on 21.10.2017.
 */

public class NotificationFactory extends SignalFactory {

    private Notification signal;

    public NotificationFactory(AppCompatActivity context) {
        super(context);
    }

    @Override
    public void createSignal() {
        signal = new Notification();
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
        signal.setUserEmail(userEmail);
    }

    public void setDate() {

    }

    public void setPriority() {

    }

    public void setGeneralPeriodicity() {

    }

    public void setSpecificPeriodicity() {

    }

    @Override
    public void setDeleteAfterUsing() {

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
        return signal;
    }
}
