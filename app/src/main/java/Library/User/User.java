package Library.User;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;

import Library.DataHelpers.DataSaver;
import Library.IEdite;
import Library.Settings.ISetting;
import Library.Settings.UserInterface;
import Library.Signals.ISignal;
import Library.User.UserBuilders.SavedUserBuilder;
import Library.User.UserBuilders.UserDirector;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class User implements IEdite, IUser {
    /****Добавление/удаление сигналов с заходом в БД****/
    /****Выйти из профиля****/
    /****Кэширование****/
    private String name; //Имя
    private ArrayList<ISignal> rings; //Будильники
    private ArrayList<ISignal> notifications; //События
    private int moneyQuantity; //Число монет
    private ISetting userInterface;
    private String email; //Email пользователя

    private static SharedPreferences preferences;
    private static UserDirector director;
    private static User user; //Ссылка на себя

    private User() { //Новый пользователь
        preferences = DataSaver.getPreferences();
        userInterface = UserInterface.getInstance();
    }

    public static User getInstance() { //Создание пользователя
        if (user == null) { //Если вход не выполнен
            user = new User(); //Создание нового пользователя
            /***потом убрать***/

            //Если имеются сохраненные настройки
            if (preferences.contains(DataSaver.getFileName())) { //если файл существует
                director = new UserDirector(new SavedUserBuilder(user));
            } else {
                /**регистрация/вход**/
            }

            try {
                director.construct();
            }
            catch (NullPointerException ex)
            {
                Log.e("Exception", "Null pointer, creation");
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
    public void logOut() {
        /***Сохранение данных на сервер***/
        DataSaver.removePreference();
    }
}
