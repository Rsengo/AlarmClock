package Library.Messages.MessageFactory;

import java.util.HashMap;

import Library.Signals.IRing;

/**
 * Created by ytgv8b on 13.11.2017.
 */

public class MessageFactories {
    private HashMap<String, MessageFactory> factories = new HashMap<>();

    public MessageFactories() {
        factories.put("SMS", new SMSFactory());
    }

    public MessageFactory getFactory(String messageFactory, IRing ring) {
        MessageFactory factory = factories.get(messageFactory);
        factory.initRing(ring);
        return factory;
    }

}
