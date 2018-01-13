package Library.Messages.MessageFactory;

import Library.Messages.SMS;

/**
 * Created by ytgv8b on 12.11.2017.
 */

public class SMSBuilder extends MessageBuilder {

    public SMSBuilder() {
        messageClass = SMS.class;
        message = new SMS();
    }


    @Override
    public SMSBuilder setText(String text) {
        message.setText(text);
        return this;
    }

    @Override
    public SMSBuilder setRecepient(String name, String number) {
        message.setRecepient(name, number);
        return this;
    }
}
