package Library.Messages.MessageFactory;

import Library.Messages.IMessage;

/**
 * Created by ytgv8b on 12.11.2017.
 */

public class SMSFactory extends MessageFactory {
    @Override
    public void createMessage() {

    }

    @Override
    public void setText() {

    }

    @Override
    public void setRecepientName() {

    }

    public void setRecepientNumber() {

    }

    @Override
    public IMessage create () {
        createMessage();
        setText();
        setRecepientName();
        setRecepientNumber();
        return message;
    }

}
