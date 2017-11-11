package Library.Messages;

import java.util.ArrayList;
import java.util.UUID;

import Library.IEdite;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class SMS extends RealmObject implements IEdite, IMessage {
    /****Добавление/удаление получателей с заходом в БД****/
    /****Переопределить методы интерфейсов****/

    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    @Required
    private String text; //Текст

    private String recepientName; //Получатель

    private String recepientNumber; //Номер получателя

    public SMS(String text) {
        this.text = text;
    }

    public SMS() { }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    @Override
    public void send() {

    }

    @Override
    public void addRecepient() {

    }

    @Override
    public void removeRecepient() {

    }

    @Override
    public void OpenEditeDialog() { //Редактирование
        //Открытие диалога редактирования
    }
}
