package Library;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class User implements Serializable, IEdite {
    /****добавить абстракции****/
    private String _name; //Имя
    private ArrayList<Signal> _alarms = new ArrayList<>(); //Будильники
    private ArrayList<Signal> _cases = new ArrayList<>(); //События
    private int _moneyQuantity; //Число монет
    private UserInterface _userInterface; /****мб сделать абстракцию****/
    //Пользовательский интерфейс

    private static User _user; //Ссылка на себя
    private User() { //Новый пользователь
        /****Ссылка на какой-то хэлпер(регистрация)****/
        /**Только имя, остальное в настройках**/
    }
    private static User getInstance(String path) { //Создание пользователя
        if (_user == null) { //Если вход не выполнен
            if (new File(path).exists()) {  //Существование файла
                _user = Deserialize(new File(path)); //Отправка файла на десериализацию
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

    public UserInterface getUserInterface() {
        return _userInterface;
    }

    public void setUserInterface(UserInterface userInterface) {
        _userInterface = userInterface;
    }

    /****Добавление/удаление будильников/событий с заходом в БД****/
    /****Выйти из профиля****/

    @Override
    public void OpenEditeDialog() { //Редактирование
        /****Открытие диалога редактирования****/
    }

    private static User Deserialize(File file) { //Загрузка данных из объекта
        /****Десериализация****/
        return null;
    }

    public void Serialize() {
        /****Сериализация****/

    }
}

