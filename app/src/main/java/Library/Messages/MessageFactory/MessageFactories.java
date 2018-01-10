package Library.Messages.MessageFactory;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;

import Library.Signals.IRing;

/**
 * Created by ytgv8b on 13.11.2017.
 */

public class MessageFactories {
    private static HashMap<String, MessageFactory> factories = new HashMap<>();

    public static MessageFactory getFactory(String messageFactory, IRing ring) {
        complete();
        MessageFactory factory = factories.get(messageFactory);
        factory.initRing(ring);
        return factory;
    }

    public static int size() {
        complete();
        return factories.size();
    }

    public static Collection<MessageFactory> factories() {
        complete();
        return factories.values();
    }

    private static void complete() {
        if (factories.isEmpty()) {
            factories.put("SMS", new SMSFactory());
        }
    }
}
