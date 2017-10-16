package Library;

/**
 * Created by ytgv8b on 17.10.2017.
 */

interface IMessage {
    void send();
    void addRecepient(IMessageRecepient recepient);
    void removeRecepient(IMessageRecepient recepient);
}
