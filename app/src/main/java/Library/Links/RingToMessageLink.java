package Library.Links;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 12.11.2017.
 */

public class RingToMessageLink extends RealmObject {
    @PrimaryKey
    private String ringID;
    @Required
    private String messageID;

    public String getRingID() {
        return ringID;
    }

    public void setRingID(String ringID) {
        this.ringID = ringID;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }
}
