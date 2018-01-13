package Library.Messages.MessageFactory;

import android.support.annotation.NonNull;

import Library.Messages.IMessage;

/**
 * Created by ytgv8b on 12.11.2017.
 */

public abstract class MessageBuilder {
    protected IMessage message;

    @NonNull
    protected Class messageClass;

    public Class getMessageClass() {
        return messageClass;
    }

    public abstract MessageBuilder setText(String text);
    public abstract MessageBuilder setRecepient(String name, String address);

    public IMessage build () {
        return message;
    }
}
