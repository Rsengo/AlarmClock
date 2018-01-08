package Library.Messages.MessageFactory;

import Library.Messages.IMessage;
import Library.Signals.IRing;
import Library.Signals.Ring;

/**
 * Created by ytgv8b on 12.11.2017.
 */

public abstract class MessageFactory {
    protected IMessage message;
    protected IRing ring;

    public void initRing (IRing ring) {
        this.ring = ring;
    }

    public  void setId() {
        message.setId(ring.getId());
    }

    public abstract void createMessage();
    public abstract void setText();
    public abstract void setRecepient();
    public abstract IMessage create();
}
