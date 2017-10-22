package Library.Messages;

import java.util.ArrayList;

import Library.IEdite;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class SMS extends RealmObject implements IEdite, IMessage {
    /****Добавление/удаление получателей с заходом в БД****/
    /****Переопределить методы интерфейсов****/

//    @PrimaryKey
//    private long id;

    @Required
    private String text; //Текст

    @Required
    private RealmList<String> recepients = new RealmList<>(); //Получатели

    private static SMS sms; //Cсылка на себя

    private SMS() { }

    public SMS (String text, RealmList<String> recepients)
    {
        this.text = text;
        this.recepients = recepients;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void OpenEditeDialog() { //Редактирование
        //Открытие диалога редактирования
    }

    @Override
    public void send() {

    }

    public RealmList<String> getRecepients() {
        return recepients;
    }

    public void setRecepients(RealmList<String> recepients) {
        this.recepients = recepients;
    }

//    public long getId() {
//        return id;
//    }
}
