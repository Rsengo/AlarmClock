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
        signal.setId(Notification.getNextId());
    }

    @Override
    public void setRepeatSignalInterval() {

    }

    @Override
    public void setDescription() {

    }

    @Override
    public void setUserEmail() {
        String userEmail = User.getInstance().getEmail();
        signal.setUserEmail(userEmail);
    }

    @Override
    public void setSignalTime() {

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

    public void setName() {

    }

    @Override
    public ISignal create() {
        createSignal();
        setId();
        setName();
        setGeneralPeriodicity();
        setSpecificPeriodicity();
        setPriority();
        setSignalTime();
        setRepeatSignalInterval();
        setDescription();
        setDeleteAfterUsing();
        setUserEmail();
        return signal;
    }
}