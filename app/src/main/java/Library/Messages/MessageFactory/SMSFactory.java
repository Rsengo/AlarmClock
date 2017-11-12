package Library.Messages.MessageFactory;

import Library.Messages.IMessage;
import Library.Messages.SMS;

/**
 * Created by ytgv8b on 12.11.2017.
 */

class SMSFactory extends MessageFactory {
    @Override
    public void createMessage() {
        message = new SMS();
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
