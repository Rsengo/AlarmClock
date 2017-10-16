package Library;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class SMS implements IEdite, IMessage {
    /****Добавление/удаление получателей с заходом в БД****/
    /****Переопределить методы интерфейсов****/
    private String _text; //Текст
    private ArrayList<IMessageRecepient> _recepients = new ArrayList<>(); //Получатели

    private static SMS _sms; //Cсылка на себя

    private SMS() { }

    private SMS (String text, ArrayList<IMessageRecepient> recepients)
    {
        _text = text;
        _recepients = recepients;
    }

    public static SMS getInstance() { //Создание СМС
        if (_sms == null) { //Если еще не создано
            _sms = new SMS(); //Создние нового
        }
        return _sms; //Возврат ссылки
    }

    public static SMS getInstance(String text, ArrayList<IMessageRecepient> recepients) {
        //Создание СМС с начальными параметрами
        if (_sms == null) { //Если еще не создано
            _sms = new SMS(text, recepients); //Создние нового
        }
        return _sms; //Возврат ссылки
    }

    public String getText() {
        return _text;
    }

    public void setText(String text) {
        _text = text;
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
