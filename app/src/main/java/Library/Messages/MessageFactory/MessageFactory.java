package Library.Messages.MessageFactory;

import android.support.annotation.NonNull;

import Library.Messages.IMessage;
import Library.Signals.IRing;
import Library.Signals.Ring;

/**
 * Created by ytgv8b on 12.11.2017.
 */

public abstract class MessageFactory {
    protected IMessage message;
    protected IRing ring;

    @NonNull
    protected Class messageClass;

    public void initRing (IRing ring) {
        this.ring = ring;
    }

    public Class getMessageClass() {
        return messageClass;
    }

    public  void setId() {
        message.setId(ring.getId());
    }

    protected abstract void createMessage();
    protected abstract void setText();
    protected abstract void setRecepient();
    public abstract IMessage create();
}
