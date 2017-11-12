package Library.Messages.MessageFactory;

import java.util.HashMap;

/**
 * Created by ytgv8b on 13.11.2017.
 */

public class MessageFactories {
    private HashMap<String, MessageFactory> factories = new HashMap<>();

    public MessageFactories() {
        factories.put("SMS", new SMSFactory());
    }

    public MessageFactory getFactory(String messageFactory) {
        return factories.get(messageFactory);
    }

}
