package Library;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;

import Library.Settings.ISetting;
import Library.Settings.UserInterface;
import Library.Signals.ISignal;
import io.realm.RealmObject;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class User implements Serializable, IEdite, ISerialize {
    /****Добавление/удаление сигналов с заходом в БД****/
    /****Выйти из профиля****/
    /****Кэширование****/
    private String name; //Имя
    private ArrayList<ISignal> rings; //Будильники
    private ArrayList<ISignal> notifications; //События
    private int moneyQuantity; //Число монет
    private ISetting userInterface;
    //Пользовательский интерфейc
    private String email; //Email пользователя

    private final static String FILE_NAME = "User"; //Имя файла на диске

    private static User user; //Ссылка на себя

    private User() { //Новый пользователь
        /****Ссылка на какой-то хэлпер(регистрация)****/
    }

    /***МБ второй конструктор***/

    public static User getInstance() { //Создание пользователя
        if (user == null) { //Если вход не выполнен
            if (new File(FILE_NAME).exists()) {  //Существование файла
                user = deserialize(); //Отправка файла на десериализацию
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void OpenEditeDialog() { //Редактирование
        /****Открытие диалога редактирования****/
    }

    @Override
    public void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(this);
            outputStream.close();
            fos.close();
        }
        catch (FileNotFoundException ex)
        {
            Log.e("Exception", "FileNotFoundException");
        }
        catch (IOException ex)
        {
            Log.e("Exception", "IOException");
        }
    }

    private static User deserialize() {
        try {
            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            User temp = (User) inputStream.readObject();
            return temp;
        }
        catch (FileNotFoundException ex)
        {
            Log.e("Exception", "FileNotFoundException");
            return null;
        }
        catch (IOException ex)
        {
            Log.e("Exception", "IOException");
            return  null;
        }
        catch (ClassNotFoundException ex)
        {
            Log.e("Exception", "ClassNotFoundException");
            return null;
        }
    }

//    @Override
//    public void setSharePreference() {
//
//    }
//
//    @Override
//    public void getSharedPreference() {
//
//    }
}

