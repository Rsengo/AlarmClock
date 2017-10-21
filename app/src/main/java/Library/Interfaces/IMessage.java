package Library.Interfaces;

/**
 * Created by ytgv8b on 17.10.2017.
 */

public interface IMessage {
    void send();
    void addRecepient(IMessageRecepient recepient);
    void removeRecepient(IMessageRecepient recepient);
}
