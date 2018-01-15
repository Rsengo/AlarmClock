package Library.Signals.SignalBuilders;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;

import Library.Signals.INotification;
import Library.Signals.ISignal;
import Library.Signals.Notification;
import Library.Signals.Ring;
import Library.User.User;

/**
 * Created by ytgv8b on 21.10.2017.
 */

public class NotificationBuilder extends SignalFactory {

    private Notification signal = new Notification();

    @Override
    public NotificationBuilder setId() {
        signal.setId(Notification.getNextId());
        return this;
    }


    @Override
    public NotificationBuilder setUserEmail() {
        String userEmail = User.getInstance().getEmail();
        signal.setUserEmail(userEmail);
        return this;
    }

    public NotificationBuilder setPriority(byte priority) {
        signal.setPriority(priority);
        return this;
    }

    public NotificationBuilder setGeneralPeriodicity(byte generalPeriodicity) {
        signal.setGeneralPeriodicity(generalPeriodicity);
        return this;
    }

    /**public NotificationBuilder setSpecificPeriodicity(byte specificPeriodicity) {
        signal.setSpecificPeriodicity(specificPeriodicity);
        return this;
    }**/


    public NotificationBuilder setName(String name) {
        signal.setName(name);
        return this;
    }

    @Override
    public NotificationBuilder setSignalTime(Date signalTime) {
        signal.setSignalTime(signalTime);
        return this;
    }

    /**@Override
    public NotificationBuilder setRepeatSignalInterval(long repeatSignalInterval) {
        signal.setRepeatSignalInterval(repeatSignalInterval);
        return this;
    }**/

    @Override
    public NotificationBuilder setDescription(String description) {
        signal.setDescription(description);
        return this;
    }


    @Override
    public INotification build() {
        setId();
        setUserEmail();
        return signal;
    }
}