package Library.Signals.SignalBuilders;

import Library.Signals.Ring;
import Library.User.User;

/**
 * Created by ytgv8b on 21.10.2017.
 */

public class RingBuilder extends SignalBuilder {

    public RingBuilder() {
        super();
    }

    @Override
    public void createSignal() {

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

    public  void setSms() {

    }

    public  void setRepeatDays() {

    }
}
