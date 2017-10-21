package Library.Messages;

import java.util.ArrayList;

import Library.Interfaces.IEdite;
import Library.Interfaces.IMessage;
import Library.Interfaces.IMessageRecepient;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class SMS implements IEdite, IMessage {
    /****Добавление/удаление получателей с заходом в БД****/
    /****Переопределить методы интерфейсов****/
    private String text; //Текст
    private ArrayList<IMessageRecepient> recepients = new ArrayList<>(); //Получатели

    private static SMS sms; //Cсылка на себя

    private SMS() { }

    private SMS (String text, ArrayList<IMessageRecepient> recepients)
    {
        this.text = text;
        this.recepients = recepients;
    }

    public static SMS getInstance() { //Создание СМС
        if (sms == null) { //Если еще не создано
            sms = new SMS(); //Создние нового
        }
        return sms; //Возврат ссылки
    }

    public static SMS getInstance(String text, ArrayList<IMessageRecepient> recepients) {
        //Создание СМС с начальными параметрами
        if (sms == null) { //Если еще не создано
            sms = new SMS(text, recepients); //Создние нового
        }
        return sms; //Возврат ссылки
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

    @Override
    public void addRecepient(IMessageRecepient recepient) {

    }

    @Override
    public void removeRecepient(IMessageRecepient recepient) {

    }
}
