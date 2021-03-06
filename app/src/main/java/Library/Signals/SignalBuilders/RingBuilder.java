package Library.Signals.SignalBuilders;


import java.util.Date;

import Library.DataHelpers.FileSystemHelper;
import Library.Messages.IMessage;
import Library.Signals.IRing;
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
    protected RingBuilder setUserEmail() {
        String userEmail = User.getInstance().getEmail();
        signal.setUserEmail(userEmail);
        return this;
    }

    public RingBuilder setDeleteAfterUsing(boolean deleteAfterUsing) {
        signal.setDeleteAfterUsing(deleteAfterUsing);
        return this;
    }

    public  RingBuilder setMessage(IMessage message) {
        signal.setMessage(message);
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

    public RingBuilder setMelody(String melody) {
        signal.setMelody(melody);
        return this;
    }

    public RingBuilder setRepeatDays(byte[] repeatDays) {
        signal.setRepeatDays(repeatDays);
        return this;
    }

    @Override
    public IRing build() {
        setId();

        if (signal.getMessage() != null)
            signal.getMessage().setId(signal.getId());

        setUserEmail();

        if (signal.getMelody() == null) {
            FileSystemHelper helper = FileSystemHelper.getInstance();
            signal.setMelody(helper.loadMelodies().get(0));
        }

        return signal;
    }
}