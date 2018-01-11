package Library.User;

import android.util.Log;

import java.util.ArrayList;

import Library.DataHelpers.PreferenceHelper;
import Library.Settings.ISetting;
import Library.Settings.UserInterface;
import Library.Signals.INotification;
import Library.Signals.IRing;
import Library.Signals.ISignal;
import Library.Signals.Ring;
import Library.User.UserBuilders.SavedUserBuilder;
import Library.User.UserBuilders.UserBuilder;
import Library.User.UserBuilders.UserDirector;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class User implements  IUser {
    /****Добавление/удаление сигналов с заходом в БД****/
    /****Выйти из профиля****/
    /****Пересмотреть абстракции***/

    private String name; //Имя
    private ArrayList<IRing> rings; //Будильники
    private ArrayList<INotification> notifications; //События
    private int moneyQuantity; //Число монет
    private ISetting userInterface;
    private String email; //Email пользователя

    private static UserDirector director;
    private static UserBuilder builder;
    private static PreferenceHelper preferenceHelper;
    private static User user; //Ссылка на себя

    private User() { //Новый пользователь

        userInterface = UserInterface.getInstance();
    }

    public static User getInstance() { //Создание пользователя
        if (user == null) { //Если вход не выполнен
            preferenceHelper = PreferenceHelper.getInstance();
            user = new User();
            //Если имеются сохраненные настройки
            if (!preferenceHelper.isEmpty()) { //если файл существует
                builder = new SavedUserBuilder(user);
                director = new UserDirector(builder);
            } else {
                /**регистрация/вход**/
            }

            try {
                director.construct();
                user = builder.getResult();
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

    public ArrayList<IRing> getRings() {
        return rings;
    }

    public void setRings(ArrayList<IRing> rings) {
        this.rings = rings;
    }

    public ArrayList<INotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<INotification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public void logOut() {
        /***Сохранение данных на сервер***/
        preferenceHelper = PreferenceHelper.getInstance();
        preferenceHelper.removePreference();
    }
}
