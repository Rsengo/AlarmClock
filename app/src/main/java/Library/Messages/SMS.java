package Library.Messages;

import android.telephony.SmsManager;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class SMS extends RealmObject implements IMessage {
    /****Добавление/удаление получателей с заходом в БД****/
    /****Переопределить методы интерфейсов****/

    @PrimaryKey
    //private String id = UUID.randomUUID().toString();
    private long id;

    @Required
    private String text; //Текст

    private String recepientName; //Получатель

    private String recepientAddress; //Номер получателя

    public SMS(String text) {
        this.text = text;
    }

    public SMS() { }

    private void setRecepientName(String recepientName) {
        this.recepientName = recepientName;
    }

    private void setRecepientAddress(String recepientAddress) {
        this.recepientAddress = recepientAddress;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getRecepientName() {
        return recepientName;
    }

    @Override
    public String getRecepientAddress() {
        return recepientAddress;
    }

    @Override
    public void send() {
        SmsManager.getDefault().sendTextMessage(recepientAddress, null, text,
                null, null);
    }

    @Override
    public void setRecepient(String name, String address) {
        setRecepientName(name);
        setRecepientAddress(address);
    }

    public void setId(long id) {
        this.id = id;
    }
}
