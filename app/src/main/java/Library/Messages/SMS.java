package Library.Messages;

import android.telephony.SmsManager;

import Library.IRealmModelWithID;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 08.10.2017.
 */


public class SMS extends RealmObject implements IMessage{
    /****Добавление/удаление получателей с заходом в БД****/
    /****Переопределить методы интерфейсов****/

    @PrimaryKey
    private int id;

    @Required
    private String text; //Текст

    private String recepientName; //Получатель

    private String recepientAddress; //Номер получателя

    public String getText() {
        return text;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getRecepientName() {
        return recepientName;
    }

    public String getRecepientAddress() {
        return recepientAddress;
    }

    public void setRecepientName(String recepientName) {
        this.recepientName = recepientName;
    }

    public void setRecepientAddress(String recepientAddress) {
        this.recepientAddress = recepientAddress;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void send() {
        SmsManager.getDefault().sendTextMessage(recepientAddress, null, text,
                null, null);
    }

    @Override
    public void setRecepient(String recepientName, String recepientAddress) {
        this.recepientName = recepientName;
        this.recepientAddress = recepientAddress;
    }
}
