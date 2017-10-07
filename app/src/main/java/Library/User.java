package Library;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class User implements IEdite, Serializable {
    private String _name; //Имя
    private ArrayList<Alarm> _alarms = new ArrayList<>(); //Будильники
    private int _moneyQuantity; //Число монет
    private SMS _sms; //СМС
    private Voice _voice; //Голосовое отключение
    private UserInterface _userInterface; //Пользовательский интерфейс
    private ArrayList<Day> _schedule = new ArrayList<>(); //Расписание

    private static User user; //Ссылка на себя
    private User(File userFile) { //Загрузка данных из объекта
        /****Десериализация****/
    }
    private User() { //Новый пользователь
        /****Ссылка на какой-то хэлпер(регистрация)****/
        /**Только имя, остально в настройках**/
    }
    public  static User getInstance(String path) { //Создание пользователя/
        if (user == null) { //Если вход не выполнен
            if (new File(path).exists()) {  //Существование файла
                user = new User(new File(path)); //Отправка файла на десериализацию
            } else { //Если файла нет
                user = new User(); //Создание нового пользователя
            }
        }
        return user; //Возврат ссылки
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
}

