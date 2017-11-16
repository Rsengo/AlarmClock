package Library.Messages;

import io.realm.RealmModel;

/**
 * Created by ytgv8b on 17.10.2017.
 */

public interface IMessage extends RealmModel{
    void send();
    void setRecepient(String name, String number);
    String getRecepientName();
    String getRecepientAddress();
    long getId();
    String getText();
    void setText(String text);
}
