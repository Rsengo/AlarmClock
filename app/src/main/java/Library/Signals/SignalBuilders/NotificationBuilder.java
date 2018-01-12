package Library.Signals.SignalBuilders;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;

import Library.Signals.ISignal;
import Library.Signals.Notification;
import Library.Signals.Ring;
import Library.User.User;

/**
 * Created by ytgv8b on 21.10.2017.
 */

public class NotificationBuilder extends SignalFactory {

    private Notification signal;

    @Override
    public void createSignal() {
        signal = new Notification();
    }

    @Override
    public void setId() {
        signal.setId(Notification.getNextId());
    }


    @Override
    public void setUserEmail() {
        String userEmail = User.getInstance().getEmail();
        signal.setUserEmail(userEmail);
    }

    public void setPriority(byte priority) {
        signal.setPriority(priority);
    }

    public void setGeneralPeriodicity(byte generalPeriodicity) {
        signal.setGeneralPeriodicity(generalPeriodicity);
    }

    public void setSpecificPeriodicity(byte specificPeriodicity) {
        signal.setSpecificPeriodicity(specificPeriodicity);
    }


    public void setName(String name) {
        signal.setName(name);
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
    public ISignal build() {
        createSignal();
        setId();
        setUserEmail();
        return signal;
    }
}