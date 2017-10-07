package Library;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class User implements Serializable, IEdite {
    private String _name; //Имя
    private ArrayList<Alarm> _alarms = new ArrayList<>(); //Будильники
    private int _moneyQuantity; //Число монет
    private SMS _sms; //СМС
    private Voice _voice; //Голосовое отключение
    private UserInterface _userInterface; //Пользовательский интерфейс
    private ArrayList<Day> _schedule = new ArrayList<>(); //Расписание

    private static User _user; //Ссылка на себя
    private User(File userFile) { //Загрузка данных из объекта
        /****Десериализация****/
    }
    private User() { //Новый пользователь
        /****Ссылка на какой-то хэлпер(регистрация)****/
        /**Только имя, остально в настройках**/
    }
    private static User getInstance(String path) { //Создание пользователя
        if (_user == null) { //Если вход не выполнен
            if (new File(path).exists()) {  //Существование файла
                _user = new User(new File(path)); //Отправка файла на десериализацию
            } else { //Если файла нет
                _user = new User(); //Создание нового пользователя
            }
        }
        return _user; //Возврат ссылки
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public int getMoneyQuantity() {
        return _moneyQuantity;
    }

    public void setMoneyQuantity(int moneyQuantity) {
        _moneyQuantity = moneyQuantity;
    }

    public SMS getSms() {
        return _sms;
    }

    public void setSms(SMS sms) {
        _sms = sms;
    }

    public Voice getVoice() {
        return _voice;
    }

    public void setVoice(Voice voice) {
        _voice = voice;
    }

    public UserInterface getUserInterface() {
        return _userInterface;
    }

    public void setUserInterface(UserInterface userInterface) {
        _userInterface = userInterface;
    }

    @Override
    public void OpenEditeDialog() { //Редактирование
        //Открытие диалога редактирования
    }

    /****Добавление/удаление будильников с заходом в БД****/
    /****Добавление/удаление дней с заходом в БД****/
}

