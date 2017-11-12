package Library.Messages.MessageFactory;

import Library.Messages.IMessage;

/**
 * Created by ytgv8b on 12.11.2017.
 */

public abstract class MessageFactory {
    protected IMessage message;

    public abstract void createMessage();
    public abstract void setText();
    public abstract void setRecepientName();
    public abstract IMessage create();
}