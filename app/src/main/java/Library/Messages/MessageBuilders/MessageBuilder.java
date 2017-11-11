package Library.Messages.MessageBuilders;

import Library.Messages.IMessage;

/**
 * Created by ytgv8b on 12.11.2017.
 */

public abstract class MessageBuilder {
    protected IMessage message;

    public abstract void createMessage();
    public abstract void setText();
    public abstract void setRecepientName();

    public IMessage getMessage() {
        return message;
    }
}
