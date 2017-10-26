package Library;

import android.content.Context;
import android.provider.ContactsContract;

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

public class User implements Serializable, IEdite, ISerialize {
    /****Добавление/удаление сигналов с заходом в БД****/
    /****Выйти из профиля****/
    /****Кэширование****/
    private String name; //Имя
    private ArrayList<Signal> alarms = new ArrayList<>(); //Будильники
    private ArrayList<Signal> notifications = new ArrayList<>(); //События
    private int moneyQuantity; //Число монет
    private ISetting userInterface;
    //Пользовательский интерфейc
    private String email; //Email пользователя
    private final static String fileName = "User"; //Имя сериализованного файла

    private static User user; //Ссылка на себя

    private User() { //Новый пользователь
        /****Ссылка на какой-то хэлпер(регистрация)****/
        /**Только имя, остальное в настройках**/
    }

    /***МБ второй конструктор***/

    public static User getInstance(Context context) { //Создание пользователя
        if (user == null) { //Если вход не выполнен
            if (new File(fileName).exists()) {  //Существование файла
                user = deserialize(context); //Отправка файла на десериализацию
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


    public void addSignal(Signal signal)
    {

    }

    public void removeSignal(Signal signal)
    {

    }

    @Override
    public void serialize(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(this);
            outputStream.close();
            fos.close();
        }
        catch (FileNotFoundException ex)
        {

        }
        catch (IOException ex)
        {

        }
    }

    private static User deserialize(Context context) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            User temp = (User) inputStream.readObject();
            return temp;
        }
        catch (FileNotFoundException ex)
        {
            return null;
        }
        catch (IOException ex)
        {
            return  null;
        }
        catch (ClassNotFoundException ex)
        {
            return null;
        }
    }
}

