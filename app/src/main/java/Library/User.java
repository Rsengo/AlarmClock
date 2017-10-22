package Library;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;

import Library.Settings.ISetting;
import Library.Settings.UserInterface;
import Library.Signals.Signal;
import io.realm.RealmObject;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class User implements Serializable, IEdite {
    /****Добавление/удаление сигналов с заходом в БД****/
    /****Выйти из профиля****/
    /****Кэширование****/
    private String name; //Имя
    private ArrayList<Signal> alarms = new ArrayList<>(); //Будильники
    private ArrayList<Signal> notifications = new ArrayList<>(); //События
    private int moneyQuantity; //Число монет
    private ISetting userInterface; /****мб сделать абстракцию****/
    //Пользовательский интерфейс

    private static User user; //Ссылка на себя
    private User() { //Новый пользователь
        /****Ссылка на какой-то хэлпер(регистрация)****/
        /**Только имя, остальное в настройках**/
    }
    private static User getInstance(String path) { //Создание пользователя
        if (user == null) { //Если вход не выполнен
            if (new File(path).exists()) {  //Существование файла
                user = Deserialize(new File(path)); //Отправка файла на десериализацию
            } else { //Если файла нет
                user = new User(); //Создание нового пользователя
            }
        }
        return user; //Возврат ссылки
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoneyQuantity() {
        return moneyQuantity;
    }

    public void setMoneyQuantity(int moneyQuantity) {
        this.moneyQuantity = moneyQuantity;
    }

    public ISetting getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void OpenEditeDialog() { //Редактирование
        /****Открытие диалога редактирования****/
    }

    private static User Deserialize(File file) { //Загрузка данных из объекта
        /****Десериализация(Кэш)****/
        return null;
    }

    public void Serialize() {
        /****Сериализация(Кэш)****/

    }

    public void addSignal(Signal signal)
    {

    }

    public void removeSignal(Signal signal)
    {

    }
}

