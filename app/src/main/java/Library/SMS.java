package Library;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class SMS implements Serializable, IEdite {
    private String _text; //Текст
    private ArrayList<Recepient> _recepients = new ArrayList<>(); //Получатели

    private static SMS _sms; //Cсылка на себя
    private SMS() { } //Конструктор
    public static SMS getInstance() { //Создание СМС
        if (_sms == null) { //Если еще не создано
            _sms = new SMS(); //Создние нового
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

    /****Добавление/удаление получателей с заходом в БД****/
}
