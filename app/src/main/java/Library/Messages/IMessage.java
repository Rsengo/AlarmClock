package Library.Messages;

import Library.IRealmModelWithID;
import io.realm.RealmModel;

/**
 * Created by ytgv8b on 17.10.2017.
 */

public interface IMessage extends IRealmModelWithID{
    void send();
    void setRecepient(String name, String number);
    void setText(String text);
    String getText();
    String getRecepientName();
    String getRecepientAddress();
}
